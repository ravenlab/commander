package com.github.ravenlab.commander.platform.bungeecord;

import java.util.Optional;
import java.util.UUID;

import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.sender.CommanderSender;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeTypeResolver implements TypeResolver<ProxiedPlayer, Object> {

	@Override
	public Optional<ProxiedPlayer> getPlayer(String name) {
		return Optional.ofNullable(ProxyServer.getInstance().getPlayer(name));
	}

	@Override
	public Optional<ProxiedPlayer> getPlayer(UUID uuid) {
		return Optional.ofNullable(ProxyServer.getInstance().getPlayer(uuid));
	}

	@Override
	public Optional<Object> getWorld(String name) {
		return Optional.empty();
	}

	@Override
	public CommanderSender<?> getSender(Object nativeSender) {
		if(nativeSender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) nativeSender;
			return new BungeeCommanderPlayer(player);
		} else {
			CommandSender commandSender = (CommandSender) nativeSender;
			return new BungeeCommanderSender(commandSender);
		}
	}

	@Override
	public Class<ProxiedPlayer> getPlayerClass() {
		return ProxiedPlayer.class;
	}

	@Override
	public Optional<Class<Object>> getWorldClass() {
		return Optional.empty();
	}
}