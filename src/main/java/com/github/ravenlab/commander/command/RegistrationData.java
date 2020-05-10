package com.github.ravenlab.commander.command;

import java.util.Collections;
import java.util.List;

public class RegistrationData {

	private List<String> aliases;
	private RegistrationStatus status;
	
	public RegistrationData(List<String> aliases, RegistrationStatus status) {
		this.aliases = Collections.unmodifiableList(aliases);
		this.status = status;
	}
	
	public List<String> getAliases() {
		return this.aliases;
	}
	
	public RegistrationStatus getStatus() {
		return this.status;
	}
}