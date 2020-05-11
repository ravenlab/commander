package com.github.ravenlab.commander.resolver.bukkit;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.github.ravenlab.commander.player.CommanderPlayer;
import com.github.ravenlab.commander.player.bukkit.BukkitCommanderPlayer;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.world.CommanderWorld;
import com.github.ravenlab.commander.world.bukkit.BukkitCommanderWorld;

public class BukkitTypeResolver implements TypeResolver {

	@Override
	public Optional<CommanderPlayer<?>> getPlayer(String name) {
		Player bukkitPlayer = Bukkit.getServer().getPlayer(name);
		if(bukkitPlayer == null) {
			return Optional.empty();
		}
		
		CommanderPlayer<?> commanderPlayer = new BukkitCommanderPlayer(bukkitPlayer);
		return Optional.of(commanderPlayer);

	}

	@Override
	public Optional<CommanderPlayer<?>> getPlayer(UUID uuid) {
		Player bukkitPlayer = Bukkit.getServer().getPlayer(uuid);
		if(bukkitPlayer == null) {
			return Optional.empty();
		}
		
		CommanderPlayer<?> commanderPlayer = new BukkitCommanderPlayer(bukkitPlayer);
		return Optional.of(commanderPlayer);
	}

	@Override
	public Optional<CommanderWorld<?>> getWorld(String name) {
		World bukkitWorld = Bukkit.getServer().getWorld(name);
		if(bukkitWorld == null) {
			return Optional.empty();
		}
		
		CommanderWorld<?> commanderWorld = new BukkitCommanderWorld(bukkitWorld);
		return Optional.of(commanderWorld);
	}
}