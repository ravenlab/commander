package com.github.ravenlab.commander.platform.velocity;

import java.util.Optional;
import java.util.UUID;

import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.sender.CommanderSender;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;

public class VelocityTypeResolver implements TypeResolver<Player, Object> {

	private ProxyServer server;
	
	public VelocityTypeResolver(ProxyServer server) {
		this.server = server;
	}
	
	@Override
	public Optional<Player> getPlayer(String name) {
		return this.server.getPlayer(name);
	}

	@Override
	public Optional<Player> getPlayer(UUID uuid) {
		return this.server.getPlayer(uuid);
	}

	@Override
	public Optional<Object> getWorld(String name) {
		return Optional.empty();
	}

	@Override
	public CommanderSender<?> getSender(Object nativeSender) {
		if(nativeSender instanceof Player) {
			Player player = (Player) nativeSender;
			return new VelocityCommanderPlayer(player);
		} else {
			CommandSource commandSender = (CommandSource) nativeSender;
			return new VelocityCommanderSender(commandSender);
		}
	}

	@Override
	public Class<Player> getPlayerClass() {
		return Player.class;
	}

	@Override
	public Optional<Class<Object>> getWorldClass() {
		return Optional.empty();
	}
}