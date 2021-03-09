package com.github.ravenlab.commander.transform;

import java.util.Optional;

public class FloatTransformer extends Transformer<Float> {

	public FloatTransformer() {
		super("float");
	}

	@Override
	public Optional<Float> transform(Class<Float> clazz, String arg) {
		try {
			Float value = Float.parseFloat(arg);
			return Optional.of(value);
		} catch(NumberFormatException ex) {
			return Optional.empty();
		}
	}
}