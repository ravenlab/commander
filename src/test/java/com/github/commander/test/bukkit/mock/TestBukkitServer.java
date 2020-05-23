package com.github.commander.test.bukkit.mock;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.entity.Player;

public abstract class TestBukkitServer implements Server {

	private CommandMap commandMap;
	private Logger logger;
	private Map<UUID, Player> playerUUIDMap;
	private Map<String, Player> playerNameMap;
	private Map<String, World> worldMap;
	
	public TestBukkitServer() {
		this.commandMap = new SimpleCommandMap(this);
		this.logger = Logger.getLogger(this.getClass().getName());
		this.playerUUIDMap = new HashMap<>();
		this.playerNameMap = new HashMap<>();
		this.worldMap = new HashMap<>();
	}
	
	@Override
	public Logger getLogger() {
		return this.logger;
	}
	
	@Override
	public Player getPlayer(UUID uuid) {
		return this.playerUUIDMap.get(uuid);
	}
	
	@Override
	public Player getPlayer(String name) {
		return this.playerNameMap.get(name);
	}
	
	public void addPlayer(Player player) {
		this.playerUUIDMap.put(player.getUniqueId(), player);
		this.playerNameMap.put(player.getName(), player);
	}
	
	@Override
	public World getWorld(String name) {
		return this.worldMap.get(name);
	}
	
	public void addWorld(World world) {
		this.worldMap.put(world.getName(), world);
	}
	
	public CommandMap getCommandMap() {
		return this.commandMap;
	}
}