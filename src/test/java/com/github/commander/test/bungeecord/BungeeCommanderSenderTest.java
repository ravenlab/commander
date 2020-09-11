package com.github.commander.test.bungeecord;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.commander.test.bungeecord.mock.BungeeMockFactory;
import com.github.commander.test.bungeecord.mock.MockBungeeCommandSender;
import com.github.ravenlab.commander.platform.bungeecord.BungeeCommanderSender;


public class BungeeCommanderSenderTest {
	
	private static BungeeMockFactory factory;
	
	@BeforeClass
	public static void initFactory() {
		factory = new BungeeMockFactory();
	}
	
	@Test
	public void getNameTest() {
		String name = "test";
		MockBungeeCommandSender sender = factory.createSender(name);
		BungeeCommanderSender commanderSender = new BungeeCommanderSender(sender);
		assertTrue(commanderSender.getName().equals("test")); 
	}
	
	
	@Test
	public void hasPermissionTest() {
		String name = "test";
		MockBungeeCommandSender sender = factory.createSender(name);
		sender.addPermission("test", true);
		BungeeCommanderSender commanderSender = new BungeeCommanderSender(sender);
		assertTrue(commanderSender.hasPermission("test")); 
	}
	
	@Test
	public void sendMessageTest() {
		String name = "test";
		MockBungeeCommandSender sender = factory.createSender(name);
		BungeeCommanderSender commanderSender = new BungeeCommanderSender(sender);
		commanderSender.sendMessage("test message");
		assertTrue(sender.getReceivedMessages().size() == 1);
		assertTrue(sender.getReceivedMessages().get(0).equals("test message"));
	}
}