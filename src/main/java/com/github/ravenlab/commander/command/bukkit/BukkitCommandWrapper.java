package com.github.ravenlab.commander.command.bukkit;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ravenlab.commander.command.CommandArgs;
import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.player.bukkit.BukkitCommanderPlayer;
import com.github.ravenlab.commander.sender.CommanderSender;
import com.github.ravenlab.commander.sender.bukkit.BukkitCommanderSender;

public class BukkitCommandWrapper extends Command {

	private CommanderCommand command;
	
	public BukkitCommandWrapper(CommandData data, CommanderCommand command)  {
		super(data.getName());
		this.command = command;
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
		
		this.command.doCommand(commanderSender, commandLabel, new CommandArgs(Arrays.asList(args)));
		return true;
	}
}