package com.github.ravenlab.commander.command;

import java.util.UUID;

import com.github.ravenlab.commander.player.CommanderPlayer;

public enum ArgType {

	INTEGER(Integer.class),
	DOUBLE(Double.class),
	STRING(String.class),
	UUID(UUID.class),
	PLAYER(CommanderPlayer.class);
	
	private Class<?> clazz;
	
	private ArgType(Class<?> clazz) {
		this.clazz = clazz;
	}
	
	public boolean isType(Object obj) {
		return obj.getClass().isAssignableFrom(this.clazz);
	}
}