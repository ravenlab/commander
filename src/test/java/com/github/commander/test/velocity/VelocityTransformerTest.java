package com.github.commander.test.velocity;

import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.github.commander.test.velocity.mock.MockVelocityPlayer;
import com.github.commander.test.velocity.mock.MockVelocityServer;
import com.github.commander.test.velocity.mock.VelocityMockFactory;
import com.github.ravenlab.commander.platform.velocity.VelocityTypeResolver;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.transform.PlayerTransformer;
import com.velocitypowered.api.proxy.Player;

public class VelocityTransformerTest {

	private MockVelocityServer server;
	private MockVelocityPlayer player;
	
	@Before
	public void bootstrapServer() {
		VelocityMockFactory factory = new VelocityMockFactory();
		this.server = factory.createServer();
		UUID uuid = UUID.randomUUID();
		String playerName = "test";
		this.player = factory.createPlayer(playerName, uuid);
		this.server.addPlayer(this.player);
	}
	
	@Test
	public void playerTransformerTest() {
		TypeResolver<Player, Object> resolver = new VelocityTypeResolver(this.server);
		PlayerTransformer<Player> transformer = new PlayerTransformer<>(resolver);
		Optional<Player> playerOptional = transformer.transform(Player.class, this.player.getName());
		assertTrue(playerOptional.isPresent());
	}
}