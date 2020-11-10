package com.github.commander.test.velocity.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.velocitypowered.api.proxy.Player;

public abstract class MockVelocityPlayer implements Player {

	private String name;
	private UUID uuid;
	private Map<String, Boolean> permissions;
	private List<String> receivedMessages;
	
	public MockVelocityPlayer(String name, UUID uuid) {
		this.name = name;
		this.uuid = uuid;
		this.permissions = new HashMap<>();
		this.receivedMessages = new ArrayList<>();
	}
	
	public String getName() {
		return this.name;
	}
	
	@Override
	public UUID getUniqueId() {
		return this.uuid;
	}

	@Override
	public boolean hasPermission(String permission) {
		Boolean value = this.permissions.get(permission);
		if(value != null) {
			return value;
		}
		
		return false;
	}
	
	public void addPermission(String permission, boolean value) {
		this.permissions.put(permission, value);
	}
	
	public void sendMessage(String message) {
		this.receivedMessages.add(message);
	}
	
	public List<String> getReceivedMessages() {
		return Collections.unmodifiableList(this.receivedMessages);
	}
}