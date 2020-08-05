package com.github.ravenlab.commander.command.platform.bukkit;

import java.util.UUID;

import org.bukkit.entity.Player;

import com.github.ravenlab.commander.player.CommanderPlayer;

public class BukkitCommanderPlayer extends CommanderPlayer<Player> {

	public BukkitCommanderPlayer(Player player) {
		super(player);
	}

	@Override
	public UUID getUniqueId() {
		return this.getNative().getUniqueId();
	}

	@Override
	public String getName() {
		return this.getNative().getName();
	}

	@Override
	public boolean hasPermission(String permission) {
		return this.getNative().hasPermission(permission);
	}

	@Override
	public void sendMessage(String message) {
		this.getNative().sendMessage(message);
	}
}