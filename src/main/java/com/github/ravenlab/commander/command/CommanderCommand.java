package com.github.ravenlab.commander.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.github.ravenlab.commander.command.parser.CommandDataParser;

public abstract class CommanderCommand {
	
	private CommandDataParser dataParser;
	private Optional<CommandData> data;
	private Set<CommanderCommand> childrenSet;
	private Map<String, CommanderCommand> childrenMap;
	
	public CommanderCommand() {
		this.dataParser = new CommandDataParser();
		this.data = this.dataParser.parse(this);
		this.childrenSet = new HashSet<>();
		this.childrenMap = new HashMap<>();
	}
	
	public abstract <T> void doCommand(T sender, String name, CommandArgs arg);
	
	public Optional<CommandData> getData() {
		return this.data;
	}
	
	public Collection<CommanderCommand> getChildren() {
		return Collections.unmodifiableCollection(this.childrenSet);
	}
	
	public CommanderCommand getChildByName(String name) {
		return this.childrenMap.get(name.toLowerCase());
	}

	public CommanderCommand addChild(CommanderCommand child) {
		Optional<CommandData> childData = child.getData();

		if(childData.isPresent()) {
			this.childrenSet.add(child);
			Collection<String> aliases = childData.get().getAliases();
			for(String alias : aliases) {
				this.childrenMap.put(alias, child);
			}
		}

		return this;
	}
}