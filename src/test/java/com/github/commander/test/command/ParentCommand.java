package com.github.commander.test.command;

import com.github.ravenlab.commander.command.Command;
import com.github.ravenlab.commander.command.CommandArgs;
import com.github.ravenlab.commander.command.CommanderCommand;

@Command(
	value = "parent", 
	aliases= {"father", "mother"},
	permission="parent.use", 
	noPermissionMessage="&cNo permission for that action"
)
public class ParentCommand<T> extends CommanderCommand<T> {
	
	private boolean ran;
	
	public ParentCommand() {
		this.ran = false;
	}
	
	@Override
	public boolean execute(T sender, String name, CommandArgs arg) {
		this.ran = true;
		return true;
	}
	
	public boolean hasRan() {
		return this.ran;
	}
}