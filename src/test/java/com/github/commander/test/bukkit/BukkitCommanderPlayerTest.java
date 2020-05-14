package com.github.commander.test.bukkit;

import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import com.github.commander.test.bukkit.mock.TestBukkitPlayer;
import com.github.ravenlab.commander.player.bukkit.BukkitCommanderPlayer;

public class BukkitCommanderPlayerTest {

	@Test
	public void getNameTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		TestBukkitPlayer player = new TestBukkitPlayer(name, uuid);
		BukkitCommanderPlayer commanderPlayer = new BukkitCommanderPlayer(player);
		assertTrue(commanderPlayer.getName().equals("test")); 
	}
	
	@Test
	public void getUniqueIdTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		TestBukkitPlayer player = new TestBukkitPlayer(name, uuid);
		BukkitCommanderPlayer commanderPlayer = new BukkitCommanderPlayer(player);
		assertTrue(commanderPlayer.getUniqueId().equals(uuid)); 
	}
	
	@Test
	public void hasPermissionTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		TestBukkitPlayer player = new TestBukkitPlayer(name, uuid);
		player.addPermission("test", true);
		BukkitCommanderPlayer commanderPlayer = new BukkitCommanderPlayer(player);
		assertTrue(commanderPlayer.hasPermission("test")); 
	}
	
	@Test
	public void sendMessageTest() {
		String name = "test";
		UUID uuid = UUID.randomUUID();
		TestBukkitPlayer player = new TestBukkitPlayer(name, uuid);
		BukkitCommanderPlayer commanderPlayer = new BukkitCommanderPlayer(player);
		commanderPlayer.sendMessage("test message");
		assertTrue(player.getReceivedMessages().size() == 1);
		assertTrue(player.getReceivedMessages().get(0).equals("test message"));
	}
}