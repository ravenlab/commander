package com.github.ravenlab.commander.sender.bukkit;

import org.bukkit.command.CommandSender;

import com.github.ravenlab.commander.sender.CommanderSender;

public class BukkitCommanderSender extends CommanderSender<CommandSender> {

	public BukkitCommanderSender(CommandSender sender) {
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
}