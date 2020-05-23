package com.github.commander.test.bukkit.mock;

import org.bukkit.World;

public abstract class TestBukkitWorld implements World {

	private String name;
	
	public TestBukkitWorld(String name) {
		this.name = name;
	}
	
	@Override
	public String getName() {
		return this.name;
	}
}