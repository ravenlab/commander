package com.github.ravenlab.commander.command.bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.CommanderExecutor;
import com.github.ravenlab.commander.player.bukkit.BukkitCommanderPlayer;
import com.github.ravenlab.commander.resolver.bukkit.BukkitTypeResolver;
import com.github.ravenlab.commander.sender.CommanderSender;
import com.github.ravenlab.commander.sender.bukkit.BukkitCommanderSender;

public class BukkitCommandWrapper extends Command {

	private CommanderExecutor executor;
	
	public BukkitCommandWrapper(CommandData data, CommanderCommand command)  {
		super(data.getName());
		this.executor = new CommanderExecutor(command, new BukkitTypeResolver());
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