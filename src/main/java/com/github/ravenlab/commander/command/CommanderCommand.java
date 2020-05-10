package com.github.ravenlab.commander.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.ravenlab.commander.sender.CommanderSender;
import com.google.inject.Inject;
import com.google.inject.name.Named;

public abstract class CommanderCommand {
	
	@Inject
	@Named("data")
	private CommandData data;
	private List<CommanderCommand> children;
	
	public CommanderCommand() {
		this.children = new ArrayList<>();
	}
	
	public abstract void doCommand(CommanderSender<?> sender, String name, CommandArgs arg);
	
	public CommandData getData() {
		return this.data;
	}
	
	public List<CommanderCommand> getChildren() {
		return Collections.unmodifiableList(this.children);
	}
	
	public CommanderCommand addChild(CommanderCommand child) {
		this.children.add(child);
		return this;
	}
}