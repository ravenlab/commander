package com.github.ravenlab.commander.command;

import com.github.ravenlab.commander.sender.CommanderSender;

public interface CommanderCommand {
	
	public void doCommand(CommanderSender sender, String name, CommandArgs arg);
	
}