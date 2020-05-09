package com.github.ravenlab.commander.command;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

public abstract class CommandRegistrar<T> {

	public abstract List<String> register(T plugin, CommanderCommand command, boolean forceRegister);

	public List<String> register(T plugin, CommanderCommand command) {
		return this.register(plugin, command, false);
	}
	
	protected CommandData parseCommandData(CommanderCommand command) {
		Command found = null;
		
		for(Annotation anno : command.getClass().getAnnotations()) {
			if(anno.getClass().equals(Command.class)) {
				found = (Command) anno;
			}
		}
		
		if(found == null) {
			return null;
		}
		
		List<String> aliases = Arrays.asList(found.aliases());
		aliases.add(found.value());
		
		return new CommandData(aliases);
	}
}