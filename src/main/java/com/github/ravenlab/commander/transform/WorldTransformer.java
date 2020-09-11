package com.github.ravenlab.commander.transform;

import java.util.Optional;

import com.github.ravenlab.commander.resolver.TypeResolver;

public class WorldTransformer<T> extends Transformer<T> {

	public WorldTransformer(TypeResolver<?, ?> resolver) {
		super(resolver);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<T> transform(Class<T> clazz, String arg) {
		return (Optional<T>) this.getResolver().get().getWorld(arg);
	}
}