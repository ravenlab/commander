package com.github.commander.test.command;

import com.github.ravenlab.commander.command.CommandArgs;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.sender.CommanderSender;

public class NoAnnotationCommand extends CommanderCommand {

	@Override
	public void doCommand(CommanderSender<?> sender, String name, CommandArgs arg) {}

}