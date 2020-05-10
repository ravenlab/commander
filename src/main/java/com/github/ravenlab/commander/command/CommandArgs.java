package com.github.ravenlab.commander.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.transform.Transformer;

public class CommandArgs {

	private List<String> args;
	private TypeResolver<?> resolver;
	private Map<Class<?>, Transformer<?>> transformerMap;
	
	public CommandArgs(List<String> args, TypeResolver<?> resolver) {
		this.args = args;
		this.resolver = resolver;
		this.transformerMap = new HashMap<>();
	}
	
	public <T> Optional<T> getArg(Class<T> clazz, int index) {
		if(isOutOfBounds(index)) {
			return Optional.empty();
		} else if(!isType(clazz, index)) {
			return Optional.empty();
		}
		
		String arg = this.args.get(index);
		return this.transform(clazz, arg);
	}
	
	private boolean isType(Class<?> clazz, int index) {
		Class<?> objClazz = this.args.get(index).getClass();
		return clazz.isAssignableFrom(objClazz);
	}
	
	private boolean isOutOfBounds(int index) {
		return index < 0 || index > args.size() - 1;
	}
	
	@SuppressWarnings("unchecked")
	private <T> Optional<T> transform(Class<T> clazz, String arg) {
		Transformer<?> transformer = this.transformerMap.get(clazz);
		if(transformer == null) {
			return Optional.empty();
		}
		
		return Optional.of((T) transformer.transform(arg));
	}
}