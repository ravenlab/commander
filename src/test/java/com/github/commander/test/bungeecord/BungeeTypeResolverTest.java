package com.github.commander.test.bungeecord;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.github.commander.test.bungeecord.mock.BungeeMockFactory;
import com.github.commander.test.bungeecord.mock.MockBungeeCommandSender;
import com.github.commander.test.bungeecord.mock.MockBungeePlayer;
import com.github.commander.test.bungeecord.mock.MockBungeeServer;
import com.github.ravenlab.commander.command.platform.bungeecord.BungeeTypeResolver;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.sender.CommanderSender;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeTypeResolverTest {

	private MockBungeeServer server;
	private MockBungeePlayer player;
	private MockBungeeCommandSender sender;
	
	@Before
	public void bootstrapServer() {
		BungeeMockFactory factory = new BungeeMockFactory();
		this.server = factory.createServer();
		this.setServer(server);
		UUID uuid = UUID.randomUUID();
		String playerName = "test";
		this.player = factory.createPlayer(playerName, uuid);
		this.sender = factory.createSender("test");
		this.server.addPlayer(this.player);
	}
	
	private void setServer(ProxyServer server) {
		try {
			Field serverField = ProxyServer.class.getDeclaredField("instance");
			serverField.setAccessible(true);
			serverField.set(null, server);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getPlayerNonExistentNameTest() {
		TypeResolver<ProxiedPlayer, Object> resolver = new BungeeTypeResolver();
		Optional<ProxiedPlayer> playerOptional = resolver.getPlayer("doesnotexist");
		assertFalse(playerOptional.isPresent());
	}
	
	@Test
	public void getPlayerNameTest() {
		TypeResolver<ProxiedPlayer, Object> resolver = new BungeeTypeResolver();
		Optional<ProxiedPlayer> playerOptional = resolver.getPlayer("test");
		assertTrue(playerOptional.isPresent());
		assertTrue(playerOptional.get().getName().equals(this.player.getName()));
	}
	
	@Test
	public void getPlayerUUIDDoesNotExistTest() {
		TypeResolver<ProxiedPlayer, Object> resolver = new BungeeTypeResolver();
		Optional<ProxiedPlayer> playerOptional = resolver.getPlayer(UUID.randomUUID());
		assertFalse(playerOptional.isPresent());
	}
	
	@Test
	public void getPlayerUUIDTest() {
		TypeResolver<ProxiedPlayer, Object> resolver = new BungeeTypeResolver();
		UUID uuid = this.player.getUniqueId();
		Optional<ProxiedPlayer> playerOptional = resolver.getPlayer(uuid);
		assertTrue(playerOptional.isPresent());
		assertTrue(playerOptional.get().getUniqueId().equals(uuid));
	}
	
	@Test
	public void getWorldTest() {
		TypeResolver<ProxiedPlayer, Object> resolver = new BungeeTypeResolver();
		Optional<Object> worldOptional = resolver.getWorld("test");
		assertFalse(worldOptional.isPresent());
	}
	
	@Test
	public void getSenderPlayerTest() {
		TypeResolver<ProxiedPlayer, Object> resolver = new BungeeTypeResolver();
		CommanderSender<?> sender = resolver.getSender(this.player);
		assertTrue(sender.getName().equals(this.player.getName()));
	}
	
	@Test
	public void getSenderOtherTest() {
		TypeResolver<ProxiedPlayer, Object> resolver = new BungeeTypeResolver();
		CommanderSender<?> sender = resolver.getSender(this.sender);
		assertTrue(sender.getName().equals(this.sender.getName()));
	}
}