package com.github.ravenlab.commander.transform;

import java.util.Optional;

public class IntegerTransformer extends Transformer<Integer> {

	public IntegerTransformer() {
		super("integer");
	}

	@Override
	public Optional<Integer> transform(Class<Integer> clazz, String arg) {
		try {
			int value = Integer.valueOf(arg);
			return Optional.of(value);
		} catch(NumberFormatException ex) {
			return Optional.empty();
		}
	}
}