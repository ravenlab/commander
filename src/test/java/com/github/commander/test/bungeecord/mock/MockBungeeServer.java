package com.github.commander.test.bungeecord.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;


public abstract class MockBungeeServer extends ProxyServer {

	private Logger logger;
	private Map<UUID, ProxiedPlayer> playerUUIDMap;
	private Map<String, ProxiedPlayer> playerNameMap;
	
	public MockBungeeServer() {
		this.logger = Logger.getLogger(this.getClass().getName());
		this.playerUUIDMap = new HashMap<>();
		this.playerNameMap = new HashMap<>();
	}
	
	@Override
	public Logger getLogger() {
		return this.logger;
	}
	
	@Override
	public ProxiedPlayer getPlayer(UUID uuid) {
		return this.playerUUIDMap.get(uuid);
	}
	
	@Override
	public ProxiedPlayer getPlayer(String name) {
		return this.playerNameMap.get(name);
	}
	
	public void addPlayer(ProxiedPlayer player) {
		this.playerUUIDMap.put(player.getUniqueId(), player);
		this.playerNameMap.put(player.getName(), player);
	}
}