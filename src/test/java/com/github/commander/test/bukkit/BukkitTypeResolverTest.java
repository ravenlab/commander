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

import com.github.commander.test.bukkit.mock.BukkitMockFactory;
import com.github.commander.test.bukkit.mock.TestBukkitPlayer;
import com.github.commander.test.bukkit.mock.TestBukkitServer;
import com.github.commander.test.bukkit.mock.TestBukkitWorld;
import com.github.ravenlab.commander.player.CommanderPlayer;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.resolver.bukkit.BukkitTypeResolver;
import com.github.ravenlab.commander.world.CommanderWorld;

public class BukkitTypeResolverTest {

	private TestBukkitServer server;
	private TestBukkitPlayer player;
	private TestBukkitWorld world;
	
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
	public void getPlayerNonExistentNameTest() {
		TypeResolver resolver = new BukkitTypeResolver();
		Optional<CommanderPlayer<?>> playerOptional = resolver.getPlayer("doesnotexist");
		assertFalse(playerOptional.isPresent());
	}
	
	@Test
	public void getPlayerNameTest() {
		TypeResolver resolver = new BukkitTypeResolver();
		Optional<CommanderPlayer<?>> playerOptional = resolver.getPlayer("test");
		assertTrue(playerOptional.isPresent());
		assertTrue(playerOptional.get().getName().equals(this.player.getName()));
	}
	
	@Test
	public void getPlayerUUIDDoesNotExistTest() {
		TypeResolver resolver = new BukkitTypeResolver();
		Optional<CommanderPlayer<?>> playerOptional = resolver.getPlayer(UUID.randomUUID());
		assertFalse(playerOptional.isPresent());
	}
	
	@Test
	public void getPlayerUUIDTest() {
		TypeResolver resolver = new BukkitTypeResolver();
		UUID uuid = this.player.getUniqueId();
		Optional<CommanderPlayer<?>> playerOptional = resolver.getPlayer(uuid);
		assertTrue(playerOptional.isPresent());
		assertTrue(playerOptional.get().getUniqueId().equals(uuid));
	}
	
	@Test
	public void getWorldDoesNotExistTest() {
		TypeResolver resolver = new BukkitTypeResolver();
		Optional<CommanderWorld<?>> worldOptional = resolver.getWorld("doesnotexist");
		assertFalse(worldOptional.isPresent());
	}
	
	@Test
	public void getWorldTest() {
		TypeResolver resolver = new BukkitTypeResolver();
		Optional<CommanderWorld<?>> worldOptional = resolver.getWorld(this.world.getName());
		assertTrue(worldOptional.isPresent());
		assertTrue(worldOptional.get().getName().equals(this.world.getName()));
	}
}