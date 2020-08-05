package com.github.commander.test.bungeecord.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import net.md_5.bungee.api.connection.ProxiedPlayer;

public abstract class MockBungeePlayer implements ProxiedPlayer {

	private String name;
	private UUID uuid;
	private Map<String, Boolean> permissions;
	private List<String> receivedMessages;
	
	public MockBungeePlayer(String name, UUID uuid) {
		this.name = name;
		this.uuid = uuid;
		this.permissions = new HashMap<>();
		this.receivedMessages = new ArrayList<>();
	}
	
	@Override
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
	
	@Override
	public void sendMessage(String message) {
		this.receivedMessages.add(message);
	}
	
	public List<String> getReceivedMessages() {
		return Collections.unmodifiableList(this.receivedMessages);
	}
}