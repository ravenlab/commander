package com.github.ravenlab.commander.resolver;

import java.util.Optional;
import java.util.UUID;

public interface TypeResolver<T> {

	public Optional<T> getPlayer(String name);
	public Optional<T> getPlayer(UUID uuid);
	
}