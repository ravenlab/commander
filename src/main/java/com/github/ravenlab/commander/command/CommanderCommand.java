package com.github.ravenlab.commander.command;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import com.github.ravenlab.commander.sender.CommanderSender;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public abstract class CommanderCommand {
	
	@Inject
	@Named("data")
	private CommandData data;
	private Collection<CommanderCommand> children;
	
	public CommanderCommand() {
		this.children = new ArrayList<>();
	}
	
	public abstract void doCommand(CommanderSender<?> sender, String name, CommandArgs arg);
	
	public CommandData getData() {
		return this.data;
	}
	
	public Collection<CommanderCommand> getChildren() {
		return Collections.unmodifiableCollection(this.children);
	}
	
	public CommanderCommand addChild(CommanderCommand child) {
		this.children.add(child);
		return this;
	}
}