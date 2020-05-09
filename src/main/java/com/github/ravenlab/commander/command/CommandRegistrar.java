package com.github.ravenlab.commander.command;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public abstract class CommandRegistrar<T> {

	public abstract RegistrationData register(T plugin, CommanderCommand command, boolean forceRegister);

	public RegistrationData register(T plugin, CommanderCommand command) {
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
	
	protected RegistrationStatus getStatus(CommandData data, Collection<String> aliases) {
		if(aliases.size() == 0) {
			return RegistrationStatus.REGISTERED_NONE;
		} else if(aliases.size() != data.getAliases().size()) {
			return RegistrationStatus.REGISTERED_SOME;
		} else {
			return RegistrationStatus.REGISTERED_ALL;
		}
	}
}