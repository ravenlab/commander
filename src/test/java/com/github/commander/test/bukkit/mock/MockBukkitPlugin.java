package com.github.commander.test.bukkit.mock;

import org.bukkit.plugin.Plugin;

public abstract class MockBukkitPlugin implements Plugin {

	private String name;
	public MockBukkitPlugin(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}