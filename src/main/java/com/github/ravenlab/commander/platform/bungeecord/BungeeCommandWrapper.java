package com.github.ravenlab.commander.platform.bungeecord;

import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.CommanderExecutor;
import com.github.ravenlab.commander.sender.CommanderSender;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class BungeeCommandWrapper extends Command {

	private CommanderExecutor<CommandSender> executor;
	
	public BungeeCommandWrapper(CommanderCommand<CommandSender> command)  {
		super(command.getData().get().getName());
		this.executor = new CommanderExecutor<>(command, new BungeeTypeResolver());
	}

	@Override
	public void execute(CommandSender proxySender, String[] args) {
		CommanderSender<?> commanderSender = null;
		if(proxySender instanceof ProxiedPlayer) {
			ProxiedPlayer player = (ProxiedPlayer) proxySender;
			commanderSender = new BungeeCommanderPlayer(player);
		} else {
			commanderSender = new BungeeCommanderSender(proxySender);
		}
		this.executor.execute(commanderSender, this.getName(), args);
	}
}