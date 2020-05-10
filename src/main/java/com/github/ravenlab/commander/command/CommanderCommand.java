package com.github.ravenlab.commander.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.github.ravenlab.commander.sender.CommanderSender;
import com.google.inject.Inject;

public abstract class CommanderCommand {
	
	@Inject
	private List<String> aliases;
	private List<CommanderCommand> children;
	
	public CommanderCommand() {
		this.children = new ArrayList<>();
	}
	
	public abstract void doCommand(CommanderSender<?> sender, String name, CommandArgs arg);
	
	public List<String> getAliases() {
		return this.aliases;
	}
	
	public List<CommanderCommand> getChildren() {
		return Collections.unmodifiableList(this.children);
	}
	
	public CommanderCommand addChild(CommanderCommand child) {
		this.children.add(child);
		return this;
	}
}