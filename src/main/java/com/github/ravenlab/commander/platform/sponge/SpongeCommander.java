package com.github.ravenlab.commander.platform.sponge;

import java.util.Map;
import java.util.Optional;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.plugin.PluginContainer;

import com.github.ravenlab.commander.Commander;
import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;

public class SpongeCommander extends Commander<PluginContainer, CommandExecutor, CommandSource>{

	private Map<String, CommandExecutor> knownCommands;
	
	//Look at https://github.com/SpongePowered/SpongeCommon/blob/cd424add778a1059f036c052c3f685619305d001/src/main/java/org/spongepowered/common/command/SpongeCommandManager.java
	
	public SpongeCommander() {
		this.knownCommands = new BungeeCommandMap()
		.getMapIfExists(ProxyServer.getInstance().getPluginManager());
	}
	
	@Override
	protected Optional<String> registerAlias(PluginContainer plugin, CommandExecutor command, String alias, boolean forceRegister) {
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
	protected CommandExecutor createCommandWrapper(CommandData data, CommanderCommand<CommandSource> command) {
		return new SpongeCommandWrapper(command);
	}
	
	@Override
	protected String getPluginName(PluginContainer plugin) {
		return plugin.getName();
	}
}