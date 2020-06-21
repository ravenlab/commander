package com.github.ravenlab.commander.command.parser;

import com.github.ravenlab.commander.command.CommanderCommand;

public class ChildCommandParserData<T> {

	private CommanderCommand<T> command;
	private String[] args;
	
	public ChildCommandParserData(CommanderCommand<T> command, String[] args) {
		this.command = command;
		this.args = args;
	}
	
	public CommanderCommand<T> getCommand() {
		return this.command;
	}
	
	public String[] getArgs() {
		return this.args;
	}
}