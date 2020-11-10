package com.github.commander.test.velocity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.commander.test.velocity.mock.MockVelocityCommandSource;
import com.github.commander.test.velocity.mock.MockVelocityPlayer;
import com.github.commander.test.velocity.mock.VelocityMockFactory;
import com.github.ravenlab.commander.platform.velocity.VelocityCommandWrapper;
import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;

public class VelocityCommandWrapperTest {

	private static VelocityMockFactory factory;
	private static ProxyServer server;
	
	@BeforeClass
	public static void initFactory() {
		factory = new VelocityMockFactory();
		server = factory.createServer();
	}
	
	@Test
	public void wrapperExecutePlayerNoPermissionTest() {
		ParentCommand<CommandSource> command = new ParentCommand<>();
		Command wrapper = new VelocityCommandWrapper(command, server);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockVelocityPlayer player = factory.createPlayer(name, uuid);
		String[] args = new String[0];
		wrapper.execute(player, args);
		assertFalse(command.hasRan());
	}
	
	@Test
	public void wrapperExecutePlayerHasPermissionTest() {
		ParentCommand<CommandSource> command = new ParentCommand<>();
		Command wrapper = new VelocityCommandWrapper(command, server);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockVelocityPlayer player = factory.createPlayer(name, uuid);
		player.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(player,  args);
		assertTrue(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteSenderNoPermissionTest() {
		ParentCommand<CommandSource> command = new ParentCommand<>();
		Command wrapper = new VelocityCommandWrapper(command, server);
		String name = "test";
		MockVelocityCommandSource sender = factory.createSender(name);
		String[] args = new String[0];
		wrapper.execute(sender, args);
		assertFalse(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteSenderHasPermissionTest() {
		ParentCommand<CommandSource> command = new ParentCommand<>();
		Command wrapper = new VelocityCommandWrapper(command, server);
		String name = "test";
		MockVelocityCommandSource sender = factory.createSender(name);
		sender.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(sender, args);
		assertTrue(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteCommandNoPermissionTest() {
		ChildCommand<CommandSource> command = new ChildCommand<>();
		Command wrapper = new VelocityCommandWrapper(command, server);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockVelocityPlayer player = factory.createPlayer(name, uuid);
		player.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(player, args);
		assertTrue(command.hasRan());
	}
}