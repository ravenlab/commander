package com.github.ravenlab.commander.platform.velocity;

import com.github.ravenlab.commander.platform.velocity.util.MessageUtil;
import com.github.ravenlab.commander.sender.CommanderSender;
import com.velocitypowered.api.command.CommandSource;

public class VelocityCommanderSender extends CommanderSender<CommandSource> {

	public VelocityCommanderSender(CommandSource sender) {
		super(sender);
	}

	@Override
	public String getName() {
		return "Console-Sender";
	}

	@Override
	public boolean hasPermission(String permission) {
		return this.getNative().hasPermission(permission);
	}

	@Override
	public void sendMessage(String message) {
		MessageUtil.sendMessage(this.getNative(), message);
	}
}