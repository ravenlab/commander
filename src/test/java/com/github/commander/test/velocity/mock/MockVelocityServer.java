package com.github.commander.test.velocity.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

import com.velocitypowered.api.command.CommandManager;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;


public abstract class MockVelocityServer implements ProxyServer {

	private Logger logger;
	private Map<UUID, Player> playerUUIDMap;
	private Map<String, Player> playerNameMap;
	private CommandManager commandManager;
	
	public MockVelocityServer() {
		this.logger = Logger.getLogger(this.getClass().getName());
		this.playerUUIDMap = new HashMap<>();
		this.playerNameMap = new HashMap<>();
		this.commandManager = new VelocityMockFactory()
				.createCommandManager();
	}
	
	public Logger getLogger() {
		return this.logger;
	}
	
	@Override
	public Optional<Player> getPlayer(UUID uuid) {
		return Optional.ofNullable(this.playerUUIDMap.get(uuid));
	}
	
	@Override
	public Optional<Player> getPlayer(String name) {
		return Optional.ofNullable(this.playerNameMap.get(name));
	}
	
	@Override
	public CommandManager getCommandManager() {
		return this.commandManager;
	}
	
	public void addPlayer(Player player) {
		this.playerUUIDMap.put(player.getUniqueId(), player);
		this.playerNameMap.put(player.getUsername(), player);
	}
}