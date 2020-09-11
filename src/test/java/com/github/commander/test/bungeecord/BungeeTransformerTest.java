package com.github.commander.test.bungeecord;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.github.commander.test.bungeecord.mock.BungeeMockFactory;
import com.github.commander.test.bungeecord.mock.MockBungeePlayer;
import com.github.commander.test.bungeecord.mock.MockBungeeServer;
import com.github.ravenlab.commander.platform.bungeecord.BungeeTypeResolver;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.transform.PlayerTransformer;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class BungeeTransformerTest {

	private MockBungeeServer server;
	private MockBungeePlayer player;
	
	@Before
	public void bootstrapServer() {
		BungeeMockFactory factory = new BungeeMockFactory();
		this.server = factory.createServer();
		this.setServer(server);
		UUID uuid = UUID.randomUUID();
		String playerName = "test";
		this.player = factory.createPlayer(playerName, uuid);
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
	public void playerTransformerTest() {
		TypeResolver<ProxiedPlayer, Object> resolver = new BungeeTypeResolver();
		PlayerTransformer<ProxiedPlayer> transformer = new PlayerTransformer<>(resolver);
		Optional<ProxiedPlayer> playerOptional = transformer.transform(ProxiedPlayer.class, this.player.getName());
		assertTrue(playerOptional.isPresent());
	}
}