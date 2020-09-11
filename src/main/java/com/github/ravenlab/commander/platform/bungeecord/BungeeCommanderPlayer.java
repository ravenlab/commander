package com.github.ravenlab.commander.platform.bungeecord;

import java.util.UUID;

import com.github.ravenlab.commander.player.CommanderPlayer;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeCommanderPlayer extends CommanderPlayer<ProxiedPlayer> {

	public BungeeCommanderPlayer(ProxiedPlayer player) {
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

	@SuppressWarnings("deprecation")
	@Override
	public void sendMessage(String message) {
		this.getNative().sendMessage(message);
	}
}