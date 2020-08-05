package com.github.commander.test.bungeecord.mock;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginDescription;

public abstract class MockBungeePlugin extends Plugin {

	private PluginDescription description;
	
	public MockBungeePlugin(String name) {
		this.description = new MockPluginDescription(name);
	}
	
	public String getName() {
		return this.description.getName();
	}
	
	public PluginDescription getDescription() {
		return this.description;
	}
}