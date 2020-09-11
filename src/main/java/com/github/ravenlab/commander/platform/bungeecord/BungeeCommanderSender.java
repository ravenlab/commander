package com.github.ravenlab.commander.platform.bungeecord;

import com.github.ravenlab.commander.sender.CommanderSender;

import net.md_5.bungee.api.CommandSender;

public class BungeeCommanderSender extends CommanderSender<CommandSender> {

	public BungeeCommanderSender(CommandSender sender) {
		super(sender);
	}

	@Override
	public String getName() {
		return this.getNative().getName();
	}

	@Override
	public boolean hasPermission(String permission) {
		return this.getNative().hasPermission(permission);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void sendMessage(String message) {
		this.getNative().sendMessage(message);
	}
}