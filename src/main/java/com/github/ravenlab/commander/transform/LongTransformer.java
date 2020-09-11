package com.github.ravenlab.commander.transform;

import java.util.Optional;

public class LongTransformer extends Transformer<Long> {

	@Override
	public Optional<Long> transform(Class<Long> clazz, String arg) {
		try {
			Long value = Long.valueOf(arg);
			return Optional.of(value);
		} catch(NumberFormatException ex) {
			return Optional.empty();
		}
	}
}