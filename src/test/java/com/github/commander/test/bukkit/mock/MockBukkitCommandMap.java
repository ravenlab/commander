package com.github.commander.test.bukkit.mock;

import java.util.List;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;

public class MockBukkitCommandMap implements CommandMap {

	@Override
	public void registerAll(String fallbackPrefix, List<Command> commands) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean register(String label, String fallbackPrefix, Command command) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean register(String fallbackPrefix, Command command) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean dispatch(CommandSender sender, String cmdLine) throws CommandException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clearCommands() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Command getCommand(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String cmdLine) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> tabComplete(CommandSender sender, String cmdLine, Location location)
			throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}


}
