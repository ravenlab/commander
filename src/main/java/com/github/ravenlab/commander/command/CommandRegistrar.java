package com.github.ravenlab.commander.command;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.github.ravenlab.commander.inject.CommandModule;
import com.github.ravenlab.commander.util.ChatColor;
import com.google.inject.Guice;

public abstract class CommandRegistrar<T, E> {

	private Map<T, Collection<String>> pluginCommands;
	
	public CommandRegistrar() {
		this.pluginCommands = new HashMap<>();
	}
	
	public abstract boolean unregister(T plugin);
	protected abstract boolean tryToRegister(String alias, boolean forceRegister, E command);
	
	public RegistrationData register(T plugin, CommanderCommand command, boolean forceRegister) {
		List<String> registeredAliases = new ArrayList<>();
		CommandData data = this.parseCommandData(command);
		if(data == null) {
			return new RegistrationData(registeredAliases, RegistrationStatus.NO_ANNOTATION);
		}
		
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
	
	protected Collection<String> getCommands(T plugin) {
		return Collections.unmodifiableCollection(this.pluginCommands.get(plugin));
	}
	
	protected boolean removePluginCommands(T plugin) {
		return this.pluginCommands.remove(plugin) != null;
	}
	
	protected abstract E createCommandWrapper(CommandData data, CommanderCommand command);
	
	private void bootstrapCommand(T plugin, CommanderCommand command, CommandData data) {
		Collection<String> aliases = data.getAliases();
		this.addPluginCommands(plugin, aliases);
		this.injectCommand(command, data);
	}
	
	
	private void addPluginCommands(T plugin, Collection<String> registeredCommands) {
		Collection<String> cmds = this.pluginCommands.get(plugin);
		if(cmds == null) {
			cmds = new HashSet<>();
			this.pluginCommands.put(plugin, cmds);
		}
		
		cmds.addAll(registeredCommands);
	}
	
	private void injectCommand(CommanderCommand command, CommandData data) {
		Guice.createInjector(new CommandModule(command, data));
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
		Collection<String> aliases = new HashSet<>();
		for(String alias : found.aliases()) {
			aliases.add(alias.toLowerCase());
		}
		
		aliases.add(name);
		String permission = found.permission();
		String noPermissionMessage = found.noPermissionMessage();
		
		if(noPermissionMessage.equals("")) {
			noPermissionMessage = ChatColor.translateAlternateColorCodes('&', "&cYou do not have permission to execute that command!");
		} else {
			noPermissionMessage = ChatColor.translateAlternateColorCodes('&', noPermissionMessage);
		}
		
		return new CommandData(name, aliases, permission, noPermissionMessage);
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