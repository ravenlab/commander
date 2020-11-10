package com.github.ravenlab.commander.platform.velocity;

import java.util.Map;
import java.util.Optional;

import com.github.ravenlab.commander.Commander;
import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.proxy.ProxyServer;

public class VelocityCommander extends Commander<PluginContainer, Command, CommandSource>{

	private Map<String, Command> knownCommands;
	private ProxyServer server;
	
	public VelocityCommander(ProxyServer server) {
		this.server = server;
		this.knownCommands = new VelocityCommandMap()
				.getMapIfExists(this.server.getCommandManager());
	}
	
	@Override
	protected Optional<String> registerAlias(PluginContainer plugin, Command command, String alias, boolean forceRegister) {
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
	protected Command createCommandWrapper(CommandData data, CommanderCommand<CommandSource> command) {
		return new VelocityCommandWrapper(command, this.server);
	}
	
	@Override
	protected String getPluginName(PluginContainer plugin) {
		return plugin.getDescription().getName().get();
	}
}