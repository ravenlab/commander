package com.github.ravenlab.commander.platform.velocity;

import java.lang.reflect.Field;
import java.util.Map;

import com.velocitypowered.api.command.Command;

public class VelocityCommandMap {

	@SuppressWarnings("unchecked")
	public Map<String, Command> getMapIfExists(Object manager) {
		try {
			Field mapField = null;
			System.out.println(manager.getClass().getName());
			for(Field field : manager.getClass().getDeclaredFields()) {
				if(field.getName().equals("commands")) {
					mapField = field;
					break;
				}
			}
			if(mapField != null) {
				mapField.setAccessible(true);
				return (Map<String, Command>) mapField.get(manager);
			}
		} catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		return null;
	}
}