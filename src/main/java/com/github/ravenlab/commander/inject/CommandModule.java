package com.github.ravenlab.commander.inject;

import java.util.List;

import com.github.ravenlab.commander.command.CommanderCommand;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;

public class CommandModule implements Module {

	private CommanderCommand command;
	private List<String> aliases;
	public CommandModule(CommanderCommand command, List<String> aliases) {
		this.command = command;
		this.aliases = aliases;
	}
	
	@Override
	public void configure(Binder binder) {
		binder.bind(new TypeLiteral<List<String>>(){})
		.annotatedWith(Names.named("aliases"))
		.toInstance(this.aliases);
		binder.requestInjection(this.command);
	}
}