package com.github.commander.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.GrandChildCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.commander.test.platform.TestSender;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.parser.ChildCommandParser;
import com.github.ravenlab.commander.command.parser.ChildCommandParserData;

public class ChildCommandParserTest {

	@Test
	public void testDepthZeroNoArg() {
		CommanderCommand<TestSender> parent = new ParentCommand<>();
		ChildCommandParser<TestSender> parser = new ChildCommandParser<>();
		ChildCommandParserData<TestSender> data = parser.parse(parent, new String[0]);
		assertTrue(data.getCommand().getData().get().getName().equals("parent"));
		assertTrue(data.getArgs().length == 0);
	}
	
	@Test
	public void testDepthZeroWithArg() {
		CommanderCommand<TestSender> parent = new ParentCommand<>();
		ChildCommandParser<TestSender> parser = new ChildCommandParser<>();
		ChildCommandParserData<TestSender> data = parser.parse(parent, new String[] {"test"});
		assertTrue(data.getCommand().getData().get().getName().equals("parent"));
		assertTrue(data.getArgs().length == 1);
		assertTrue(data.getArgs()[0].equals("test"));
	}

	@Test
	public void testDepthOneNoArg() {
		CommanderCommand<TestSender> parent = new ParentCommand<>();
		parent.addChild(new ChildCommand<>());
		ChildCommandParser<TestSender> parser = new ChildCommandParser<>();
		ChildCommandParserData<TestSender> data = parser.parse(parent, new String[] {"child"});
		assertTrue(data.getCommand().getData().get().getName().equals("child"));
		assertTrue(data.getArgs().length == 0);
	}
	
	@Test
	public void testDepthOneWithArg() {
		CommanderCommand<TestSender> parent = new ParentCommand<>();
		parent.addChild(new ChildCommand<>());
		ChildCommandParser<TestSender> parser = new ChildCommandParser<>();
		ChildCommandParserData<TestSender> data = parser.parse(parent, new String[] {"child", "test"});
		assertTrue(data.getCommand().getData().get().getName().equals("child"));
		assertTrue(data.getArgs().length == 1);
		assertTrue(data.getArgs()[0].equals("test"));
	}
	
	@Test
	public void testDeptTwoNoArg() {
		CommanderCommand<TestSender> parent = new ParentCommand<>();
		CommanderCommand<TestSender> child = new ChildCommand<>();
		CommanderCommand<TestSender> grandChild = new GrandChildCommand<>();
		parent.addChild(child);
		child.addChild(grandChild);
		ChildCommandParser<TestSender> parser = new ChildCommandParser<>();
		ChildCommandParserData<TestSender> data = parser.parse(parent, new String[] {"child", "grandchild"});
		assertTrue(data.getCommand().getData().get().getName().equals("grandchild"));
		assertTrue(data.getArgs().length == 0);
	}
	
	@Test
	public void testDeptTwoWithArg() {
		CommanderCommand<TestSender> parent = new ParentCommand<>();
		CommanderCommand<TestSender> child = new ChildCommand<>();
		CommanderCommand<TestSender> grandChild = new GrandChildCommand<>();
		parent.addChild(child);
		child.addChild(grandChild);
		ChildCommandParser<TestSender> parser = new ChildCommandParser<>();
		ChildCommandParserData<TestSender> data = parser.parse(parent, new String[] {"child", "grandchild", "test"});
		assertTrue(data.getCommand().getData().get().getName().equals("grandchild"));
		assertTrue(data.getArgs().length == 1);
		assertTrue(data.getArgs()[0].equals("test"));
	}
}