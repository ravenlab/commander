package com.github.commander.test.bungeecord;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.BeforeClass;
import org.junit.Test;

import com.github.commander.test.bungeecord.mock.BungeeMockFactory;
import com.github.commander.test.bungeecord.mock.MockBungeeCommandSender;
import com.github.commander.test.bungeecord.mock.MockBungeePlayer;
import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.ravenlab.commander.platform.bungeecord.BungeeCommandWrapper;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class BungeeCommandWrapperTest {

	private static BungeeMockFactory factory;
	
	@BeforeClass
	public static void initFactory() {
		factory = new BungeeMockFactory();
	}
	
	@Test
	public void wrapperExecutePlayerNoPermissionTest() {
		ParentCommand<CommandSender> command = new ParentCommand<>();
		Command wrapper = new BungeeCommandWrapper(command);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockBungeePlayer player = factory.createPlayer(name, uuid);
		String[] args = new String[0];
		wrapper.execute(player, args);
		assertFalse(command.hasRan());
	}
	
	@Test
	public void wrapperExecutePlayerHasPermissionTest() {
		ParentCommand<CommandSender> command = new ParentCommand<>();
		Command wrapper = new BungeeCommandWrapper(command);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockBungeePlayer player = factory.createPlayer(name, uuid);
		player.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(player,  args);
		assertTrue(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteSenderNoPermissionTest() {
		ParentCommand<CommandSender> command = new ParentCommand<>();
		Command wrapper = new BungeeCommandWrapper(command);
		String name = "test";
		MockBungeeCommandSender sender = factory.createSender(name);
		String[] args = new String[0];
		wrapper.execute(sender, args);
		assertFalse(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteSenderHasPermissionTest() {
		ParentCommand<CommandSender> command = new ParentCommand<>();
		Command wrapper = new BungeeCommandWrapper(command);
		String name = "test";
		MockBungeeCommandSender sender = factory.createSender(name);
		sender.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(sender, args);
		assertTrue(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteCommandNoPermissionTest() {
		ChildCommand<CommandSender> command = new ChildCommand<>();
		Command wrapper = new BungeeCommandWrapper(command);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockBungeePlayer player = factory.createPlayer(name, uuid);
		player.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(player, args);
		assertTrue(command.hasRan());
	}
}