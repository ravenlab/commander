package com.github.ravenlab.commander.transform;

import java.util.Optional;

public class EnumTransformer<T extends Enum<T>> extends Transformer<T> {

	public EnumTransformer() {
		super("enum");
	}

	@Override
	public Optional<T> transform(Class<T> clazz, String arg) {
		for(T t : clazz.getEnumConstants()) {
			if(t.toString().equalsIgnoreCase(arg)) {
				return Optional.of(t);
			}
		}
		return Optional.empty();
	}
}