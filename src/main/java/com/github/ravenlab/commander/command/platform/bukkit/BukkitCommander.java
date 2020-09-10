package com.github.ravenlab.commander.command.platform.bukkit;

import java.util.Map;
import java.util.Optional;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.plugin.Plugin;

import com.github.ravenlab.commander.Commander;
import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;

public class BukkitCommander extends Commander<Plugin, Command, CommandSender> {
	
	private Map<String, Command> knownCommands;
	
	public BukkitCommander() {
		this.knownCommands = new BukkitCommandMap()
		.getMapIfExists(SimpleCommandMap.class);
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
	protected Command createCommandWrapper(CommandData data, CommanderCommand<CommandSender> command) {
		return new BukkitCommandWrapper(command);
	}
	
	@Override
	protected String getPluginName(Plugin plugin) {
		return plugin.getName();
	}

	@Override
	protected void createUnregisterSequence(Plugin plugin) {
		BukkitCommander commander = this;
		plugin.getServer().getPluginManager().registerEvents(new Listener() {
			@EventHandler
			public void onDisable(PluginDisableEvent event) {
				Plugin plugin = event.getPlugin();
				if(commander.removeRegisteredPlugin(plugin)) {
					commander.unregister(plugin);
				}
			}
		}, plugin);
	}
}