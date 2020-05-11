package com.github.ravenlab.commander.transform;

import java.util.Optional;

import com.github.ravenlab.commander.resolver.TypeResolver;

public abstract class Transformer<T> {

	private TypeResolver resolver;
	
	public Transformer() {
		this(null);
	}
	
	public Transformer(TypeResolver resolver) {
		this.resolver = resolver;
	}
	
	public abstract Optional<T> transform(String arg);
	
	public TypeResolver getResolver() {
		return this.resolver;
	}
}