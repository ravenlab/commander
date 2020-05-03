package com.github.ravenlab.commander.command;

public abstract class CommandArg<T> {

	private T arg;
	public CommandArg(T arg) {
		this.arg = arg;
	}
	
	public T getArg() {
		return this.arg;
	}
}