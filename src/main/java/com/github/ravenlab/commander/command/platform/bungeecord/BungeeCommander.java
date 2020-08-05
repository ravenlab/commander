package com.github.ravenlab.commander.command.platform.bungeecord;


import java.util.Optional;

import com.github.ravenlab.commander.Commander;
import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeeCommander extends Commander<Plugin, Command, CommandSender>{

	@Override
	protected Optional<String> registerAlias(Plugin plugin, Command command, String alias, boolean forceRegister) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean unregisterAlias(String command) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Command createCommandWrapper(CommandData data, CommanderCommand<CommandSender> command) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getPluginName(Plugin plugin) {
		// TODO Auto-generated method stub
		return null;
	}

}
