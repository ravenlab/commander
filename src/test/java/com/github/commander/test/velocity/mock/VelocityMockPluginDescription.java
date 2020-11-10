package com.github.commander.test.velocity.mock;

import java.util.Optional;

import com.velocitypowered.api.plugin.PluginDescription;

public class VelocityMockPluginDescription implements PluginDescription {

	private String name;
	
	public VelocityMockPluginDescription(String name) {
		this.name = name;
	}
	
	@Override
	public Optional<String> getName() {
		return Optional.of(this.name);
	}

	@Override
	public String getId() {
		return this.name;
	}
}