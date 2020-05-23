package com.github.commander.test.bukkit;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.command.SimpleCommandMap;
import org.junit.Before;
import org.junit.Test;

import com.github.commander.test.bukkit.mock.BukkitMockFactory;
import com.github.commander.test.bukkit.mock.MockBukkitCommandMap;
import com.github.commander.test.bukkit.mock.MockBukkitServer;
import com.github.ravenlab.commander.command.platform.bukkit.BukkitCommandMap;

public class BukkitCommandMapTest {

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
	public void invalidGetMapIfExists() {
		BukkitCommandMap map = new BukkitCommandMap();
		assertTrue(map.getMapIfExists(MockBukkitCommandMap.class) == null);
	}
	
	@Test
	public void nullGetMapIfExists() {
		setServer(this.factory.createNoCommandServer());
		BukkitCommandMap map = new BukkitCommandMap();
		assertTrue(map.getMapIfExists(SimpleCommandMap.class) == null);
		setServer(this.server);
	}
	
	@Test
	public void validGetMapIfExists() {
		BukkitCommandMap map = new BukkitCommandMap();
		assertTrue(map.getMapIfExists(SimpleCommandMap.class) != null);
	}
	
	@Test
	public void invalidGetKnownCommands() {
		setServer(this.factory.createNoCommandServer());
		BukkitCommandMap map = new BukkitCommandMap();
		try {
			Method getKnownCommands = map.getClass().getDeclaredMethod("getKnownCommands");
			getKnownCommands.setAccessible(true);
			Object knownCommands = getKnownCommands.invoke(map);
			assertTrue(knownCommands == null);
		} catch (NoSuchMethodException | SecurityException | InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		setServer(this.server);
	}
	
	@Test
	public void validGetKnownCommands() {
		BukkitCommandMap map = new BukkitCommandMap();
		try {
			Method getKnownCommands = map.getClass().getDeclaredMethod("getKnownCommands");
			getKnownCommands.setAccessible(true);
			Object knownCommands = getKnownCommands.invoke(map, SimpleCommandMap.class);
			assertTrue(knownCommands != null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void invalidGetCommandMap() {
		setServer(this.factory.createNoCommandServer());
		BukkitCommandMap map = new BukkitCommandMap();
		try {
			Method getCommandMap = map.getClass().getDeclaredMethod("getCommandMap");
			getCommandMap.setAccessible(true);
			Object bukkitCommandMap = getCommandMap.invoke(map, SimpleCommandMap.class);
			assertTrue(bukkitCommandMap == null);
		} catch (NoSuchMethodException | SecurityException | InvocationTargetException | IllegalAccessException | IllegalArgumentException e) {
			e.printStackTrace();
		}
		setServer(this.server);
	}
	
	@Test
	public void validGetCommandMap() {
		BukkitCommandMap map = new BukkitCommandMap();
		try {
			Method getCommandMap = map.getClass().getDeclaredMethod("getCommandMap");
			getCommandMap.setAccessible(true);
			Object bukkitCommandMap = getCommandMap.invoke(map);
			assertTrue(bukkitCommandMap != null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
}