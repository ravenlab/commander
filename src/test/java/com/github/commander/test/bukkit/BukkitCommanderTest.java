package com.github.commander.test.bukkit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Optional;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.junit.Before;
import org.junit.Test;

import com.github.commander.test.bukkit.mock.BukkitMockFactory;
import com.github.commander.test.bukkit.mock.MockBukkitServer;
import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.NoAnnotationCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.platform.bukkit.BukkitCommander;

public class BukkitCommanderTest {

	private BukkitMockFactory factory;
	private MockBukkitServer server;
	
	@Before
	public void bootstrapServer() {
		this.factory = new BukkitMockFactory();
		this.server = this.factory.createServer();
		this.setServer(this.server);
	}
	
	private void setServer(Server server) {
		try {
			Field serverField = Bukkit.class.getDeclaredField("server");
			serverField.setAccessible(true);
			serverField.set(null, server);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testRegisterNoAnnotation() {
		Plugin plugin = this.factory.createPlugin("test");
		BukkitCommander commander = new BukkitCommander();
		CommanderCommand command = new NoAnnotationCommand();
		boolean registered = commander.register(plugin, command, true);
		assertFalse(registered);
	}
	
	@Test
	public void testRegisterSuccessWithoutForce() {
		Plugin plugin = this.factory.createPlugin("test");
		BukkitCommander commander = new BukkitCommander();
		CommanderCommand command = new ParentCommand();
		boolean registered = commander.register(plugin, command);
		assertTrue(registered);
	}
	
	@Test
	public void testRegisterSuccessForce() {
		Plugin plugin = this.factory.createPlugin("test");
		BukkitCommander commander = new BukkitCommander();
		CommanderCommand command = new ParentCommand();
		boolean registered = commander.register(plugin, command, true);
		assertTrue(registered);
	}
	
	@Test
	public void testRegisterMultipleCommands() {
		Plugin plugin = this.factory.createPlugin("test");
		BukkitCommander commander = new BukkitCommander();
		CommanderCommand command = new ParentCommand();
		CommanderCommand child = new ChildCommand();
		boolean parentRegistered = commander.register(plugin, command);
		boolean childRegistered = commander.register(plugin, child);
		assertTrue(parentRegistered);
		assertTrue(childRegistered);
	}
	
	@Test
	public void testRegisterWithAlias() {
		Plugin plugin = this.factory.createPlugin("test");
		Plugin plugin2 = this.factory.createPlugin("test2");
		CommanderCommand command = new ParentCommand();
		BukkitCommander commander = new BukkitCommander();
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
		Plugin plugin = this.factory.createPlugin("test");
		Plugin plugin2 = this.factory.createPlugin("test");
		CommanderCommand command = new ParentCommand();
		BukkitCommander commander = new BukkitCommander();
		commander.register(plugin, command);
		commander.register(plugin2, command, true);
		Optional<Collection<String>> commandOptional = commander.getCommands(plugin2);
		assertTrue(commandOptional.isPresent());
		assertFalse(commandOptional.get().contains("test2:father"));
	}
	
	@Test
	public void testRegisterNoKnownCommandsField() {
		setServer(this.factory.createNoCommandServer());
		Plugin plugin = this.factory.createPlugin("test");
		CommanderCommand command = new ParentCommand();
		BukkitCommander commander = new BukkitCommander();
		boolean registered = commander.register(plugin, command);
		assertFalse(registered);
		setServer(this.server);
	}
	
	@Test
	public void testUnregisterOneCommand() {
		Plugin plugin = this.factory.createPlugin("test");
		BukkitCommander commander = new BukkitCommander();
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
		Plugin plugin = this.factory.createPlugin("test");
		BukkitCommander commander = new BukkitCommander();
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
		Plugin plugin = this.factory.createPlugin("test");
		BukkitCommander commander = new BukkitCommander();
		CommanderCommand command = new ParentCommand();
		boolean parentRegistered = commander.register(plugin, command);
		commander.unregister(plugin);
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertTrue(parentRegistered);
		assertTrue(commands.isPresent());
		assertTrue(commands.get().size() == 0);
	}
	
	@Test
	public void testUnregisterNoRegisteredPlugin() {
		Plugin plugin = this.factory.createPlugin("test");
		BukkitCommander commander = new BukkitCommander();
		boolean unregistered = commander.unregister(plugin);
		assertFalse(unregistered);
	}
	
	@Test
	public void testUnregisterNoRegisteredPluginCollection() {
		Plugin plugin = this.factory.createPlugin("test");
		BukkitCommander commander = new BukkitCommander();
		boolean unregistered = commander.unregister(plugin, new String[0]);
		assertFalse(unregistered);
	}
	
	@Test
	public void testUnregisterNonExistentCommand() {
		Plugin plugin = this.factory.createPlugin("test");
		CommanderCommand command = new ParentCommand();
		BukkitCommander commander = new BukkitCommander();
		commander.register(plugin, command);
		boolean unregistered = commander.unregister(plugin, "doesntexist");
		assertFalse(unregistered);
	}
	
	@Test
	public void testUnregisterWithAlias() {
		Plugin plugin = this.factory.createPlugin("test");
		Plugin plugin2 = this.factory.createPlugin("test2");
		CommanderCommand command = new ParentCommand();
		BukkitCommander commander = new BukkitCommander();
		commander.register(plugin, command);
		commander.register(plugin2, command);
		boolean unregistered = commander.unregister(plugin2, "parent");
		assertTrue(unregistered);
	}
	
	@Test
	public void testGetCommandsWithoutCommands() {
		Plugin plugin = this.factory.createPlugin("test");
		BukkitCommander commander = new BukkitCommander();
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertFalse(commands.isPresent());
	}
	
	@Test
	public void testGetCommandsWithCommands() {
		Plugin plugin = this.factory.createPlugin("test");
		BukkitCommander commander = new BukkitCommander();
		CommanderCommand command = new ParentCommand();
		commander.register(plugin, command);
		Optional<Collection<String>> commands = commander.getCommands(plugin);
		assertTrue(commands.isPresent());
		assertTrue(commands.get().size() == 3);
	}
}