package com.github.ravenlab.commander.transform;

import java.util.Optional;
import java.util.UUID;

public class UUIDTransformer extends Transformer<UUID> {

	public UUIDTransformer() {
		super("uuid");
	}

	@Override
	public Optional<UUID> transform(Class<UUID> clazz, String arg) {
		try {
			UUID value = UUID.fromString(arg);
			return Optional.of(value);
		} catch(IllegalArgumentException  ex) {
			return Optional.empty();
		}
	}
}