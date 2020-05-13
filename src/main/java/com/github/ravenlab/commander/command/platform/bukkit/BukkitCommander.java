package com.github.ravenlab.commander.command.platform.bukkit;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;

import com.github.ravenlab.commander.Commander;
import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;

public class BukkitCommander extends Commander<Plugin, Command> {
	
	private Map<String, Command> knownCommands;
	
	public BukkitCommander() {
		this.knownCommands = this.getKnownCommands();
	}
	
	@Override
	protected boolean tryToRegister(String alias, boolean forceRegister, Command command) {
		if(this.knownCommands.containsKey(alias) && !forceRegister) {
			return false;
		} else {
			this.knownCommands.put(alias, command);
			return true;
		}
	}
	
	@Override
	protected boolean tryToUnregister(Collection<String> commands) {
		boolean modified = false;
		for(String cmd : commands) {
			Command knownCommand = this.knownCommands.remove(cmd);
			if(knownCommand != null) {
				modified = true;
			}
		}
		
		return modified;
	}
	
	@Override
	protected Command createCommandWrapper(CommandData data, CommanderCommand command) {
		return new BukkitCommandWrapper(data, command);
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Command> getKnownCommands() {	
		try {
			Field commandField = SimpleCommandMap.class.getDeclaredField("knownCommands");
			commandField.setAccessible(true);
			return (Map<String, Command>) commandField.get(this.getCommandMap());
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private CommandMap getCommandMap() {
		try {
			Server server = Bukkit.getServer();
			Field mapField = server.getClass().getDeclaredField("commandMap");
			mapField.setAccessible(true);
			return (CommandMap) mapField.get(server);
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		}

		return null;
	}
}