package com.github.ravenlab.commander.command;

import java.util.List;

public class RegisteredData {

	public List<String> aliases;
	public RegisteredData(List<String> aliases) {
		this.aliases = aliases;
	}
	
	public List<String> getAliases() {
		return this.aliases;
	}
}
