package com.github.ravenlab.commander.command;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.github.ravenlab.commander.inject.CommandModule;
import com.google.inject.Guice;

public abstract class CommandRegistrar<T> {

	private Map<T, Collection<String>> pluginCommands;
	
	public CommandRegistrar() {
		this.pluginCommands = new HashMap<>();
	}
	
	public abstract RegistrationData register(T plugin, CommanderCommand command, boolean forceRegister);
	public abstract boolean unregister(T plugin);
	
	public RegistrationData register(T plugin, CommanderCommand command) {
		return this.register(plugin, command, false);
	}
	
	protected Collection<String> getCommands(T plugin) {
		return Collections.unmodifiableCollection(this.pluginCommands.get(plugin));
	}
	
	protected boolean removePluginCommands(T plugin) {
		return this.pluginCommands.remove(plugin) != null;
	}
	
	protected void bootstrapCommand(T plugin, CommanderCommand command, List<String> aliases) {
		this.addPluginCommands(plugin, aliases);
		this.injectCommand(command, aliases);
	}
	
	private void addPluginCommands(T plugin, Collection<String> registeredCommands) {
		Collection<String> cmds = this.pluginCommands.get(plugin);
		if(cmds == null) {
			cmds = new HashSet<>();
			this.pluginCommands.put(plugin, cmds);
		}
		
		cmds.addAll(registeredCommands);
	}
	
	private void injectCommand(CommanderCommand command, List<String> aliases) {
		Guice.createInjector(new CommandModule(command, aliases));
	}
	
	protected CommandData parseCommandData(CommanderCommand command) {
		Command found = null;
		
		for(Annotation anno : command.getClass().getAnnotations()) {
			if(anno.getClass().equals(Command.class)) {
				found = (Command) anno;
			}
		}
		
		if(found == null) {
			return null;
		}
		
		String name = found.value();
		List<String> aliases = Arrays.asList(found.aliases());
		aliases.add(name);
		return new CommandData(name, aliases);
	}
	
	protected RegistrationStatus getStatus(CommandData data, Collection<String> aliases) {
		if(aliases.size() == 0) {
			return RegistrationStatus.FAILED;
		} else if(aliases.size() != data.getAliases().size()) {
			return RegistrationStatus.REGISTERED_SOME;
		} else {
			return RegistrationStatus.REGISTERED_ALL;
		}
	}
}