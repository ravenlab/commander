package com.github.ravenlab.commander.transform;

import java.util.Optional;

import com.github.ravenlab.commander.resolver.TypeResolver;

public abstract class Transformer<T> {

	private String name;
	private TypeResolver<?, ?> resolver;
	public Transformer(String name) {
		this(name, null);
	}
	
	public Transformer(String name, TypeResolver<?, ?> resolver) {
		this.resolver = resolver;
	}

	public String getName() {
		return this.name;
	}

	public Optional<TypeResolver<?, ?>> getResolver() {
		if(this.resolver == null) {
			return Optional.empty();
		}

		return Optional.of(this.resolver);
	}

	public abstract Optional<T> transform(Class<T> clazz, String arg);
}