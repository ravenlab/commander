package com.github.ravenlab.commander.command;

import java.util.*;

import com.github.ravenlab.commander.resolver.TypeResolver;
import com.github.ravenlab.commander.transform.*;

public class CommandArgs{

	private List<String> args;
	private Map<Class<?>, Transformer<?>> transformerClassMap;
	private Map<String, Transformer<?>> transformerNameMap;
	
	public CommandArgs(List<String> args, TypeResolver<?, ?> resolver) {
		this.args = args;
		this.transformerClassMap = new HashMap<>();
		this.transformerNameMap = new HashMap<>();
		this.registerTransformers(resolver);
	}

	public Optional<String> getArg(int index) {
		return this.getArg(String.class, index);
	}
	
	@SuppressWarnings("unchecked")
	public <T> Optional<T> getArg(Class<T> clazz, int index) {
		if(isOutOfBounds(index)) {
			return Optional.empty();
		}
		
		String arg = this.args.get(index);
		if(clazz.equals(String.class)) {
			return Optional.of((T) arg);
		}
		
		return this.transform(clazz, arg);
	}
	
	
	private boolean isOutOfBounds(int index) {
		return index < 0 || index > args.size() - 1;
	}
	
	@SuppressWarnings("unchecked")
	private <T> Optional<T> transform(Class<T> clazz, String arg) {
		if(clazz.isEnum()) {
			return (Optional<T>) this.transformEnum(clazz, arg);
		}
		
		Transformer<?> transformer = this.transformerClassMap.get(clazz);
		if(transformer == null) {
			return Optional.empty();
		}
		
		Transformer<T> typeTransformer = ((Transformer<T>)transformer);
		Optional<T> transformed = typeTransformer.transform(clazz, arg);
		if(transformed.isPresent()) {
			return Optional.of(transformed.get());
		}
		
		return Optional.empty();
	}
	
	@SuppressWarnings("unchecked")
	private <T extends Enum<T>> Optional<T> transformEnum(Class<?> clazz, String arg) {
		EnumTransformer<T> enumTransformer = new EnumTransformer<>();
		return enumTransformer.transform((Class<T>) clazz, arg);
	}
	
	public int size() {
		return this.args.size();
	}
	
	private void registerTransformers(TypeResolver<?, ?> resolver) {
		this.registerTransformer(resolver.getPlayerClass(), new PlayerTransformer<>(resolver));
		resolver.getWorldClass().ifPresent(clazz -> {
			this.registerTransformer(clazz, new WorldTransformer<>(resolver));
		});
		this.registerTransformer(Integer.class, new IntegerTransformer());
		this.registerTransformer(Double.class, new DoubleTransformer());
		this.registerTransformer(Float.class, new FloatTransformer());
		this.registerTransformer(Long.class, new LongTransformer());
		this.registerTransformer(UUID.class, new UUIDTransformer());
	}

	private void registerTransformer(Class<?> clazz, Transformer<?> transformer) {
		this.transformerClassMap.put(clazz, transformer);
		this.transformerNameMap.put(transformer.getName(), transformer);
	}
}