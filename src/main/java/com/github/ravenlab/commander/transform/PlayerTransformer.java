package com.github.ravenlab.commander.transform;

import java.util.Optional;

import com.github.ravenlab.commander.resolver.TypeResolver;

public class PlayerTransformer<T> extends Transformer<T> {

	public PlayerTransformer(TypeResolver<?, ?> resolver) {
		super(resolver);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<T> transform(String name) {
		return (Optional<T>) this.getResolver().get().getPlayer(name);
	}
}