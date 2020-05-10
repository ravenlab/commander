package com.github.ravenlab.commander.resolver.bukkit;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.github.ravenlab.commander.player.bukkit.BukkitCommanderPlayer;
import com.github.ravenlab.commander.resolver.TypeResolver;

public class BukkitTypeResolver implements TypeResolver<BukkitCommanderPlayer> {

	@Override
	public Optional<BukkitCommanderPlayer> getPlayer(String name) {
		Player bukkitPlayer = Bukkit.getServer().getPlayer(name);
		if(bukkitPlayer == null) {
			return Optional.empty();
		}
		
		BukkitCommanderPlayer commanderPlayer = new BukkitCommanderPlayer(bukkitPlayer);
		
		return Optional.of(commanderPlayer);

	}

	@Override
	public Optional<BukkitCommanderPlayer> getPlayer(UUID uuid) {
		Player bukkitPlayer = Bukkit.getServer().getPlayer(uuid);
		if(bukkitPlayer == null) {
			return Optional.empty();
		}
		
		BukkitCommanderPlayer commanderPlayer = new BukkitCommanderPlayer(bukkitPlayer);
		
		return Optional.of(commanderPlayer);
	}
}