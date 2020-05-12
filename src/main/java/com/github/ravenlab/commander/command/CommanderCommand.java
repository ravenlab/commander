package com.github.ravenlab.commander.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.github.ravenlab.commander.inject.CommandInjector;
import com.github.ravenlab.commander.sender.CommanderSender;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public abstract class CommanderCommand {
	
	@Inject
	@Named("data")
	private CommandData data;
	private Set<CommanderCommand> childrenSet;
	private Map<String, CommanderCommand> childrenMap;
	private CommandInjector injector;
	
	public CommanderCommand() {
		this.childrenSet = new HashSet<>();
		this.childrenMap = new HashMap<>();
		this.injector = new CommandInjector();
	}
	
	public abstract void doCommand(CommanderSender<?> sender, String name, CommandArgs arg);
	
	public CommandData getData() {
		return this.data;
	}
	
	public Collection<CommanderCommand> getChildren() {
		return Collections.unmodifiableCollection(this.childrenSet);
	}
	
	public CommanderCommand getChildByName(String name) {
		return this.childrenMap.get(name.toLowerCase());
	}
	
	public CommanderCommand addChild(CommanderCommand child) {
		this.injector.inject(child);
		this.childrenSet.add(child);
		
		Collection<String> aliases = child.getData().getAliases();
		for(String alias : aliases) {
			this.childrenMap.put(alias, child);
		}
		
		return this;
	}
}