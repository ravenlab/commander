package com.github.ravenlab.commander.transform;

import java.util.Optional;

import com.github.ravenlab.commander.player.CommanderPlayer;
import com.github.ravenlab.commander.resolver.TypeResolver;

public class PlayerTransformer extends Transformer<CommanderPlayer<?>> {

	public PlayerTransformer(TypeResolver resolver) {
		super(resolver);
	}

	@Override
	public Optional<CommanderPlayer<?>> transform(String name) {
		return this.getResolver().get().getPlayer(name);
	}
}