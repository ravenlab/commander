package com.github.ravenlab.commander.platform.sponge;

import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.text.Text;

import com.github.ravenlab.commander.sender.CommanderSender;

public class SpongeCommanderSender extends CommanderSender<CommandSource> {

	public SpongeCommanderSender(CommandSource sender) {
		super(sender);
	}

	@Override
	public String getName() {
		return this.getNative().getName();
	}

	@Override
	public boolean hasPermission(String permission) {
		return this.getNative().hasPermission(permission);
	}

	@Override
	public void sendMessage(String message) {
		this.getNative().sendMessages(Text.of(message));
	}
}