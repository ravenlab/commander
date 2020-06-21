package com.github.ravenlab.commander.resolver;

import java.util.Optional;
import java.util.UUID;

import com.github.ravenlab.commander.sender.CommanderSender;

public interface TypeResolver<P, W> {

	public Optional<P> getPlayer(String name);
	public Optional<P> getPlayer(UUID uuid);
	public Optional<W> getWorld(String name);
	public CommanderSender<?> getSender(Object nativeSender);
	public Class<P> getPlayerClass();
	public Class<W> getWorldClass();
}