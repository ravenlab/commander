package com.github.ravenlab.commander.command.parser;

import com.github.ravenlab.commander.command.CommanderCommand;

public class ParserData {

	private CommanderCommand command;
	private String[] args;
	
	public ParserData(CommanderCommand command, String[] args) {
		this.command = command;
		this.args = args;
	}
	
	public CommanderCommand getCommand() {
		return this.command;
	}
	
	public String[] getArgs() {
		return this.args;
	}
}