package com.github.ravenlab.commander.platform.velocity.util;

import com.velocitypowered.api.command.CommandSource;

import net.kyori.text.Component;
import net.kyori.text.serializer.legacy.LegacyComponentSerializer;

public final class MessageUtil {

	private static LegacyComponentSerializer SERIALIZER = LegacyComponentSerializer
			.legacy();
	
	private MessageUtil() {}
	
	public static void sendMessage(final CommandSource source, final String message) {
		Component serialized = SERIALIZER.deserialize(message);
		source.sendMessage(serialized);
	}
}