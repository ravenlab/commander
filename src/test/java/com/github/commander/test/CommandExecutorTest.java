package com.github.commander.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.commander.test.platform.TestCommanderSender;
import com.github.commander.test.platform.TestSender;
import com.github.commander.test.resolver.TestTypeResolver;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.CommanderExecutor;
import com.github.ravenlab.commander.resolver.TypeResolver;

public class CommandExecutorTest {

	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testExecutionWithUsage() {
		TypeResolver<?, ?> testResolver = new TestTypeResolver();
		TestSender testSender = new TestSender("test");
		testSender.addPermission("parent.use", true);
		CommanderCommand<TestSender> command = new ParentCommand<>();
		CommanderExecutor<CommanderCommand<TestSender>> executor = new CommanderExecutor(command, testResolver);
		TestCommanderSender commanderSender = new TestCommanderSender(testSender);
		executor.execute(commanderSender, "parent", new String[]{"fail"});
		List<String> messages = testSender.getReceivedMessages();
		assertTrue(messages.size() == 1);
		assertTrue(messages.get(0).equals("/parent"));
	}
	
	@Test
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void testExecutionNoUsage() {
		TypeResolver<?, ?> testResolver = new TestTypeResolver();
		TestSender testSender = new TestSender("test");
		CommanderCommand<TestSender> command = new ChildCommand<>();
		CommanderExecutor<CommanderCommand<TestSender>> executor = new CommanderExecutor(command, testResolver);
		TestCommanderSender commanderSender = new TestCommanderSender(testSender);
		executor.execute(commanderSender, "child", new String[]{"fail"});
		List<String> messages = testSender.getReceivedMessages();
		assertTrue(messages.size() == 0);
	}
}