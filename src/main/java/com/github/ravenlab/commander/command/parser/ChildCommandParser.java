package com.github.ravenlab.commander.command.parser;

import java.util.Arrays;

import com.github.ravenlab.commander.command.CommanderCommand;

public class ChildCommandParser {

	public ChildCommandParserData parse(CommanderCommand cmd, String[] args) {
		CommanderCommand root = cmd;
		if(args.length > 0) {
			String first = args[0].toLowerCase();
			CommanderCommand child = root.getChildByName(first);
			if(child != null) {
				String[] newArgs = new String[0];
				if(args.length > 1) {
					newArgs = Arrays.copyOfRange(args, 1, args.length);
				}
				
				return parse(child, newArgs);
			}
		}

		return new ChildCommandParserData(root, args);
	}
}