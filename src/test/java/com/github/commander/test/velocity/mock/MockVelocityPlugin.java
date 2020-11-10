package com.github.commander.test.velocity.mock;

import com.velocitypowered.api.plugin.PluginContainer;
import com.velocitypowered.api.plugin.PluginDescription;

public abstract class MockVelocityPlugin implements PluginContainer {

	private PluginDescription description;
	
	public MockVelocityPlugin(String name) {
		this.description = new VelocityMockPluginDescription(name);
	}
	
	public String getName() {
		return this.description.getName().get();
	}
	
	@Override
	public PluginDescription getDescription() {
		return this.description;
	}
}