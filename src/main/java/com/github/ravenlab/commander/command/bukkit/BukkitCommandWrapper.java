package com.github.ravenlab.commander.command.bukkit;

import java.util.Arrays;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.ravenlab.commander.command.CommandArgs;
import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.parser.CommandParser;
import com.github.ravenlab.commander.command.parser.ParserData;
import com.github.ravenlab.commander.player.bukkit.BukkitCommanderPlayer;
import com.github.ravenlab.commander.sender.CommanderSender;
import com.github.ravenlab.commander.sender.bukkit.BukkitCommanderSender;

public class BukkitCommandWrapper extends Command {

	private CommanderCommand command;
	private CommandParser parser;
	
	public BukkitCommandWrapper(CommandData data, CommanderCommand command)  {
		super(data.getName());
		this.command = command;
		this.parser = new CommandParser();
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
		
		ParserData data = this.parser.parse(this.command, args);
		CommanderCommand commandToExecute = data.getCommand();
		String[] executeArgs = data.getArgs();
		
		CommandArgs commandArgs = new CommandArgs(Arrays.asList(executeArgs));
		commandToExecute.doCommand(commanderSender, commandLabel, commandArgs);
		return true;
	}
}