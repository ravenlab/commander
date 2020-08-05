package com.github.commander.test.bukkit;

import static org.junit.Assert.assertFalse;
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
import com.github.commander.test.bukkit.mock.MockBukkitCommandSender;
import com.github.commander.test.bukkit.mock.MockBukkitPlayer;
import com.github.commander.test.bukkit.mock.MockBukkitServer;
import com.github.commander.test.bukkit.mock.MockBukkitWorld;
import com.github.ravenlab.commander.command.platform.bukkit.BukkitTypeResolver;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.sender.CommanderSender;

public class BukkitTypeResolverTest {

	private MockBukkitServer server;
	private MockBukkitPlayer player;
	private MockBukkitWorld world;
	private MockBukkitCommandSender sender;
	
	@Before
	public void bootstrapServer() {
		BukkitMockFactory factory = new BukkitMockFactory();
		this.server = factory.createServer();
		this.setServer(server);
		UUID uuid = UUID.randomUUID();
		String playerName = "test";
		this.player = factory.createPlayer(playerName, uuid);
		this.sender = factory.createSender("test");
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
		TypeResolver<Player, World> resolver = new BukkitTypeResolver();
		Optional<Player> playerOptional = resolver.getPlayer("doesnotexist");
		assertFalse(playerOptional.isPresent());
	}
	
	@Test
	public void getPlayerNameTest() {
		TypeResolver<Player, World> resolver = new BukkitTypeResolver();
		Optional<Player> playerOptional = resolver.getPlayer("test");
		assertTrue(playerOptional.isPresent());
		assertTrue(playerOptional.get().getName().equals(this.player.getName()));
	}
	
	@Test
	public void getPlayerUUIDDoesNotExistTest() {
		TypeResolver<Player, World> resolver = new BukkitTypeResolver();
		Optional<Player> playerOptional = resolver.getPlayer(UUID.randomUUID());
		assertFalse(playerOptional.isPresent());
	}
	
	@Test
	public void getPlayerUUIDTest() {
		TypeResolver<Player, World> resolver = new BukkitTypeResolver();
		UUID uuid = this.player.getUniqueId();
		Optional<Player> playerOptional = resolver.getPlayer(uuid);
		assertTrue(playerOptional.isPresent());
		assertTrue(playerOptional.get().getUniqueId().equals(uuid));
	}
	
	@Test
	public void getWorldDoesNotExistTest() {
		TypeResolver<Player, World> resolver = new BukkitTypeResolver();
		Optional<World> worldOptional = resolver.getWorld("doesnotexist");
		assertFalse(worldOptional.isPresent());
	}
	
	@Test
	public void getWorldTest() {
		TypeResolver<Player, World> resolver = new BukkitTypeResolver();
		Optional<World> worldOptional = resolver.getWorld(this.world.getName());
		assertTrue(worldOptional.isPresent());
		assertTrue(worldOptional.get().getName().equals(this.world.getName()));
	}
	
	@Test
	public void getSenderPlayerTest() {
		TypeResolver<Player, World> resolver = new BukkitTypeResolver();
		CommanderSender<?> sender = resolver.getSender(this.player);
		assertTrue(sender.getName().equals(this.player.getName()));
	}
	
	@Test
	public void getSenderOtherTest() {
		TypeResolver<Player, World> resolver = new BukkitTypeResolver();
		CommanderSender<?> sender = resolver.getSender(this.sender);
		assertTrue(sender.getName().equals(this.sender.getName()));
	}
}