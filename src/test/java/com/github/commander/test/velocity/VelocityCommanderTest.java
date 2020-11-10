package com.github.commander.test.velocity;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.NoAnnotationCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.commander.test.velocity.mock.MockVelocityServer;
import com.github.commander.test.velocity.mock.VelocityMockFactory;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.platform.velocity.VelocityCommander;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.plugin.PluginContainer;

public class VelocityCommanderTest {

	private VelocityMockFactory factory;
	private MockVelocityServer server;
	
	@Before
	public void bootstrapServer() {
		this.factory = new VelocityMockFactory();
		this.server = this.factory.createServer();
	}
	
	@Test
	public void testRegisterNoAnnotation() {
		PluginContainer plugin = this.factory.createPlugin("test");
		VelocityCommander commander = new VelocityCommander(this.server);
		CommanderCommand<CommandSource> command = new NoAnnotationCommand<>();
		boolean registered = commander.register(plugin, command, true);
		assertFalse(registered);
	}
	
	@Test
	public void testRegisterSuccessWithoutForce() {
		PluginContainer plugin = this.factory.createPlugin("test");
		VelocityCommander commander = new VelocityCommander(this.server);
		CommanderCommand<CommandSource> command = new ParentCommand<>();
		boolean registered = commander.register(plugin, command);
		assertTrue(registered);
	}
	
	@Test
	public void testRegisterSuccessForce() {
		PluginContainer plugin = this.factory.createPlugin("test");
		VelocityCommander commander = new VelocityCommander(this.server);
		CommanderCommand<CommandSource> command = new ParentCommand<>();
		boolean registered = commander.register(plugin, command, true);
		assertTrue(registered);
	}
	
	@Test
	public void testRegisterMultipleCommands() {
		PluginContainer plugin = this.factory.createPlugin("test");
		VelocityCommander commander = new VelocityCommander(this.server);
		CommanderCommand<CommandSource> command = new ParentCommand<>();
		CommanderCommand<CommandSource> child = new ChildCommand<>();
		boolean parentRegistered = commander.register(plugin, command);
		boolean childRegistered = commander.register(plugin, child);
		assertTrue(parentRegistered);
		assertTrue(childRegistered);
	}
	
	@Test
	public void testRegisterWithAlias() {
		PluginContainer plugin = this.factory.createPlugin("test");
		PluginContainer plugin2 = this.factory.createPlugin("test2");
		CommanderCommand<CommandSource> command = new ParentCommand<>();
		VelocityCommander commander = new VelocityCommander(this.server);
		commander.register(plugin, command);
		commander.register(plugin2, command);
		Optional<Collection<String>> commandOptional = commander.getCommands(plugin2);
		assertTrue(commandOptional.isPresent());
		String first = null;
		for(String cmd : commandOptional.get()) {
			first = cmd;
			break;
		}
		assertTrue(first.equals("test2:parent"));
	}
	
	@Test
	public void testRegisterSameAliasForce() {
		PluginContainer plugin = this.factory.createPlugin("test");
		PluginContainer plugin2 = this.factory.createPlugin("test");
		CommanderCommand<CommandSource> command = new ParentCommand<>();
		VelocityCommander commander = new VelocityCommander(this.server);
		commander.register(plugin, command);
		commander.register(plugin2, command, true);
		Optional<Collection<String>> commandOptional = commander.getCommands(plugin2);
		assertTrue(commandOptional.isPresent());
		assertFalse(commandOptional.get().contains("test2:father"));
	}
	
	@Test
	public void testUnregisterOneCommand() {
		PluginContainer plugin = this.factory.createPlugin("test");
		VelocityCommander commander = new VelocityCommander(this.server);
		CommanderCommand<CommandSource> command = new ParentCommand<>();
		boolean parentRegistered = commander.register(plugin, command);
		commander.unregister(plugin, "parent");
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertTrue(parentRegistered);
		assertTrue(commands.isPresent());
		assertTrue(commands.get().size() == 2);
	}
	
	@Test
	public void testUnregisterMultipleCommand() {
		PluginContainer plugin = this.factory.createPlugin("test");
		VelocityCommander commander = new VelocityCommander(this.server);
		CommanderCommand<CommandSource> command = new ParentCommand<>();
		boolean parentRegistered = commander.register(plugin, command);
		commander.unregister(plugin, "parent", "father", "mother");
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertTrue(parentRegistered);
		assertTrue(commands.isPresent());
		assertTrue(commands.get().size() == 0);
	}
	
	@Test
	public void testUnregisterPlugin() {
		PluginContainer plugin = this.factory.createPlugin("test");
		VelocityCommander commander = new VelocityCommander(this.server);
		CommanderCommand<CommandSource> command = new ParentCommand<>();
		boolean parentRegistered = commander.register(plugin, command);
		commander.unregister(plugin);
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertTrue(parentRegistered);
		assertTrue(commands.isPresent());
		assertTrue(commands.get().size() == 0);
	}
	
	@Test
	public void testUnregisterNoRegisteredPlugin() {
		PluginContainer plugin = this.factory.createPlugin("test");
		VelocityCommander commander = new VelocityCommander(this.server);
		boolean unregistered = commander.unregister(plugin);
		assertFalse(unregistered);
	}
	
	@Test
	public void testUnregisterNoRegisteredPluginCollection() {
		PluginContainer plugin = this.factory.createPlugin("test");
		VelocityCommander commander = new VelocityCommander(this.server);
		boolean unregistered = commander.unregister(plugin, new String[0]);
		assertFalse(unregistered);
	}
	
	@Test
	public void testUnregisterNonExistentCommand() {
		PluginContainer plugin = this.factory.createPlugin("test");
		CommanderCommand<CommandSource> command = new ParentCommand<>();
		VelocityCommander commander = new VelocityCommander(this.server);
		commander.register(plugin, command);
		boolean unregistered = commander.unregister(plugin, "doesntexist");
		assertFalse(unregistered);
	}
	
	@Test
	public void testUnregisterWithAlias() {
		PluginContainer plugin = this.factory.createPlugin("test");
		PluginContainer plugin2 = this.factory.createPlugin("test2");
		CommanderCommand<CommandSource> command = new ParentCommand<>();
		VelocityCommander commander = new VelocityCommander(this.server);
		commander.register(plugin, command);
		commander.register(plugin2, command);
		boolean unregistered = commander.unregister(plugin2, "parent");
		assertTrue(unregistered);
	}
	
	@Test
	public void testGetCommandsWithoutCommands() {
		PluginContainer plugin = this.factory.createPlugin("test");
		VelocityCommander commander = new VelocityCommander(this.server);
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertFalse(commands.isPresent());
	}
	
	@Test
	public void testGetCommandsWithCommands() {
		PluginContainer plugin = this.factory.createPlugin("test");
		VelocityCommander commander = new VelocityCommander(this.server);
		CommanderCommand<CommandSource> command = new ParentCommand<>();
		commander.register(plugin, command);
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertTrue(commands.isPresent());
		assertTrue(commands.get().size() == 3);
	}
}