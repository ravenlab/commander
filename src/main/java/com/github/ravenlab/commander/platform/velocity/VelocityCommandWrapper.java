package com.github.ravenlab.commander.platform.velocity;

import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.CommanderExecutor;
import com.github.ravenlab.commander.sender.CommanderSender;
import com.velocitypowered.api.command.Command;
import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import com.velocitypowered.api.proxy.ProxyServer;

public class VelocityCommandWrapper implements Command {

	private CommanderExecutor<CommandSource> executor;
	private String name;
	
	public VelocityCommandWrapper(CommanderCommand<CommandSource> command, ProxyServer server)  {
		this.executor = new CommanderExecutor<>(command, new VelocityTypeResolver(server));
		this.name = command.getData().get().getName();
	}

	@Override
	public void execute(CommandSource sender, String[] args) {
		CommanderSender<?> commanderSender = null;
		if(sender instanceof Player) {
			Player player = (Player) sender;
			commanderSender = new VelocityCommanderPlayer(player);
		} else {
			commanderSender = new VelocityCommanderSender(sender);
		}
		this.executor.execute(commanderSender, this.name, args);
	}
}