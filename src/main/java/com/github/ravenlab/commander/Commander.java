package com.github.ravenlab.commander;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;

import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;

public abstract class Commander<T, E, F> {
	
	private Map<T, Collection<String>> pluginCommands;
	
	public Commander() {
		this.pluginCommands = new HashMap<>();
	}
	
	protected abstract Optional<String> registerAlias(T plugin, E command, String alias, boolean forceRegister);
	protected abstract boolean unregisterAlias(String command);
	protected abstract E createCommandWrapper(CommandData data, CommanderCommand<F> command);
	
	public boolean register(T plugin, CommanderCommand<F> command, boolean forceRegister) {
		Collection<String> registeredAliases = new ArrayList<>();
		Optional<CommandData> dataOptional = command.getData();
		if(!dataOptional.isPresent()) {
			return false;
		}

		CommandData data = dataOptional.get();
		E wrapperCommand = this.createCommandWrapper(data, command);
		Collection<String> aliases = data.getAliases();
		for(String alias : aliases) {
			Optional<String> aliasOptional = this.registerAlias(plugin, wrapperCommand, alias, forceRegister);
			if(aliasOptional.isPresent()) {
				registeredAliases.add(aliasOptional.get());
			} else {
				return false;
			}
		}

		this.addPluginCommands(plugin, registeredAliases);
		return true;
	}

	public boolean register(T plugin, CommanderCommand<F> command) {
		return this.register(plugin, command, false);
	}

	public boolean unregister(T plugin) {
		Optional<Collection<String>> commandsOptional = this.getCommands(plugin);
		if(!commandsOptional.isPresent()) {
			return false;
		}
		
		Collection<String> commands = commandsOptional.get();
		String[] commandsArray = commands.toArray(new String[commands.size()]);
		return this.unregister(plugin, commandsArray);
	}

	public boolean unregister(T plugin, String... commandsToUnregister) {
		Collection<String> commands = Arrays.asList(commandsToUnregister);
		String pluginName = this.getPluginName(plugin);
		
		for(String command : commands) {
			String pluginAlias = pluginName + ":" + command;
			if(!this.unregisterAlias(pluginAlias)) {
				this.unregisterAlias(command);
			}
		}
		
		return this.removePluginCommands(plugin, commands);
	}

	public Optional<Collection<String>> getCommands(T plugin) {
		Collection<String> commands = this.pluginCommands.get(plugin);
		if(commands == null) {
			return Optional.empty();
		}

		return Optional.of(Collections.unmodifiableCollection(commands));
	}
	
	protected abstract String getPluginName(T plugin);

	private boolean removePluginCommands(T plugin, Collection<String> commands) {
		Collection<String> pluginCommands = this.pluginCommands.get(plugin);
		if(pluginCommands == null) {
			return false;
		}
		
		boolean modified = false;
		String pluginName = this.getPluginName(plugin);
		for(String cmd : commands) {
			String pluginAlias = pluginName + ":" + cmd;
			boolean removed = pluginCommands.remove(pluginAlias);
			if(!removed) {
				removed = pluginCommands.remove(cmd);
				if(removed) {
					modified = true;
				}
			} else {
				modified = true;
			}
		}
		
		return modified;
	}

	private void addPluginCommands(T plugin, Collection<String> registeredCommands) {
		Collection<String> cmds = this.pluginCommands.get(plugin);
		if(cmds == null) {
			cmds = new HashSet<>();
			this.pluginCommands.put(plugin, cmds);
		}

		cmds.addAll(registeredCommands);
	}
}