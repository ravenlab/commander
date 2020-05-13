package com.github.commander.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.commander.test.command.NoAnnotationCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.commander.test.platform.TestPlugin;
import com.github.commander.test.registrar.TestCommandRegistrar;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.registrar.RegistrationData;
import com.github.ravenlab.commander.command.registrar.RegistrationStatus;

public class TestRegistrar {

	@Test
	public void testRegisterNoAnnotation() {
		TestPlugin plugin = new TestPlugin();
		TestCommandRegistrar registrar = new TestCommandRegistrar(false);
		CommanderCommand command = new NoAnnotationCommand();
		RegistrationData data = registrar.register(plugin, command, true);
		assertTrue(data.getStatus() == RegistrationStatus.NO_ANNOTATION);
		assertTrue(data.getAliases().size() == 0);
	}
	
	@Test
	public void testRegisterNoneRegistered() {
		TestPlugin plugin = new TestPlugin();
		TestCommandRegistrar registrar = new TestCommandRegistrar(false);
		CommanderCommand command = new ParentCommand();
		RegistrationData data = registrar.register(plugin, command, true);
		assertTrue(data.getStatus() == RegistrationStatus.REGISTERED_NONE);
		assertTrue(data.getAliases().size() == 0);
	}
	
	@Test
	public void testRegisterSuccessForce() {
		TestPlugin plugin = new TestPlugin();
		TestCommandRegistrar registrar = new TestCommandRegistrar(true);
		CommanderCommand command = new ParentCommand();
		RegistrationData data = registrar.register(plugin, command, true);
		assertTrue(data.getStatus() == RegistrationStatus.REGISTERED_ALL);
		assertTrue(data.getAliases().size() == 3);
	}
}