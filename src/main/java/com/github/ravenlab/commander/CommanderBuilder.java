package com.github.ravenlab.commander;

import com.github.ravenlab.commander.command.CommanderCommand;

public class CommanderBuilder {

	private CommanderCommand parent;
	protected CommanderBuilder(CommanderCommand parent) {
		this.parent = parent;
	}
	
	public CommanderBuilder addChild(CommanderCommand child) {
		
	}
}