package com.github.ravenlab.commander.command;

import java.util.Collection;

public class CommandData {

	private String name;
	private Collection<String> aliases;
	
	public CommandData(String name, Collection<String> aliases) {
		this.name = name;
		this.aliases = aliases;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Collection<String> getAliases() {
		return this.aliases;
	}
}