package com.github.ravenlab.commander.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.github.ravenlab.commander.command.parser.CommandDataParser;
import com.github.ravenlab.commander.sender.CommanderSender;

public abstract class CommanderCommand {
	
	private CommandDataParser dataParser;
	private CommandData data;
	private Set<CommanderCommand> childrenSet;
	private Map<String, CommanderCommand> childrenMap;
	
	public CommanderCommand() {
		this.dataParser = new CommandDataParser();
		this.data = this.dataParser.parse(this);
		this.childrenSet = new HashSet<>();
		this.childrenMap = new HashMap<>();
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
		this.childrenSet.add(child);
		
		Collection<String> aliases = child.getData().getAliases();
		for(String alias : aliases) {
			this.childrenMap.put(alias, child);
		}
		
		return this;
	}
}