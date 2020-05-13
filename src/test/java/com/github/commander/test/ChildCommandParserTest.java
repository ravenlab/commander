package com.github.commander.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.GrandChildCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.parser.ChildCommandParser;
import com.github.ravenlab.commander.command.parser.ParserData;

public class ChildCommandParserTest {

	@Test
	public void testDepthZeroNoArg() {
		CommanderCommand parent = new ParentCommand();
		ChildCommandParser parser = new ChildCommandParser();
		ParserData data = parser.parse(parent, new String[0]);
		assertTrue(data.getCommand().getData().get().getName().equals("parent"));
		assertTrue(data.getArgs().length == 0);
	}
	
	@Test
	public void testDepthZeroWithArg() {
		CommanderCommand parent = new ParentCommand();
		ChildCommandParser parser = new ChildCommandParser();
		ParserData data = parser.parse(parent, new String[] {"test"});
		assertTrue(data.getCommand().getData().get().getName().equals("parent"));
		assertTrue(data.getArgs().length == 1);
		assertTrue(data.getArgs()[0].equals("test"));
	}

	@Test
	public void testDepthOneNoArg() {
		CommanderCommand parent = new ParentCommand();
		parent.addChild(new ChildCommand());
		ChildCommandParser parser = new ChildCommandParser();
		ParserData data = parser.parse(parent, new String[] {"child"});
		assertTrue(data.getCommand().getData().get().getName().equals("child"));
		assertTrue(data.getArgs().length == 0);
	}
	
	@Test
	public void testDepthOneWithArg() {
		CommanderCommand parent = new ParentCommand();
		parent.addChild(new ChildCommand());
		ChildCommandParser parser = new ChildCommandParser();
		ParserData data = parser.parse(parent, new String[] {"child", "test"});
		assertTrue(data.getCommand().getData().get().getName().equals("child"));
		assertTrue(data.getArgs().length == 1);
		assertTrue(data.getArgs()[0].equals("test"));
	}
	
	@Test
	public void testDeptTwoNoArg() {
		CommanderCommand parent = new ParentCommand();
		CommanderCommand child = new ChildCommand();
		CommanderCommand grandChild = new GrandChildCommand();
		parent.addChild(child);
		child.addChild(grandChild);
		ChildCommandParser parser = new ChildCommandParser();
		ParserData data = parser.parse(parent, new String[] {"child", "grandchild"});
		assertTrue(data.getCommand().getData().get().getName().equals("grandchild"));
		assertTrue(data.getArgs().length == 0);
	}
	
	@Test
	public void testDeptTwoWithArg() {
		CommanderCommand parent = new ParentCommand();
		CommanderCommand child = new ChildCommand();
		CommanderCommand grandChild = new GrandChildCommand();
		parent.addChild(child);
		child.addChild(grandChild);
		ChildCommandParser parser = new ChildCommandParser();
		ParserData data = parser.parse(parent, new String[] {"child", "grandchild", "test"});
		assertTrue(data.getCommand().getData().get().getName().equals("grandchild"));
		assertTrue(data.getArgs().length == 1);
		assertTrue(data.getArgs()[0].equals("test"));
	}
}