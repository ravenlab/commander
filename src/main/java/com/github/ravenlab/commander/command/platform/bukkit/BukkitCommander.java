package com.github.ravenlab.commander.command.platform.bukkit;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

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
	protected Optional<String> registerAlias(Plugin plugin, Command command, String alias, boolean forceRegister) {
		String registeredAlias = alias;
		if(this.knownCommands == null) {
			return Optional.empty();
		}
		
		if(this.knownCommands.containsKey(alias) && !forceRegister) {
			registeredAlias = plugin.getName().toLowerCase() + ":" + alias;
		}
		
		this.knownCommands.put(registeredAlias, command);
		return Optional.of(registeredAlias);
	}
	
	@Override
	protected boolean unregisterAlias(String command) {
		return this.knownCommands.remove(command) != null;
	}
	
	@Override
	protected Command createCommandWrapper(CommandData data, CommanderCommand command) {
		return new BukkitCommandWrapper(command);
	}
	
	@Override
	protected String getPluginName(Plugin plugin) {
		return plugin.getName();
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, Command> getKnownCommands() {	
		try {
			Field commandField = SimpleCommandMap.class.getDeclaredField("knownCommands");
			commandField.setAccessible(true);
			CommandMap commandMap = this.getCommandMap();
			if(commandMap != null) {
				return (Map<String, Command>) commandField.get(commandMap);
			}
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	private CommandMap getCommandMap() {
		try {
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
		} 
		catch (Exception e) { 
			e.printStackTrace(); 
		}

		return null;
	}
}