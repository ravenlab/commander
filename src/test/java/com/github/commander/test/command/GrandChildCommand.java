package com.github.commander.test.command;

import com.github.ravenlab.commander.command.Command;
import com.github.ravenlab.commander.command.CommandArgs;
import com.github.ravenlab.commander.command.CommanderCommand;

@Command("grandchild")
public class GrandChildCommand<T> extends CommanderCommand<T> {

	@Override
	public void doCommand(T sender, String name, CommandArgs arg) {}

}