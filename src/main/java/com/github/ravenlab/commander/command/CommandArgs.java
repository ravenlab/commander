package com.github.ravenlab.commander.command;

import java.util.List;
import java.util.Optional;

public class CommandArgs {

	private List<String> args;
	
	public CommandArgs(List<String> args) {
		this.args = args;
	}
	
	@SuppressWarnings("unchecked")
	public <T> Optional<T> getArg(Class<T> clazz, int index) {
		if(isOutOfBounds(index)) {
			return Optional.empty();
		} else if(!isType(clazz, index)) {
			return Optional.empty();
		}
		
		Object obj = this.args.get(index);
		return Optional.of((T) obj);
	}
	
	private boolean isType(Class<?> clazz, int index) {
		Class<?> objClazz = this.args.get(index).getClass();
		return clazz.isAssignableFrom(objClazz);
	}
	
	private boolean isOutOfBounds(int index) {
		return index < 0 || index > args.size() - 1;
	}
}