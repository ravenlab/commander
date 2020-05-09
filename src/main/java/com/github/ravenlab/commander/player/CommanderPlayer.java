package com.github.ravenlab.commander.player;

import java.util.UUID;

import com.github.ravenlab.commander.sender.CommanderSender;

public abstract class CommanderPlayer<T> extends CommanderSender<T> {
	
	public CommanderPlayer(T player) {
		super(player);
	}
	
	public abstract UUID getUniqueId();
}