package com.github.commander.test.velocity;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.github.commander.test.velocity.mock.MockVelocityServer;
import com.github.commander.test.velocity.mock.VelocityMockFactory;
import com.github.ravenlab.commander.platform.velocity.VelocityCommandMap;

public class VelocityCommandMapTest {

	private VelocityMockFactory factory;
	private MockVelocityServer server;
	
	@Before
	public void bootstrapServer() {
		this.factory = new VelocityMockFactory();
		this.server = this.factory.createServer();
	}
	
	@Test
	public void invalidGetMapIfExists() {
		VelocityCommandMap map = new VelocityCommandMap();
		assertTrue(map.getMapIfExists("doesnotexist") == null);
	}
	
	@Test
	public void validGetMapIfExists() {
		VelocityCommandMap map = new VelocityCommandMap();
		assertTrue(map.getMapIfExists(this.server.getCommandManager()) != null);
	}
}