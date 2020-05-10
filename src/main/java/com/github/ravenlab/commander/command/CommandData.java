package com.github.ravenlab.commander.command;

import java.util.Collections;
import java.util.List;

public class CommandData {

	private String name;
	private List<String> aliases;
	private String permission;
	
	public CommandData(String name, List<String> aliases, String permission) {
		this.name = name;
		this.aliases = Collections.unmodifiableList(aliases);
		this.permission = permission;
	}
	
	public String getName() {
		return this.name;
	}
	
	public List<String> getAliases() {
		return this.aliases;
	}
	
	public String getPermission() {
		return this.permission;
	}
}