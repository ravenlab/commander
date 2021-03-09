package com.github.ravenlab.commander.transform;

import java.util.Optional;

import com.github.ravenlab.commander.resolver.TypeResolver;

public class PlayerTransformer<T> extends Transformer<T> {

	public PlayerTransformer(TypeResolver<?, ?> resolver) {
		super("player", resolver);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<T> transform(Class<T> clazz, String name) {
		return (Optional<T>) this.getResolver().get().getPlayer(name);
	}
}