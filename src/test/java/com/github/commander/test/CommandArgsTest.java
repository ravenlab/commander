package com.github.commander.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.junit.Test;

import com.github.commander.test.resolver.TestTypeResolver;
import com.github.ravenlab.commander.command.CommandArgs;
import com.github.ravenlab.commander.resolver.TypeResolver;

public class CommandArgsTest {

	@Test
	public void lowerBoundsTest() {
		List<String> args = new ArrayList<>();
		args.add("test");
		TypeResolver<?, ?> testResolver = new TestTypeResolver();
		CommandArgs commandArgs = new CommandArgs(args, testResolver);
		assertFalse(commandArgs.getArg(-1).isPresent());
	}

	@Test
	public void upperBoundsTest() {
		List<String> args = new ArrayList<>();
		args.add("test");
		TypeResolver<?, ?> testResolver = new TestTypeResolver();
		CommandArgs commandArgs = new CommandArgs(args, testResolver);
		assertFalse(commandArgs.getArg(args.size()).isPresent());
	}
	
	@Test
	public void argTest() {
		List<String> args = new ArrayList<>();
		args.add("test");
		TypeResolver<?, ?> testResolver = new TestTypeResolver();
		CommandArgs commandArgs = new CommandArgs(args, testResolver);
		assertTrue(commandArgs.getArg(0).isPresent());
		assertTrue(commandArgs.getArg(0).get().equals("test"));
	}
	
	@Test
	public void notStringTest() {
		List<String> args = new ArrayList<>();
		args.add("1");
		TypeResolver<?, ?> testResolver = new TestTypeResolver();
		CommandArgs commandArgs = new CommandArgs(args, testResolver);
		assertTrue(commandArgs.getArg(0).isPresent());
		
		Optional<Integer> optionalValue = commandArgs.getArg(Integer.class, 0);
		assertTrue(optionalValue.isPresent());
		assertTrue(optionalValue.get() == 1);
	}
	
	@Test
	public void noTransformerTest() {
		List<String> args = new ArrayList<>();
		args.add("1");
		TypeResolver<?, ?> testResolver = new TestTypeResolver();
		CommandArgs commandArgs = new CommandArgs(args, testResolver);
		
		Optional<Pattern> value = commandArgs.getArg(Pattern.class, 0);
		assertFalse(value.isPresent());
	}
	
	@Test
	public void noValueTest() {
		List<String> args = new ArrayList<>();
		args.add("a");
		TypeResolver<?, ?> testResolver = new TestTypeResolver();
		CommandArgs commandArgs = new CommandArgs(args, testResolver);
		assertTrue(commandArgs.getArg(0).isPresent());
		
		Optional<Integer> optionalValue = commandArgs.getArg(Integer.class, 0);
		assertFalse(optionalValue.isPresent());
	}
	
	@Test
	public void sizeTest() {
		List<String> args = new ArrayList<>();
		args.add("1");
		TypeResolver<?, ?> testResolver = new TestTypeResolver();
		CommandArgs commandArgs = new CommandArgs(args, testResolver);
		
		assertTrue(commandArgs.size() == 1);
	}
}