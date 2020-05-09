package com.github.ravenlab.commander.registrar;

import com.github.ravenlab.commander.command.CommanderCommand;

public abstract class CommandRegistrar {

	public abstract boolean register(CommanderCommand command, boolean forceRegister);

	public boolean register(CommanderCommand command) {
		return this.register(command, false);
	}
}