package com.github.commander.test.bukkit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.commander.test.bukkit.mock.BukkitMockFactory;
import com.github.commander.test.bukkit.mock.MockBukkitCommandSender;
import com.github.commander.test.bukkit.mock.MockBukkitPlayer;
import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.ravenlab.commander.platform.bukkit.BukkitCommandWrapper;

public class BukkitCommandWrapperTest {

	private static BukkitMockFactory factory;
	
	@BeforeClass
	public static void initFactory() {
		factory = new BukkitMockFactory();
	}
	
	@Test
	public void wrapperExecutePlayerNoPermissionTest() {
		ParentCommand<CommandSender> command = new ParentCommand<>();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockBukkitPlayer player = factory.createPlayer(name, uuid);
		String[] args = new String[0];
		wrapper.execute(player, command.getData().get().getName(), args);
		assertFalse(command.hasRan());
	}
	
	@Test
	public void wrapperExecutePlayerHasPermissionTest() {
		ParentCommand<CommandSender> command = new ParentCommand<>();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockBukkitPlayer player = factory.createPlayer(name, uuid);
		player.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(player, command.getData().get().getName(), args);
		assertTrue(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteSenderNoPermissionTest() {
		ParentCommand<CommandSender> command = new ParentCommand<>();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		MockBukkitCommandSender sender = factory.createSender(name);
		String[] args = new String[0];
		wrapper.execute(sender, command.getData().get().getName(), args);
		assertFalse(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteSenderHasPermissionTest() {
		ParentCommand<CommandSender> command = new ParentCommand<>();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		MockBukkitCommandSender sender = factory.createSender(name);
		sender.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(sender, command.getData().get().getName(), args);
		assertTrue(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteCommandNoPermissionTest() {
		ChildCommand<CommandSender> command = new ChildCommand<>();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		MockBukkitPlayer player = factory.createPlayer(name, uuid);
		player.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(player, command.getData().get().getName(), args);
		assertTrue(command.hasRan());
	}
}