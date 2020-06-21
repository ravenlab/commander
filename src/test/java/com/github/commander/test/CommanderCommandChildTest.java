package com.github.commander.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.NoAnnotationCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.commander.test.platform.TestSender;
import com.github.ravenlab.commander.command.CommanderCommand;

public class CommanderCommandChildTest {

	@Test
	public void testNoChildren() {
		CommanderCommand<TestSender> command = new ParentCommand<>();
		assertTrue(command.getChildren().size() == 0);
	}
	
	@Test
	public void testOneChild() {
		CommanderCommand<TestSender> command = new ParentCommand<>();
		command.addChild(new ChildCommand<>());
		assertTrue(command.getChildren().size() == 1);
	}
	
	@Test
	public void testAddChildWithNoAnnotation() {
		CommanderCommand<TestSender> command = new ParentCommand<>();
		command.addChild(new NoAnnotationCommand<>());
		assertTrue(command.getChildren().size() == 0);
	}
	
	@Test
	public void testGetChildByName() {
		CommanderCommand<TestSender> command = new ParentCommand<>();
		command.addChild(new ChildCommand<>());
		assertTrue(command.getChildren().size() == 1);
		assertTrue(command.getChildByName("child").getData().get().getName().equals("child"));
	}
}