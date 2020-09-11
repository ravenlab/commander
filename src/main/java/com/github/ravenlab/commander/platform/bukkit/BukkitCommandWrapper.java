package com.github.ravenlab.commander.platform.bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.CommanderExecutor;
import com.github.ravenlab.commander.sender.CommanderSender;

public class BukkitCommandWrapper extends Command {

	private CommanderExecutor<CommandSender> executor;
	
	public BukkitCommandWrapper(CommanderCommand<CommandSender> command)  {
		super(command.getData().get().getName());
		this.executor = new CommanderExecutor<>(command, new BukkitTypeResolver());
	}

	@Override
	public boolean execute(CommandSender bukkitSender, String commandLabel, String[] args) {
		CommanderSender<?> commanderSender = null;
		if(bukkitSender instanceof Player) {
			Player player = (Player) bukkitSender;
			commanderSender = new BukkitCommanderPlayer(player);
		} else {
			commanderSender = new BukkitCommanderSender(bukkitSender);
		}
		
		this.executor.execute(commanderSender, commandLabel, args);
		return true;
	}
}