package com.github.commander.test.bungeecord;

import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.Test;

import com.github.commander.test.bungeecord.mock.BungeeMockFactory;
import com.github.commander.test.bungeecord.mock.MockBungeeServer;
import com.github.ravenlab.commander.command.platform.bungeecord.BungeeCommandMap;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeCommandMapTest {

	private BungeeMockFactory factory;
	private MockBungeeServer server;
	
	@Before
	public void bootstrapServer() {
		this.factory = new BungeeMockFactory();
		this.server = this.factory.createServer();
		this.setServer(this.server);
	}
	
	private void setServer(ProxyServer server) {
		try {
			Field serverField = ProxyServer.class.getDeclaredField("instance");
			serverField.setAccessible(true);
			serverField.set(null, server);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void invalidGetMapIfExists() {
		BungeeCommandMap map = new BungeeCommandMap();
		assertTrue(map.getMapIfExists("doesnotexist") == null);
	}
	
	@Test
	public void validGetMapIfExists() {
		BungeeCommandMap map = new BungeeCommandMap();
		assertTrue(map.getMapIfExists(ProxyServer.getInstance().getPluginManager()) != null);
	}
	
	@Test
	public void validGetKnownCommands() {
		BungeeCommandMap map = new BungeeCommandMap();
		try {
			Method getKnownCommands = map.getClass().getDeclaredMethod("getKnownCommands");
			getKnownCommands.setAccessible(true);
			Object knownCommands = getKnownCommands.invoke(map);
			assertTrue(knownCommands != null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void validGetCommandMap() {
		BungeeCommandMap map = new BungeeCommandMap();
		try {
			Method getCommandMap = map.getClass().getDeclaredMethod("getCommandMap");
			getCommandMap.setAccessible(true);
			Object BungeeCommandMap = getCommandMap.invoke(map);
			assertTrue(BungeeCommandMap != null);
		} catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			e.printStackTrace();
		}
	}
}