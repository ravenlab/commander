package com.github.commander.test.bukkit;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.commander.test.bukkit.mock.TestBukkitCommandSender;
import com.github.ravenlab.commander.sender.bukkit.BukkitCommanderSender;


public class BukkitCommanderSenderTest {

	@Test
	public void getNameTest() {
		String name = "test";
		TestBukkitCommandSender sender = new TestBukkitCommandSender(name);
		BukkitCommanderSender commanderSender = new BukkitCommanderSender(sender);
		assertTrue(commanderSender.getName().equals("test")); 
	}
	
	
	@Test
	public void hasPermissionTest() {
		String name = "test";
		TestBukkitCommandSender sender = new TestBukkitCommandSender(name);
		sender.addPermission("test", true);
		BukkitCommanderSender commanderSender = new BukkitCommanderSender(sender);
		assertTrue(commanderSender.hasPermission("test")); 
	}
	
	@Test
	public void sendMessageTest() {
		String name = "test";
		TestBukkitCommandSender sender = new TestBukkitCommandSender(name);
		BukkitCommanderSender commanderSender = new BukkitCommanderSender(sender);
		commanderSender.sendMessage("test message");
		assertTrue(sender.getReceivedMessages().size() == 1);
		assertTrue(sender.getReceivedMessages().get(0).equals("test message"));
	}
}