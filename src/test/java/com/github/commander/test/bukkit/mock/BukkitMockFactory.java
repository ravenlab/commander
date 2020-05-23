package com.github.commander.test.bukkit.mock;

import java.util.UUID;

import org.mockito.Mockito;

public class BukkitMockFactory {

	public MockBukkitServer createServer() {
		return Mockito.spy(MockBukkitServer.class);
	}
	
	public MockNoCommandBukkitServer createNoCommandServer() {
		return Mockito.spy(MockNoCommandBukkitServer.class);
	}
	
	public MockBukkitPlugin createPlugin(String name) {
		return Mockito.mock(MockBukkitPlugin.class,
				Mockito.withSettings().useConstructor(name).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
	
	public MockBukkitWorld createWorld(String name) {
		return Mockito.mock(MockBukkitWorld.class,
				Mockito.withSettings().useConstructor(name).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
	
	public MockBukkitPlayer createPlayer(String name, UUID uuid) {
		return Mockito.mock(MockBukkitPlayer.class, 
				Mockito.withSettings().useConstructor(name, uuid).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
	
	public MockBukkitCommandSender createSender(String name) {
		return Mockito.mock(MockBukkitCommandSender.class, 
				Mockito.withSettings().useConstructor(name).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
}