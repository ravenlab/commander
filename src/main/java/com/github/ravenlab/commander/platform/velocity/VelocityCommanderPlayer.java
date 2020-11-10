package com.github.ravenlab.commander.platform.velocity;

import java.util.UUID;

import com.github.ravenlab.commander.player.CommanderPlayer;
import com.velocitypowered.api.proxy.Player;

import net.kyori.text.Component;
import net.kyori.text.serializer.legacy.LegacyComponentSerializer;

public class VelocityCommanderPlayer extends CommanderPlayer<Player> {

	public VelocityCommanderPlayer(Player player) {
		super(player);
	}

	@Override
	public UUID getUniqueId() {
		return this.getNative().getUniqueId();
	}

	@Override
	public String getName() {
		return this.getNative().getUsername();
	}

	@Override
	public boolean hasPermission(String permission) {
		return this.getNative().hasPermission(permission);
	}

	@Override
	public void sendMessage(String message) {
		Component messageComponent = LegacyComponentSerializer
				.legacy()
				.deserialize(message);
		this.getNative().sendMessage(messageComponent);
	}
}