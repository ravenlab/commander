package com.github.commander.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.commander.test.command.NoAnnotationCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.commander.test.platform.TestPlugin;
import com.github.commander.test.registrar.TestCommander;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.registration.RegistrationData;
import com.github.ravenlab.commander.registration.RegistrationStatus;

public class CommanderTest {

	@Test
	public void testRegisterNoAnnotation() {
		TestPlugin plugin = new TestPlugin();
		TestCommander commander = new TestCommander(false);
		CommanderCommand command = new NoAnnotationCommand();
		RegistrationData data = commander.register(plugin, command, true);
		assertTrue(data.getStatus() == RegistrationStatus.NO_ANNOTATION);
		assertTrue(data.getAliases().size() == 0);
	}
	
	@Test
	public void TestCommander() {
		TestPlugin plugin = new TestPlugin();
		TestCommander commander = new TestCommander(false);
		CommanderCommand command = new ParentCommand();
		RegistrationData data = commander.register(plugin, command, true);
		assertTrue(data.getStatus() == RegistrationStatus.REGISTERED_NONE);
		assertTrue(data.getAliases().size() == 0);
	}
	
	@Test
	public void testRegisterSuccessForce() {
		TestPlugin plugin = new TestPlugin();
		TestCommander commander = new TestCommander(true);
		CommanderCommand command = new ParentCommand();
		RegistrationData data = commander.register(plugin, command, true);
		assertTrue(data.getStatus() == RegistrationStatus.REGISTERED_ALL);
		assertTrue(data.getAliases().size() == 3);
	}
}