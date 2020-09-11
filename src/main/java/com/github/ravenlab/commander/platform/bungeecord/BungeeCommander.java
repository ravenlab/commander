package com.github.ravenlab.commander.platform.bungeecord;


import java.util.Map;
import java.util.Optional;

import com.github.ravenlab.commander.Commander;
import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeCommander extends Commander<Plugin, Command, CommandSender>{

	private Map<String, Command> knownCommands;
	
	public BungeeCommander() {
		this.knownCommands = new BungeeCommandMap()
		.getMapIfExists(ProxyServer.getInstance().getPluginManager());
	}
	
	@Override
	protected Optional<String> registerAlias(Plugin plugin, Command command, String alias, boolean forceRegister) {
		String registeredAlias = alias;
		
		if(this.knownCommands.containsKey(alias) && !forceRegister) {
			registeredAlias = this.getPluginName(plugin).toLowerCase() + ":" + alias;
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
		return new BungeeCommandWrapper(command);
	}
	
	@Override
	protected String getPluginName(Plugin plugin) {
		return plugin.getDescription().getName();
	}
}