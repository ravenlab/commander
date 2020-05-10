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
		ParserData parserData = this.parser.parse(this.command, args);
		CommanderCommand commandToExecute = parserData.getCommand();
		CommandData commandData = commandToExecute.getData();
		
		String[] executeArgs = parserData.getArgs();
		String permission = commandData.getPermission();
		
		
		if(permission.equals("") || sender.hasPermission(permission)) {
			CommandArgs commandArgs = new CommandArgs(Arrays.asList(executeArgs));
			commandToExecute.doCommand(sender, label, commandArgs);
		} else {
			String noPermissionMessage = commandData.getNoPermissionMessage();
			sender.sendMessage(noPermissionMessage);
		}
	}
}