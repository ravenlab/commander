package com.github.commander.test.bukkit.mock;

import org.bukkit.World;

public abstract class MockBukkitWorld implements World {

	private String name;
	
	public MockBukkitWorld(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}