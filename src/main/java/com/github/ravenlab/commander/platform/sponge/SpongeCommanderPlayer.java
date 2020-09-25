package com.github.ravenlab.commander.platform.sponge;

import java.util.UUID;

import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;

import com.github.ravenlab.commander.player.CommanderPlayer;

public class SpongeCommanderPlayer extends CommanderPlayer<Player> {

	public SpongeCommanderPlayer(Player player) {
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
		this.getNative().sendMessage(Text.of(message));
	}
}