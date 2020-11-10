package com.github.commander.test.velocity.mock;

import java.util.UUID;

import org.mockito.Mockito;

public class VelocityMockFactory {

	public MockVelocityServer createServer() {
		return Mockito.spy(MockVelocityServer.class);
	}

	public MockVelocityPlugin createPlugin(String name) {
		return Mockito.mock(MockVelocityPlugin.class,
				Mockito.withSettings().useConstructor(name).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
	
	public MockVelocityPlayer createPlayer(String name, UUID uuid) {
		return Mockito.mock(MockVelocityPlayer.class, 
				Mockito.withSettings().useConstructor(name, uuid).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
	
	public MockVelocityCommandSource createSender(String name) {
		return Mockito.mock(MockVelocityCommandSource.class, 
				Mockito.withSettings().useConstructor(name).defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
	
	public MockVelocityCommandManager createCommandManager() {
		return Mockito.mock(MockVelocityCommandManager.class, 
				Mockito.withSettings().useConstructor().defaultAnswer(Mockito.CALLS_REAL_METHODS));
	}
}