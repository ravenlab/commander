package com.github.ravenlab.commander.command.parser;

import java.util.Arrays;

import com.github.ravenlab.commander.command.CommanderCommand;

public class CommandParser {

	public ParserData parse(CommanderCommand cmd, String[] args) {
		CommanderCommand root = cmd;
		for(int i = 0; i < args.length; i++) {
			String arg = args[i];
			for(CommanderCommand child : root.getChildren()) {
				for(String alias : child.getAliases()) {
					if(arg.equalsIgnoreCase(alias)) {
						String[] newArgs = Arrays.copyOfRange(args, i, args.length);
						return parse(child, newArgs);
					}
				}
			}
		}
		
		return new ParserData(root, args);
	}
}