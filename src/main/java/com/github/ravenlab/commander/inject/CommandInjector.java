package com.github.ravenlab.commander.inject;

import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.parser.CommandDataParser;
import com.google.inject.Guice;

public class CommandInjector {

	private CommandDataParser dataParser;
	
	public CommandInjector() {
		this.dataParser = new CommandDataParser();
	}
	
	public void inject(CommanderCommand command) {
		CommandData data = this.dataParser.parse(command);
		Guice.createInjector(new CommandModule(command, data));
	}
}