package com.github.commander.test.bungeecord.mock;

import java.util.UUID;

import org.mockito.Mockito;

public class BungeeMockFactory {

	public MockBungeeServer createServer() {
		return Mockito.spy(MockBungeeServer.class);
	}

	public MockBungeePlugin createPlugin(String name) {
		return Mockito.mock(MockBungeePlugin.class,
				Mockito.withSettings().useConstructor(name).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
	
	public MockBungeePlayer createPlayer(String name, UUID uuid) {
		return Mockito.mock(MockBungeePlayer.class, 
				Mockito.withSettings().useConstructor(name, uuid).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
	
	public MockBungeeCommandSender createSender(String name) {
		return Mockito.mock(MockBungeeCommandSender.class, 
				Mockito.withSettings().useConstructor(name).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
}