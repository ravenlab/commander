package com.github.ravenlab.commander.command.platform.bukkit;

import java.lang.reflect.Field;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

public class BukkitCommandMap {

	public Map<String, Command> getMapIfExists(Class<? extends CommandMap> clazz) {
		try {
			return this.getKnownCommands(clazz);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Command> getKnownCommands(Class<? extends CommandMap> clazz) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {	
		Field commandField = clazz.getDeclaredField("knownCommands");
		commandField.setAccessible(true);
		CommandMap commandMap = this.getCommandMap();
		if(commandMap != null) {
			return (Map<String, Command>) commandField.get(commandMap);
		}

		return null;
	}

	private CommandMap getCommandMap() throws IllegalArgumentException, IllegalAccessException {
		Server server = Bukkit.getServer();
		Field mapField = null;
		for(Field field : server.getClass().getDeclaredFields()) {
			if(field.getName().equals("commandMap")) {
				mapField = field;
				break;
			}
		}

		if(mapField != null) {
			mapField.setAccessible(true);
			return (CommandMap) mapField.get(server);
		}

		return null;
	}
}