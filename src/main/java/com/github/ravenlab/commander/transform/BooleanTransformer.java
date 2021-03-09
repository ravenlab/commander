package com.github.ravenlab.commander.transform;

import java.util.Optional;

public class BooleanTransformer extends Transformer<Boolean> {

	public BooleanTransformer() {
		super("boolean");
	}

	@Override
	public Optional<Boolean> transform(Class<Boolean> clazz, String arg) {
		String lowerArg = arg.toLowerCase();
		if(lowerArg.equals("true") || lowerArg.equals("false")) {
			return Optional.of(Boolean.parseBoolean(lowerArg));
		}
		return Optional.empty();
	}
}