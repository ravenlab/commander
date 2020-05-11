package com.github.ravenlab.commander.world;

public abstract class CommanderWorld<T> {

	private T world;
	public CommanderWorld(T world) {
		this.world = world;
	}
	
	public abstract String getName();
	
	public T getNative() {
		return this.world;
	}
}