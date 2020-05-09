package com.github.ravenlab.commander;

import com.github.ravenlab.commander.registrar.CommandRegistrar;

public class Commander {

	public static CommandRegistrar createRegistrar() {
		return createRegistrar(findPlatform());
	}
	
	public static CommandRegistrar createRegistrar(Platform platform) {
		
	}
	
	private static Platform findPlatform() {
		
	}
}