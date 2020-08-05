package com.github.commander.test.bungeecord.mock;

import net.md_5.bungee.api.plugin.Plugin;

public abstract class MockBungeePlugin extends Plugin {

	private String name;
	public MockBungeePlugin(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
}