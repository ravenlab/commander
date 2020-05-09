package com.github.ravenlab.commander.command;

import java.util.Collection;

public class CommandData {

	private Collection<String> aliases;
	public CommandData(Collection<String> aliases) {
		this.aliases = aliases;
	}
	
	public Collection<String> getAliases() {
		return this.aliases;
	}
}