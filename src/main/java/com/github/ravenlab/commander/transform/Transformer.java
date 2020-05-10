package com.github.ravenlab.commander.transform;

import java.util.Optional;

import com.github.ravenlab.commander.resolver.TypeResolver;

public abstract class Transformer<T> {

	private TypeResolver<T> resolver;
	
	public Transformer(TypeResolver<T> resolver) {
		this.resolver = resolver;
	}
	
	public abstract Optional<T> transform(String arg);
	
	public TypeResolver<T> getResolver() {
		return this.resolver;
	}
}