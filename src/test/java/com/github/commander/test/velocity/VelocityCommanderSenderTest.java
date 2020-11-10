package com.github.commander.test.velocity;

import static org.junit.Assert.assertTrue;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.commander.test.velocity.mock.MockVelocityCommandSource;
import com.github.commander.test.velocity.mock.VelocityMockFactory;
import com.github.ravenlab.commander.platform.velocity.VelocityCommanderSender;


public class VelocityCommanderSenderTest {
	
	private static VelocityMockFactory factory;
	
	@BeforeClass
	public static void initFactory() {
		factory = new VelocityMockFactory();
	}
	
	@Test
	public void getNameTest() {
		String name = "test";
		MockVelocityCommandSource sender = factory.createSender(name);
		VelocityCommanderSender commanderSender = new VelocityCommanderSender(sender);
		assertTrue(commanderSender.getName().equals("test")); 
	}
	
	
	@Test
	public void hasPermissionTest() {
		String name = "test";
		MockVelocityCommandSource sender = factory.createSender(name);
		sender.addPermission("test", true);
		VelocityCommanderSender commanderSender = new VelocityCommanderSender(sender);
		assertTrue(commanderSender.hasPermission("test")); 
	}
	
	@Test
	public void sendMessageTest() {
		String name = "test";
		MockVelocityCommandSource sender = factory.createSender(name);
		VelocityCommanderSender commanderSender = new VelocityCommanderSender(sender);
		commanderSender.sendMessage("test message");
		assertTrue(sender.getReceivedMessages().size() == 1);
		assertTrue(sender.getReceivedMessages().get(0).equals("test message"));
	}
}