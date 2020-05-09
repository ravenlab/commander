package com.github.ravenlab.commander.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.ravenlab.commander.sender.CommanderSender;

public abstract class CommanderCommand {
	
	private List<CommanderCommand> children;
	
	public CommanderCommand() {
		this.children = new ArrayList<>();
	}
	
	public abstract void doCommand(CommanderSender<?> sender, String name, CommandArgs arg);
	
	public List<CommanderCommand> getChildren() {
		return Collections.unmodifiableList(this.children);
	}
	
	public CommanderCommand addChild(CommanderCommand child) {
		this.children.add(child);
		return this;
	}
}