package com.github.ravenlab.commander.transform;

import java.util.Optional;

public class DoubleTransformer extends Transformer<Double> {

	@Override
	public Optional<Double> transform(String arg) {
		try {
			double value = Double.parseDouble(arg);
			return Optional.of(value);
		} catch(NumberFormatException ex) {
			return Optional.empty();
		}
	}
}