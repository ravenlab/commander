package com.github.ravenlab.commander.command;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

public abstract class CommanderCommand<T> {
	
	private Optional<CommandData> data;
	private Set<CommanderCommand<T>> childrenSet;
	private Map<String, CommanderCommand<T>> childrenMap;
	
	public CommanderCommand() {
		this.data = new CommandData.Builder().consumeCommand(this).build();
		this.childrenSet = new HashSet<>();
		this.childrenMap = new HashMap<>();
	}
	
	public abstract boolean execute(T sender, String commandName, CommandArgs args);
	
	public Optional<CommandData> getData() {
		return this.data;
	}
	
	public boolean setData(CommandData data) {
		if(this.data.isPresent()) {
			return false;
		}
		this.data = Optional.of(data);
		return true;
	}
	
	public Collection<CommanderCommand<T>> getChildren() {
		return Collections.unmodifiableCollection(this.childrenSet);
	}
	
	public CommanderCommand<T> getChildByName(String name) {
		return this.childrenMap.get(name.toLowerCase());
	}

	public CommanderCommand<T> addChild(CommanderCommand<T> child) {
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