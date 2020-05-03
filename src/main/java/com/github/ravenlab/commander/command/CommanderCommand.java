package com.github.ravenlab.commander.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.ravenlab.commander.sender.CommanderSender;

public abstract class CommanderCommand {
	
	private String name;
	private List<CommanderCommand> children;
	
	public CommanderCommand(String name) {
		this.name = name;
		this.children = new ArrayList<>();
	}
	
	public abstract void doCommand(CommanderSender sender, String name, CommandArgs arg);
	
	public String getName() {
		return this.name;
	}
	
	public List<CommanderCommand> getChildren() {
		return Collections.unmodifiableList(this.children);
	}
	
	public CommanderCommand addChild(CommanderCommand child) {
		this.children.add(child);
		return this;
	}
}