package com.github.commander.test.bukkit.mock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.command.CommandSender;

public abstract class TestBukkitCommandSender implements CommandSender {

	private String name;
	private Map<String, Boolean> permissions;
	private List<String> receivedMessages;
	
	public TestBukkitCommandSender(String name) {
		this.name = name;
		this.permissions = new HashMap<>();
		this.receivedMessages = new ArrayList<>();
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public void sendMessage(String message) {
		this.receivedMessages.add(message);
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
	
	public List<String> getReceivedMessages() {
		return Collections.unmodifiableList(this.receivedMessages);
	}
}