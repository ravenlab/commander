package com.github.commander.test.bukkit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.bukkit.command.Command;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.commander.test.bukkit.mock.BukkitMockFactory;
import com.github.commander.test.bukkit.mock.TestBukkitCommandSender;
import com.github.commander.test.bukkit.mock.TestBukkitPlayer;
import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.ravenlab.commander.command.platform.bukkit.BukkitCommandWrapper;

public class BukkitCommandWrapperTest {

	private static BukkitMockFactory factory;
	
	@BeforeClass
	public static void initFactory() {
		factory = new BukkitMockFactory();
	}
	
	@Test
	public void wrapperExecutePlayerNoPermissionTest() {
		ParentCommand command = new ParentCommand();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		TestBukkitPlayer player = factory.createPlayer(name, uuid);
		String[] args = new String[0];
		wrapper.execute(player, command.getData().get().getName(), args);
		assertFalse(command.hasRan());
	}
	
	@Test
	public void wrapperExecutePlayerHasPermissionTest() {
		ParentCommand command = new ParentCommand();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		TestBukkitPlayer player = factory.createPlayer(name, uuid);
		player.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(player, command.getData().get().getName(), args);
		assertTrue(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteSenderNoPermissionTest() {
		ParentCommand command = new ParentCommand();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		TestBukkitCommandSender sender = factory.createSender(name);
		String[] args = new String[0];
		wrapper.execute(sender, command.getData().get().getName(), args);
		assertFalse(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteSenderHasPermissionTest() {
		ParentCommand command = new ParentCommand();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		TestBukkitCommandSender sender = factory.createSender(name);
		sender.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(sender, command.getData().get().getName(), args);
		assertTrue(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteCommandNoPermissionTest() {
		ChildCommand command = new ChildCommand();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		TestBukkitPlayer player = factory.createPlayer(name, uuid);
		player.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(player, command.getData().get().getName(), args);
		assertTrue(command.hasRan());
	}
}