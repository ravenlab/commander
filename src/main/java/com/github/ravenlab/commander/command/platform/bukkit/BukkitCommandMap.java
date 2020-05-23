package com.github.ravenlab.commander.command.platform.bukkit;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;

public class BukkitCommandMap {

	public Map<String, Command> getMapIfExists(Class<? extends CommandMap> clazz) {
		try {
			return this.getKnownCommands(clazz);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Command> getKnownCommands(Class<? extends CommandMap> clazz) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {	
		Field commandField = clazz.getDeclaredField("knownCommands");
		commandField.setAccessible(true);
		CommandMap commandMap = this.getCommandMap();
		if(commandMap != null) {
			return (Map<String, Command>) commandField.get(commandMap);
		}

		return null;
	}

	private CommandMap getCommandMap() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		Server server = Bukkit.getServer();
		Method mapMethod = null;
		for(Method method : server.getClass().getDeclaredMethods()) {
			if(method.getName().equals("getCommandMap")) {
				mapMethod = method;
				break;
			}
		}
		
		if(mapMethod != null) {
			mapMethod.setAccessible(true);
			return (CommandMap) mapMethod.invoke(server);
		}

		return null;
	}
}