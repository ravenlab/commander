package com.github.commander.test.resolver;

import java.util.Optional;
import java.util.UUID;

import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.sender.CommanderSender;

public class TestTypeResolver implements TypeResolver<Object, Object> {

	@Override
	public CommanderSender<?> getSender(Object nativeSender) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<Object> getPlayerClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Class<Object> getWorldClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Object> getPlayer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Object> getPlayer(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Object> getWorld(String name) {
		// TODO Auto-generated method stub
		return null;
	}

}
