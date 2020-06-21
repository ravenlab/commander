package com.github.commander.test.command;

import com.github.ravenlab.commander.command.CommandArgs;
import com.github.ravenlab.commander.command.CommanderCommand;

public class NoAnnotationCommand<T> extends CommanderCommand<T> {

	@Override
	public void doCommand(T sender, String name, CommandArgs arg) {}

}