package com.github.ravenlab.commander.command.platform.bukkit;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.sender.CommanderSender;

public class BukkitTypeResolver implements TypeResolver<Player, World> {

	@Override
	public Optional<Player> getPlayer(String name) {
		Player bukkitPlayer = Bukkit.getServer().getPlayer(name);
		if(bukkitPlayer == null) {
			return Optional.empty();
		}
		return Optional.of(bukkitPlayer);
	}

	@Override
	public Optional<Player> getPlayer(UUID uuid) {
		Player bukkitPlayer = Bukkit.getServer().getPlayer(uuid);
		if(bukkitPlayer == null) {
			return Optional.empty();
		}
		return Optional.of(bukkitPlayer);
	}

	@Override
	public Optional<World> getWorld(String name) {
		World bukkitWorld = Bukkit.getServer().getWorld(name);
		if(bukkitWorld == null) {
			return Optional.empty();
		}
		return Optional.of(bukkitWorld);
	}

	@Override
	public CommanderSender<?> getSender(Object nativeSender) {
		if(nativeSender instanceof Player) {
			Player player = (Player) nativeSender;
			return new BukkitCommanderPlayer(player);
		} else {
			CommandSender commandSender = (CommandSender) nativeSender;
			return new BukkitCommanderSender(commandSender);
		}
	}

	@Override
	public Class<Player> getPlayerClass() {
		return Player.class;
	}

	@Override
	public Class<World> getWorldClass() {
		return World.class;
	}
}