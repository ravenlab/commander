package com.github.ravenlab.commander.command.platform.bungeecord;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.PluginManager;


public class BungeeCommandMap {

	public Map<String, Command> getMapIfExists(Class<? extends Map<String, Command>> clazz) {
		try {
			return this.getKnownCommands(clazz);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Command> getKnownCommands(Class<? extends Map<String, Command>> clazz) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InvocationTargetException {	
		Field commandField = clazz.getDeclaredField("knownCommands");
		commandField.setAccessible(true);
		Map<String, Command> commandMap = this.getCommandMap();
		if(commandMap != null) {
			return (Map<String, Command>) commandField.get(commandMap);
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	private Map<String, Command> getCommandMap() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		PluginManager manager = ProxyServer.getInstance().getPluginManager();
		Field mapField = null;
		for(Field field : manager.getClass().getDeclaredFields()) {
			if(field.getName().equals("commandMap")) {
				mapField = field;
				break;
			}
		}
		
		if(mapField != null) {
			mapField.setAccessible(true);
			return (Map<String, Command>) mapField.get(manager);
		}

		return null;
	}
}