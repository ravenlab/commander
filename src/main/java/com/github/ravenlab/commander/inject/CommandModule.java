package com.github.ravenlab.commander.inject;

import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.name.Names;

public class CommandModule implements Module {

	private CommanderCommand command;
	private CommandData data;
	
	public CommandModule(CommanderCommand command, CommandData data) {
		this.command = command;
		this.data = data;
	}
	
	@Override
	public void configure(Binder binder) {
		binder.bind(CommandData.class)
		.annotatedWith(Names.named("data"))
		.toInstance(this.data);
		binder.requestInjection(this.command);
	}
}