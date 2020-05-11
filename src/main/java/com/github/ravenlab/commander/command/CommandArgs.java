package com.github.ravenlab.commander.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.github.ravenlab.commander.player.CommanderPlayer;
import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.transform.DoubleTransformer;
import com.github.ravenlab.commander.transform.IntegerTransformer;
import com.github.ravenlab.commander.transform.PlayerTransformer;
import com.github.ravenlab.commander.transform.Transformer;
import com.github.ravenlab.commander.transform.WorldTransformer;
import com.github.ravenlab.commander.world.CommanderWorld;

public class CommandArgs {

	private List<String> args;
	private Map<Class<?>, Transformer<?>> transformerMap;
	
	public CommandArgs(List<String> args, TypeResolver resolver) {
		this.args = args;
		this.transformerMap = this.registerTransformers(resolver);
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
	
	public int size() {
		return this.args.size();
	}
	
	private Map<Class<?>, Transformer<?>> registerTransformers(TypeResolver resolver) {
		Map<Class<?>, Transformer<?>> transformers = new HashMap<>();
		transformers.put(CommanderPlayer.class, new PlayerTransformer(resolver));
		transformers.put(CommanderWorld.class, new WorldTransformer(resolver));
		transformers.put(Integer.class, new IntegerTransformer());
		transformers.put(Double.class, new DoubleTransformer());
		return transformers;
	}
}