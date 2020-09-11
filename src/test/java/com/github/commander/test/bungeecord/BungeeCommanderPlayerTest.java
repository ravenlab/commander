package com.github.commander.test.bungeecord;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.commander.test.bungeecord.mock.BungeeMockFactory;
import com.github.commander.test.bungeecord.mock.MockBungeePlayer;
import com.github.ravenlab.commander.platform.bungeecord.BungeeCommanderPlayer;

public class BungeeCommanderPlayerTest {

	private static BungeeMockFactory factory;
	
	@BeforeClass
	public static void initFactory() {
		factory = new BungeeMockFactory();
	}
	
	@Test
	public void getNameTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockBungeePlayer player = factory.createPlayer(name, uuid);
		BungeeCommanderPlayer commanderPlayer = new BungeeCommanderPlayer(player);
		assertTrue(commanderPlayer.getName().equals("test")); 
	}
	
	@Test
	public void getUniqueIdTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockBungeePlayer player = factory.createPlayer(name, uuid);
		BungeeCommanderPlayer commanderPlayer = new BungeeCommanderPlayer(player);
		assertTrue(commanderPlayer.getUniqueId().equals(uuid)); 
	}
	
	@Test
	public void hasPermissionTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockBungeePlayer player = factory.createPlayer(name, uuid);
		player.addPermission("test", true);
		BungeeCommanderPlayer commanderPlayer = new BungeeCommanderPlayer(player);
		assertTrue(commanderPlayer.hasPermission("test")); 
	}
	
	@Test
	public void sendMessageTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockBungeePlayer player = factory.createPlayer(name, uuid);
		BungeeCommanderPlayer commanderPlayer = new BungeeCommanderPlayer(player);
		commanderPlayer.sendMessage("test message");
		assertTrue(player.getReceivedMessages().size() == 1);
		assertTrue(player.getReceivedMessages().get(0).equals("test message"));
	}
}