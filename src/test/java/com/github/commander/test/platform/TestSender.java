package com.github.commander.test.platform;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestSender {

	private String name;
	private Map<String, Boolean> permissions;
	private List<String> receivedMessages;
	public TestSender(String name) {
		this.name = name;
		this.permissions = new HashMap<>();
		this.receivedMessages = new ArrayList<>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public void sendMessage(String message) {
		this.receivedMessages.add(message);
	}
	
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