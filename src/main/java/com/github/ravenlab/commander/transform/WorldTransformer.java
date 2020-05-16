package com.github.ravenlab.commander.transform;

import java.util.Optional;

import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.world.CommanderWorld;

public class WorldTransformer extends Transformer<CommanderWorld<?>> {

	public WorldTransformer(TypeResolver resolver) {
		super(resolver);
	}

	@Override
	public Optional<CommanderWorld<?>> transform(String arg) {
		return this.getResolver().get().getWorld(arg);
	}
}