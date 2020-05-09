package com.github.ravenlab.commander.command;

import java.util.List;

public abstract class CommandRegistrar<T> {

	public abstract List<String> register(T plugin, CommanderCommand command, boolean forceRegister);

	public List<String> register(T plugin, CommanderCommand command) {
		return this.register(plugin, command, false);
	}
	
	protected CommandData parseCommandData(CommanderCommand command) {
		
	}
}