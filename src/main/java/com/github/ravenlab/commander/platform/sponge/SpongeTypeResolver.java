package com.github.ravenlab.commander.platform.sponge;

import java.util.Optional;
import java.util.UUID;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.world.World;

import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.sender.CommanderSender;

public class SpongeTypeResolver implements TypeResolver<Player, World> {

	@Override
	public Optional<Player> getPlayer(String name) {
		return Sponge.getServer().getPlayer(name);
	}

	@Override
	public Optional<Player> getPlayer(UUID uuid) {
		return Sponge.getServer().getPlayer(uuid);
	}

	@Override
	public Optional<World> getWorld(String name) {
		return Sponge.getServer().getWorld(name);
	}

	@Override
	public CommanderSender<?> getSender(Object nativeSender) {
		if(nativeSender instanceof Player) {
			Player player = (Player) nativeSender;
			return new SpongeCommanderPlayer(player);
		} else {
			CommandSource commandSender = (CommandSource) nativeSender;
			return new SpongeCommanderSender(commandSender);
		}
	}

	@Override
	public Class<Player> getPlayerClass() {
		return Player.class;
	}

	@Override
	public Optional<Class<World>> getWorldClass() {
		return Optional.of(World.class);
	}
}