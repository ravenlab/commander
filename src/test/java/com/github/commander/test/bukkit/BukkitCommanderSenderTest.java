package com.github.commander.test.bukkit;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.commander.test.bukkit.mock.BukkitMockFactory;
import com.github.commander.test.bukkit.mock.MockBukkitCommandSender;
import com.github.ravenlab.commander.platform.bukkit.BukkitCommanderSender;


public class BukkitCommanderSenderTest {
	
	private static BukkitMockFactory factory;
	
	@BeforeClass
	public static void initFactory() {
		factory = new BukkitMockFactory();
	}
	
	@Test
	public void getNameTest() {
		String name = "test";
		MockBukkitCommandSender sender = factory.createSender(name);
		BukkitCommanderSender commanderSender = new BukkitCommanderSender(sender);
		assertTrue(commanderSender.getName().equals("test")); 
	}
	
	
	@Test
	public void hasPermissionTest() {
		String name = "test";
		MockBukkitCommandSender sender = factory.createSender(name);
		sender.addPermission("test", true);
		BukkitCommanderSender commanderSender = new BukkitCommanderSender(sender);
		assertTrue(commanderSender.hasPermission("test")); 
	}
	
	@Test
	public void sendMessageTest() {
		String name = "test";
		MockBukkitCommandSender sender = factory.createSender(name);
		BukkitCommanderSender commanderSender = new BukkitCommanderSender(sender);
		commanderSender.sendMessage("test message");
		assertTrue(sender.getReceivedMessages().size() == 1);
		assertTrue(sender.getReceivedMessages().get(0).equals("test message"));
	}
}