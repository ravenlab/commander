package com.github.commander.test.command;

import com.github.ravenlab.commander.command.Command;
import com.github.ravenlab.commander.command.CommandArgs;
import com.github.ravenlab.commander.command.CommanderCommand;

@Command("grandchild")
public class GrandChildCommand extends CommanderCommand {

	@Override
	public <TestSender> void doCommand(TestSender sender, String name, CommandArgs arg) {}

}