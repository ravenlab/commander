package com.github.commander.test.velocity;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.commander.test.velocity.mock.MockVelocityPlayer;
import com.github.commander.test.velocity.mock.VelocityMockFactory;
import com.github.ravenlab.commander.platform.velocity.VelocityCommanderPlayer;

public class VelocityCommanderPlayerTest {

	private static VelocityMockFactory factory;
	
	@BeforeClass
	public static void initFactory() {
		factory = new VelocityMockFactory();
	}
	
	@Test
	public void getNameTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockVelocityPlayer player = factory.createPlayer(name, uuid);
		VelocityCommanderPlayer commanderPlayer = new VelocityCommanderPlayer(player);
		assertTrue(commanderPlayer.getName().equals("test")); 
	}
	
	@Test
	public void getUniqueIdTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockVelocityPlayer player = factory.createPlayer(name, uuid);
		VelocityCommanderPlayer commanderPlayer = new VelocityCommanderPlayer(player);
		assertTrue(commanderPlayer.getUniqueId().equals(uuid)); 
	}
	
	@Test
	public void hasPermissionTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockVelocityPlayer player = factory.createPlayer(name, uuid);
		player.addPermission("test", true);
		VelocityCommanderPlayer commanderPlayer = new VelocityCommanderPlayer(player);
		assertTrue(commanderPlayer.hasPermission("test")); 
	}
	
	@Test
	public void sendMessageTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockVelocityPlayer player = factory.createPlayer(name, uuid);
		VelocityCommanderPlayer commanderPlayer = new VelocityCommanderPlayer(player);
		commanderPlayer.sendMessage("test message");
		assertTrue(player.getReceivedMessages().size() == 1);
		assertTrue(player.getReceivedMessages().get(0).equals("test message"));
	}
}