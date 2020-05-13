package com.github.commander.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

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
	public void testRegisterSuccessForce() {
		TestPlugin plugin = new TestPlugin("test");
		TestCommander commander = new TestCommander(true);
		CommanderCommand command = new ParentCommand();
		boolean registered = commander.register(plugin, command, true);
		assertTrue(registered);
	}
}