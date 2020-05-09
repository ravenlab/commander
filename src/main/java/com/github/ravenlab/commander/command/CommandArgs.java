package com.github.ravenlab.commander.command;

import java.util.List;
import java.util.Optional;

public class CommandArgs {

	private List<String> args;
	
	public CommandArgs(List<String> args) {
		this.args = args;
	}
	
	public boolean isType(int index, ArgType type) {
		if(this.isOutOfBounds(index)) {
			return false;
		}
		
		return type.isType(this.args.get(index));
	}
	
	@SuppressWarnings("unchecked")
	public <T> Optional<T> getArg(int index) {
		if(isOutOfBounds(index)) {
			return Optional.empty();
		}
		
		Object obj = this.args.get(index);
		return Optional.of((T) obj);
	}
	
	private boolean isOutOfBounds(int index) {
		return index < 0 || index > args.size() - 1;
	}
}