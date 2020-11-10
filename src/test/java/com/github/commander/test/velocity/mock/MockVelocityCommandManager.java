package com.github.commander.test.velocity.mock;

import java.util.HashMap;
import java.util.Map;

import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandManager;

public abstract class MockVelocityCommandManager implements CommandManager {

	@SuppressWarnings("unused")
	private Map<String, Command> commands;
	
	public MockVelocityCommandManager() {
		this.commands = new HashMap<>();
	}
	
}
