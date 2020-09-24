package com.github.commander.test.command;

import java.util.Optional;

import com.github.ravenlab.commander.command.Command;
import com.github.ravenlab.commander.command.CommandArgs;
import com.github.ravenlab.commander.command.CommanderCommand;

@Command(
	value = "parent", 
	aliases= {"father", "mother"},
	permission="parent.use", 
	noPermissionMessage="&cNo permission for that action",
	usage = "/parent"
)
public class ParentCommand<T> extends CommanderCommand<T> {
	
	private boolean ran;
	
	public ParentCommand() {
		this.ran = false;
	}
	
	@Override
	public boolean execute(T sender, String name, CommandArgs arg) {
		Optional<String> first = arg.getArg(0);
		if(first.isPresent()) {
			String firstArg = first.get();
			if(firstArg.equals("fail")) {
				return false;
			}
		}
		this.ran = true;
		return true;
	}
	
	public boolean hasRan() {
		return this.ran;
	}
}