package com.github.ravenlab.commander.command.platform.bungeecord;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.PluginManager;


public class BungeeCommandMap {

	public Map<String, Command> getMapIfExists() {
		try {
			return this.getCommandMap();
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		return null;
	}


	@SuppressWarnings("unchecked")
	public Map<String, Command> getCommandMap() throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
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