package com.github.ravenlab.commander.command;

import java.util.Collection;
import java.util.Collections;

public class RegistrationData {

	private Collection<String> aliases;
	private RegistrationStatus status;
	
	public RegistrationData(Collection<String> aliases, RegistrationStatus status) {
		this.aliases = Collections.unmodifiableCollection(aliases);
		this.status = status;
	}
	
	public Collection<String> getAliases() {
		return this.aliases;
	}
	
	public RegistrationStatus getStatus() {
		return this.status;
	}
}