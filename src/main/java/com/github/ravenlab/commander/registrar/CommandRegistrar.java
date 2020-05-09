package com.github.ravenlab.commander.registrar;

import com.github.ravenlab.commander.command.CommanderCommand;

public interface CommandRegistrar {

	public boolean register(CommanderCommand command);
	
}