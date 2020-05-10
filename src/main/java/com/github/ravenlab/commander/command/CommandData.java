package com.github.ravenlab.commander.command;

import java.util.Collections;
import java.util.List;

public class CommandData {

	private String name;
	private List<String> aliases;
	
	public CommandData(String name, List<String> aliases) {
		this.name = name;
		this.aliases = Collections.unmodifiableList(aliases);
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<String> getAliases() {
		return this.aliases;
	}
}