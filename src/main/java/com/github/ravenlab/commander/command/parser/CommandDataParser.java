package com.github.ravenlab.commander.command.parser;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import com.github.ravenlab.commander.command.Command;
import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.util.ChatColor;

public class CommandDataParser {
	
	public static final String NO_PERMISSION_MESSAGE = "&cYou do not have permission to execute that command!";
	
	public Optional<CommandData> parse(CommanderCommand command) {
		Command[] cmdAr = command.getClass().getAnnotationsByType(Command.class);
		if(cmdAr.length == 0) {
			return Optional.empty();
		}
		
		Command found = cmdAr[0];
		String name = found.value();
		String lowerName = name.toLowerCase();

		Collection<String> aliases = new HashSet<>();
		for(String alias : found.aliases()) {
			aliases.add(alias.toLowerCase());
		}
		aliases.add(lowerName);

		String permission = found.permission();
		String noPermissionMessage = found.noPermissionMessage();

		if(noPermissionMessage.equals("")) {
			noPermissionMessage = ChatColor.translateAlternateColorCodes('&', NO_PERMISSION_MESSAGE);
		} else {
			noPermissionMessage = ChatColor.translateAlternateColorCodes('&', noPermissionMessage);
		}

		CommandData commandData = new CommandData(lowerName, aliases, permission, noPermissionMessage);
		return Optional.of(commandData);
	}
}