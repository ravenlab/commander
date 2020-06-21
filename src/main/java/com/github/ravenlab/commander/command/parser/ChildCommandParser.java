package com.github.ravenlab.commander.command.parser;

import java.util.Arrays;

import com.github.ravenlab.commander.command.CommanderCommand;

public class ChildCommandParser<T> {

	public ChildCommandParserData<T> parse(CommanderCommand<T> cmd, String[] args) {
		CommanderCommand<T> root = cmd;
		if(args.length > 0) {
			String first = args[0].toLowerCase();
			CommanderCommand<T> child = root.getChildByName(first);
			if(child != null) {
				String[] newArgs = new String[0];
				if(args.length > 1) {
					newArgs = Arrays.copyOfRange(args, 1, args.length);
				}
				
				return parse(child, newArgs);
			}
		}

		return new ChildCommandParserData<>(root, args);
	}
}