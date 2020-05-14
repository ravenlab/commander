package com.github.commander.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Optional;

import org.junit.Test;

import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.NoAnnotationCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.commander.test.platform.TestPlugin;
import com.github.commander.test.registrar.TestCommander;
import com.github.ravenlab.commander.command.CommanderCommand;

public class CommanderTest {

	@Test
	public void testRegisterNoAnnotation() {
		TestPlugin plugin = new TestPlugin("test");
		TestCommander commander = new TestCommander(false);
		CommanderCommand command = new NoAnnotationCommand();
		boolean registered = commander.register(plugin, command, true);
		assertFalse(registered);
	}
	
	@Test
	public void testRegisterSuccessWithoutForce() {
		TestPlugin plugin = new TestPlugin("test");
		TestCommander commander = new TestCommander(true);
		CommanderCommand command = new ParentCommand();
		boolean registered = commander.register(plugin, command);
		assertTrue(registered);
	}
	
	@Test
	public void testRegisterSuccessForce() {
		TestPlugin plugin = new TestPlugin("test");
		TestCommander commander = new TestCommander(true);
		CommanderCommand command = new ParentCommand();
		boolean registered = commander.register(plugin, command, true);
		assertTrue(registered);
	}
	
	@Test
	public void testRegisterMultipleCommands() {
		TestPlugin plugin = new TestPlugin("test");
		TestCommander commander = new TestCommander(true);
		CommanderCommand command = new ParentCommand();
		CommanderCommand child = new ChildCommand();
		boolean parentRegistered = commander.register(plugin, command);
		boolean childRegistered = commander.register(plugin, child);
		assertTrue(parentRegistered);
		assertTrue(childRegistered);
	}
	
	@Test
	public void testUnregisterOneCommand() {
		TestPlugin plugin = new TestPlugin("test");
		TestCommander commander = new TestCommander(true);
		CommanderCommand command = new ParentCommand();
		boolean parentRegistered = commander.register(plugin, command);
		commander.unregister(plugin, "parent");
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertTrue(parentRegistered);
		assertTrue(commands.isPresent());
		assertTrue(commands.get().size() == 2);
	}
	
	@Test
	public void testUnregisterMultipleCommand() {
		TestPlugin plugin = new TestPlugin("test");
		TestCommander commander = new TestCommander(true);
		CommanderCommand command = new ParentCommand();
		boolean parentRegistered = commander.register(plugin, command);
		commander.unregister(plugin, "parent", "father", "mother");
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertTrue(parentRegistered);
		assertTrue(commands.isPresent());
		assertTrue(commands.get().size() == 0);
	}
	
	@Test
	public void testUnregisterPlugin() {
		TestPlugin plugin = new TestPlugin("test");
		TestCommander commander = new TestCommander(true);
		CommanderCommand command = new ParentCommand();
		boolean parentRegistered = commander.register(plugin, command);
		commander.unregister(plugin);
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertTrue(parentRegistered);
		assertTrue(commands.isPresent());
		assertTrue(commands.get().size() == 0);
	}
	
	@Test
	public void testGetCommandsWithoutCommands() {
		TestPlugin plugin = new TestPlugin("test");
		TestCommander commander = new TestCommander(true);
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertFalse(commands.isPresent());
	}
	
	@Test
	public void testGetCommandsWithCommands() {
		TestPlugin plugin = new TestPlugin("test");
		TestCommander commander = new TestCommander(true);
		CommanderCommand command = new ParentCommand();
		commander.register(plugin, command);
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertTrue(commands.isPresent());
		assertTrue(commands.get().size() == 3);
	}
}