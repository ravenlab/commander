package com.github.ravenlab.commander.command;

import java.util.Collection;
import java.util.Collections;

public class CommandData {

	private String name;
	private Collection<String> aliases;
	private String permission;
	private String noPermissionMessage;
	
	public CommandData(String name, Collection<String> aliases, String permission, String noPermissionMessage) {
		this.name = name;
		this.aliases = Collections.unmodifiableCollection(aliases);
		this.permission = permission;
		this.noPermissionMessage = noPermissionMessage;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Collection<String> getAliases() {
		return this.aliases;
	}
	
	public String getPermission() {
		return this.permission;
	}
	
	public String getNoPermissionMessage() {
		return this.noPermissionMessage;
	}
}