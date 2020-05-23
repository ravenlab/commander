package com.github.commander.test.bukkit.mock;

import java.util.UUID;

import org.mockito.Mockito;

public class BukkitMockFactory {

	public TestBukkitServer createServer() {
		return Mockito.spy(TestBukkitServer.class);
	}
	
	public TestNoCommandBukkitServer createNoCommandServer() {
		return Mockito.spy(TestNoCommandBukkitServer.class);
	}
	
	public TestBukkitPlugin createPlugin(String name) {
		return Mockito.mock(TestBukkitPlugin.class,
				Mockito.withSettings().useConstructor(name).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
	
	public TestBukkitWorld createWorld(String name) {
		return Mockito.mock(TestBukkitWorld.class,
				Mockito.withSettings().useConstructor(name).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
	
	public TestBukkitPlayer createPlayer(String name, UUID uuid) {
		return Mockito.mock(TestBukkitPlayer.class, 
				Mockito.withSettings().useConstructor(name, uuid).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
	
	public TestBukkitCommandSender createSender(String name) {
		return Mockito.mock(TestBukkitCommandSender.class, 
				Mockito.withSettings().useConstructor(name).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
}