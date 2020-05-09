package com.github.ravenlab.commander.command.bukkit;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.activation.CommandMap;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.Plugin;

import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommandRegistrar;
import com.github.ravenlab.commander.command.CommanderCommand;

public class BukkitCommandRegistrar extends CommandRegistrar<Plugin> {
	
	private Map<String, Command> knownCommands;
	public BukkitCommandRegistrar() {
		this.knownCommands = this.getKnownCommands();
	}

	@Override
	public List<String> register(Plugin plugin, CommanderCommand command, boolean forceRegister) {
		List<String> registeredAliases = new ArrayList<>();
		CommandData data = this.parseCommandData(command);
		Command bukkitCommand = this.createBukkitCommand(command);
		for(String alias : data.getAliases()) {
			if(this.tryToRegister(alias, forceRegister, bukkitCommand)) {
				registeredAliases.add(alias);
			}
		}
		
		return registeredAliases;
	}
	
	private boolean tryToRegister(String alias, boolean forceRegister, Command command) {
		if(this.knownCommands.containsKey(alias) && !forceRegister) {
			return false;
		} else {
			this.knownCommands.put(alias, command);
			return true;
		}
	}
	
	private Command createBukkitCommand(CommanderCommand command) {
		
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