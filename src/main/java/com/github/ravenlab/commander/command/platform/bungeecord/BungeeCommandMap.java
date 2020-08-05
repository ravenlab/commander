package com.github.ravenlab.commander.command.platform.bungeecord;

import java.lang.reflect.Field;
import java.util.Map;

import net.md_5.bungee.api.plugin.Command;

public class BungeeCommandMap {

	@SuppressWarnings("unchecked")
	public Map<String, Command> getMapIfExists(Object manager) {
		try {
			Field mapField = manager.getClass().getDeclaredField("commandMap");
			mapField.setAccessible(true);
			return (Map<String, Command>) mapField.get(manager);
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException | NoSuchFieldException e) {
			e.printStackTrace();
		}

		return null;
	}
}