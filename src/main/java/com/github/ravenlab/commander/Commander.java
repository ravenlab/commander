package com.github.ravenlab.commander;

import com.github.ravenlab.commander.command.CommanderCommand;

public class Commander {

	public boolean register(CommanderCommand command) {
		return new CommanderBuilder(command);
	}
}