package com.github.ravenlab.commander.sender;

public abstract class CommanderSender<T> {

	private T sender;
	
	public CommanderSender(T sender) {
		this.sender = sender;
	}
	
	public abstract String getName();
	public abstract boolean hasPermission(String permission);
	public abstract void sendMessage(String message);
	
	public T getNative() {
		return this.sender;
	}
}