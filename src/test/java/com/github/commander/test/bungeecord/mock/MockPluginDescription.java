package com.github.commander.test.bungeecord.mock;

import net.md_5.bungee.api.plugin.PluginDescription;

public class MockPluginDescription extends PluginDescription {

	private String name;
	
	public MockPluginDescription(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}