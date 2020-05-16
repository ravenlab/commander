package com.github.commander.test.bukkit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.junit.Before;
import org.junit.Test;

import com.github.commander.test.bukkit.mock.TestBukkitPlayer;
import com.github.commander.test.bukkit.mock.TestBukkitServer;
import com.github.commander.test.bukkit.mock.TestBukkitWorld;
import com.github.ravenlab.commander.player.CommanderPlayer;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.resolver.bukkit.BukkitTypeResolver;
import com.github.ravenlab.commander.transform.PlayerTransformer;
import com.github.ravenlab.commander.transform.WorldTransformer;
import com.github.ravenlab.commander.world.CommanderWorld;

public class BukkitTransformerTest {

	private TestBukkitServer server;
	private TestBukkitPlayer player;
	private TestBukkitWorld world;
	
	@Before
	public void bootstrapServer() {
		this.server = new TestBukkitServer();
		this.setServer(server);
		UUID uuid = UUID.randomUUID();
		String playerName = "test";
		this.player = new TestBukkitPlayer(playerName, uuid);
		this.server.addPlayer(this.player);
		this.world = new TestBukkitWorld("test");
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
		TypeResolver resolver = new BukkitTypeResolver();
		PlayerTransformer transformer = new PlayerTransformer(resolver);
		Optional<CommanderPlayer<?>> playerOptional = transformer.transform(this.player.getName());
		assertTrue(playerOptional.isPresent());
	}
	
	@Test
	public void worldTransformerTest() {
		TypeResolver resolver = new BukkitTypeResolver();
		WorldTransformer transformer = new WorldTransformer(resolver);
		Optional<CommanderWorld<?>> worldOptional = transformer.transform(this.world.getName());
		assertTrue(worldOptional.isPresent());
	}
}