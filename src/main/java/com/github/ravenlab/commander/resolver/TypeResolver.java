package com.github.ravenlab.commander.resolver;

import java.util.Optional;
import java.util.UUID;

import com.github.ravenlab.commander.player.CommanderPlayer;
import com.github.ravenlab.commander.world.CommanderWorld;

public interface TypeResolver {

	public Optional<CommanderPlayer<?>> getPlayer(String name);
	public Optional<CommanderPlayer<?>> getPlayer(UUID uuid);
	public Optional<CommanderWorld<?>> getWorld(String name);
}