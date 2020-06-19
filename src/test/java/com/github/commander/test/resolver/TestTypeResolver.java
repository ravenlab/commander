package com.github.commander.test.resolver;

import java.util.Optional;
import java.util.UUID;

import com.github.ravenlab.commander.player.CommanderPlayer;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.sender.CommanderSender;
import com.github.ravenlab.commander.world.CommanderWorld;

public class TestTypeResolver implements TypeResolver {

	@Override
	public Optional<CommanderPlayer<?>> getPlayer(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CommanderPlayer<?>> getPlayer(UUID uuid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<CommanderWorld<?>> getWorld(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CommanderSender<?> getSender(Object nativeSender) {
		// TODO Auto-generated method stub
		return null;
	}

}
