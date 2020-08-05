package com.github.commander.test.bukkit;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.junit.Before;
import org.junit.Test;

import com.github.commander.test.bukkit.mock.BukkitMockFactory;
import com.github.commander.test.bukkit.mock.MockBukkitPlayer;
import com.github.commander.test.bukkit.mock.MockBukkitServer;
import com.github.commander.test.bukkit.mock.MockBukkitWorld;
import com.github.ravenlab.commander.command.platform.bukkit.BukkitTypeResolver;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.transform.PlayerTransformer;
import com.github.ravenlab.commander.transform.WorldTransformer;

public class BukkitTransformerTest {

	private MockBukkitServer server;
	private MockBukkitPlayer player;
	private MockBukkitWorld world;
	
	@Before
	public void bootstrapServer() {
		BukkitMockFactory factory = new BukkitMockFactory();
		this.server = factory.createServer();
		this.setServer(server);
		UUID uuid = UUID.randomUUID();
		String playerName = "test";
		this.player = factory.createPlayer(playerName, uuid);
		this.server.addPlayer(this.player);
		this.world = factory.createWorld("test");
		this.server.addWorld(this.world);
	}
	
	private void setServer(Server server) {
		try {
			Field serverField = Bukkit.class.getDeclaredField("server");
			serverField.setAccessible(true);
			serverField.set(null, server);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void playerTransformerTest() {
		TypeResolver<Player, World> resolver = new BukkitTypeResolver();
		PlayerTransformer<Player> transformer = new PlayerTransformer<>(resolver);
		Optional<Player> playerOptional = transformer.transform(this.player.getName());
		assertTrue(playerOptional.isPresent());
	}
	
	@Test
	public void worldTransformerTest() {
		TypeResolver<Player, World> resolver = new BukkitTypeResolver();
		WorldTransformer<World> transformer = new WorldTransformer<>(resolver);
		Optional<World> worldOptional = transformer.transform(this.world.getName());
		assertTrue(worldOptional.isPresent());
	}
}