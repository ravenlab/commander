package com.github.commander.test.bukkit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.bukkit.command.Command;
import org.junit.Test;

import com.github.commander.test.bukkit.mock.TestBukkitPlayer;
import com.github.commander.test.bukkit.mock.TestBukkitSender;
import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.ravenlab.commander.command.platform.bukkit.BukkitCommandWrapper;

public class BukkitCommandWrapperTest {

	@Test
	public void wrapperExecutePlayerNoPermissionTest() {
		ParentCommand command = new ParentCommand();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		UUID uuid = UUID.randomUUID();
		TestBukkitPlayer player = new TestBukkitPlayer(name, uuid);
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
		TestBukkitPlayer player = new TestBukkitPlayer(name, uuid);
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
		TestBukkitSender sender = new TestBukkitSender(name);
		String[] args = new String[0];
		wrapper.execute(sender, command.getData().get().getName(), args);
		assertFalse(command.hasRan());
	}
	
	@Test
	public void wrapperExecuteSenderHasPermissionTest() {
		ParentCommand command = new ParentCommand();
		Command wrapper = new BukkitCommandWrapper(command);
		String name = "test";
		TestBukkitSender sender = new TestBukkitSender(name);
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
		TestBukkitPlayer player = new TestBukkitPlayer(name, uuid);
		player.addPermission(command.getData().get().getPermission(), true);
		String[] args = new String[0];
		wrapper.execute(player, command.getData().get().getName(), args);
		assertTrue(command.hasRan());
	}
}