package com.github.ravenlab.commander.command.parser;

import java.util.Arrays;
import java.util.Collection;

import com.github.ravenlab.commander.command.CommanderCommand;

public class CommandArgParser {

	public ParserData parse(CommanderCommand cmd, String[] args) {
		CommanderCommand root = cmd;
		for(int i = 0; i < args.length; i++) {
			String arg = args[i].toLowerCase();
			for(CommanderCommand child : root.getChildren()) {
				Collection<String> aliases = child.getData().getAliases();
				if(aliases.contains(arg)) {
					String[] newArgs = Arrays.copyOfRange(args, i, args.length);
					return parse(child, newArgs);
				}
			}
		}

		return new ParserData(root, args);
	}
}