package com.github.commander.test.velocity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;
import java.util.UUID;

import org.junit.Before;
import org.junit.Test;

import com.github.commander.test.velocity.mock.MockVelocityCommandSource;
import com.github.commander.test.velocity.mock.MockVelocityPlayer;
import com.github.commander.test.velocity.mock.MockVelocityServer;
import com.github.commander.test.velocity.mock.VelocityMockFactory;
import com.github.ravenlab.commander.platform.velocity.VelocityTypeResolver;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.sender.CommanderSender;
import com.velocitypowered.api.proxy.Player;


public class VelocityTypeResolverTest {

	private MockVelocityServer server;
	private MockVelocityPlayer player;
	private MockVelocityCommandSource sender;
	
	@Before
	public void bootstrapServer() {
		VelocityMockFactory factory = new VelocityMockFactory();
		this.server = factory.createServer();
		UUID uuid = UUID.randomUUID();
		String playerName = "test";
		this.player = factory.createPlayer(playerName, uuid);
		this.sender = factory.createSender("test");
		this.server.addPlayer(this.player);
	}
	
	@Test
	public void getPlayerNonExistentNameTest() {
		TypeResolver<Player, Object> resolver = new VelocityTypeResolver(this.server);
		Optional<Player> playerOptional = resolver.getPlayer("doesnotexist");
		assertFalse(playerOptional.isPresent());
	}
	
	@Test
	public void getPlayerNameTest() {
		TypeResolver<Player, Object> resolver = new VelocityTypeResolver(this.server);
		Optional<Player> playerOptional = resolver.getPlayer("test");
		assertTrue(playerOptional.isPresent());
		assertTrue(playerOptional.get().getUsername().equals(this.player.getName()));
	}
	
	@Test
	public void getPlayerUUIDDoesNotExistTest() {
		TypeResolver<Player, Object> resolver = new VelocityTypeResolver(this.server);
		Optional<Player> playerOptional = resolver.getPlayer(UUID.randomUUID());
		assertFalse(playerOptional.isPresent());
	}
	
	@Test
	public void getPlayerUUIDTest() {
		TypeResolver<Player, Object> resolver = new VelocityTypeResolver(this.server);
		UUID uuid = this.player.getUniqueId();
		Optional<Player> playerOptional = resolver.getPlayer(uuid);
		assertTrue(playerOptional.isPresent());
		assertTrue(playerOptional.get().getUniqueId().equals(uuid));
	}
	
	@Test
	public void getWorldTest() {
		TypeResolver<Player, Object> resolver = new VelocityTypeResolver(this.server);
		Optional<Object> worldOptional = resolver.getWorld("test");
		assertFalse(worldOptional.isPresent());
	}
	
	@Test
	public void getSenderPlayerTest() {
		TypeResolver<Player, Object> resolver = new VelocityTypeResolver(this.server);
		CommanderSender<?> sender = resolver.getSender(this.player);
		assertTrue(sender.getName().equals(this.player.getName()));
	}
	
	@Test
	public void getSenderOtherTest() {
		TypeResolver<Player, Object> resolver = new VelocityTypeResolver(this.server);
		CommanderSender<?> sender = resolver.getSender(this.sender);
		assertTrue(sender.getName().equals(this.sender.getName()));
	}
}