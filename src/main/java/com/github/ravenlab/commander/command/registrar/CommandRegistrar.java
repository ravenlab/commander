package com.github.ravenlab.commander.command.registrar;

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

public abstract class CommandRegistrar<T, E> {
	
	private Map<T, Collection<String>> pluginCommands;
	
	public CommandRegistrar() {
		this.pluginCommands = new HashMap<>();
	}
	
	protected abstract boolean tryToRegister(String alias, boolean forceRegister, E command);
	protected abstract boolean tryToUnregister(Collection<String> commands);
	protected abstract E createCommandWrapper(CommandData data, CommanderCommand command);
	
	public RegistrationData register(T plugin, CommanderCommand command, boolean forceRegister) {
		Collection<String> registeredAliases = new ArrayList<>();
		Optional<CommandData> dataOptional = command.getData();
		if(!dataOptional.isPresent()) {
			return new RegistrationData(registeredAliases, RegistrationStatus.NO_ANNOTATION);
		}

		CommandData data = dataOptional.get();
		E wrapperCommand = this.createCommandWrapper(data, command);
		Collection<String> aliases = data.getAliases();
		for(String alias : aliases) {
			if(this.tryToRegister(alias, forceRegister, wrapperCommand)) {
				registeredAliases.add(alias);
			}
		}

		this.bootstrapCommand(plugin, command, data);
		RegistrationStatus status = this.getStatus(data, registeredAliases);
		return new RegistrationData(registeredAliases, status);
	}

	public RegistrationData register(T plugin, CommanderCommand command) {
		return this.register(plugin, command, false);
	}

	public boolean unregister(T plugin) {
		Optional<Collection<String>> commands = this.getCommands(plugin);
		this.tryToUnregister(commands);
		return this.removePluginCommands(plugin);
	}

	public boolean unregister(T plugin, String... commandsToUnregister) {
		Collection<String> commands = Arrays.asList(commandsToUnregister);
		this.tryToUnregister(commands);
		return this.removePluginCommands(plugin, commands);
	}

	public Optional<Collection<String>> getCommands(T plugin) {
		Collection<String> commands = this.pluginCommands.get(plugin);
		if(commands == null) {
			return Optional.empty();
		}

		return Optional.of(Collections.unmodifiableCollection(commands));
	}

	private boolean tryToUnregister(Optional<Collection<String>> commands) {
		if(commands.isPresent()) {
			return this.tryToUnregister(commands.get());
		}

		return false;
	}

	private boolean removePluginCommands(T plugin) {
		return this.pluginCommands.remove(plugin) != null;
	}

	private boolean removePluginCommands(T plugin, Collection<String> commands) {
		Collection<String> pluginCommands = this.pluginCommands.get(plugin);
		if(pluginCommands == null) {
			return false;
		}
		
		return pluginCommands.removeAll(commands);
	}

	private void bootstrapCommand(T plugin, CommanderCommand command, CommandData data) {
		Collection<String> aliases = data.getAliases();
		this.addPluginCommands(plugin, aliases);
	}

	private void addPluginCommands(T plugin, Collection<String> registeredCommands) {
		Collection<String> cmds = this.pluginCommands.get(plugin);
		if(cmds == null) {
			cmds = new HashSet<>();
			this.pluginCommands.put(plugin, cmds);
		}

		cmds.addAll(registeredCommands);
	}

	private RegistrationStatus getStatus(CommandData data, Collection<String> aliases) {
		if(aliases.size() == 0) {
			return RegistrationStatus.REGISTERED_NONE;
		} else if(aliases.size() != data.getAliases().size()) {
			return RegistrationStatus.REGISTERED_SOME;
		} else {
			return RegistrationStatus.REGISTERED_ALL;
		}
	}
}