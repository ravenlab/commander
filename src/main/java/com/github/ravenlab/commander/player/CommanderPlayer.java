package com.github.ravenlab.commander.player;

import java.util.UUID;

import com.github.ravenlab.commander.sender.CommanderSender;

public abstract class CommanderPlayer implements CommanderSender {

	public abstract UUID getUniqueId();
	
}