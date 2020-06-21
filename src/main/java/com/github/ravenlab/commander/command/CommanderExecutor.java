package com.github.ravenlab.commander.command;

import java.util.Arrays;

import com.github.ravenlab.commander.command.parser.ChildCommandParser;
import com.github.ravenlab.commander.command.parser.ChildCommandParserData;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.sender.CommanderSender;

public class CommanderExecutor<T> {

	private CommanderCommand<T> command;
	private ChildCommandParser<T> parser;
	private TypeResolver<?, ?> resolver;
	
	public CommanderExecutor(CommanderCommand<T> command, TypeResolver<?, ?> resolver) {
		this.command = command;
		this.parser = new ChildCommandParser<>();
		this.resolver = resolver;
	}
	
	@SuppressWarnings("unchecked")
	public void execute(CommanderSender<?> sender, String label, String[] args) {
		ChildCommandParserData<T> parserData = this.parser.parse(this.command, args);
		CommanderCommand<T> commandToExecute = parserData.getCommand();
		CommandData commandData = commandToExecute.getData().get();
		
		String[] executeArgs = parserData.getArgs();
		String permission = commandData.getPermission();
		Object nativeSender = sender.getNative();
		
		if(permission.equals("") || sender.hasPermission(permission)) {
			CommandArgs commandArgs = new CommandArgs(Arrays.asList(executeArgs), this.resolver);
			commandToExecute.doCommand((T) nativeSender, label, commandArgs);
		} else {
			String noPermissionMessage = commandData.getNoPermissionMessage();
			sender.sendMessage(noPermissionMessage);
		}
	}
}