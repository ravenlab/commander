package com.github.ravenlab.commander.command;

import java.util.Arrays;

import com.github.ravenlab.commander.command.parser.CommandParser;
import com.github.ravenlab.commander.command.parser.ParserData;
import com.github.ravenlab.commander.sender.CommanderSender;

public class CommanderExecutor {

	private CommanderCommand command;
	private CommandParser parser;
	public CommanderExecutor(CommanderCommand command) {
		this.command = command;
		this.parser = new CommandParser();
	}
	
	public void execute(CommanderSender<?> sender, String label, String[] args) {
		ParserData data = this.parser.parse(this.command, args);
		CommanderCommand commandToExecute = data.getCommand();
		String[] executeArgs = data.getArgs();
		
		CommandArgs commandArgs = new CommandArgs(Arrays.asList(executeArgs));
		commandToExecute.doCommand(sender, label, commandArgs); 
	}
}