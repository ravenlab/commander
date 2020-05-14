package com.github.commander.test.platform;

import com.github.ravenlab.commander.sender.CommanderSender;

public class TestCommanderSender extends CommanderSender<TestSender>{

	public TestCommanderSender(TestSender sender) {
		super(sender);
	}

	@Override
	public String getName() {
		return this.getName();
	}

	@Override
	public boolean hasPermission(String permission) {
		return this.getNative().hasPermission(permission);
	}

	@Override
	public void sendMessage(String message) {
		this.getNative().sendMessage(message);
	}
}