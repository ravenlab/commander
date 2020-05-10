package com.github.ravenlab.commander.transform;

import java.util.Optional;

import com.github.ravenlab.commander.player.CommanderPlayer;
import com.github.ravenlab.commander.resolver.TypeResolver;

public class PlayerTransformer<T> extends Transformer<CommanderPlayer<T>> {

	public PlayerTransformer(TypeResolver<CommanderPlayer<T>> resolver) {
		super(resolver);
	}

	@Override
	public Optional<CommanderPlayer<T>> transform(String name) {
		return this.getResolver().getPlayer(name);
	}
}