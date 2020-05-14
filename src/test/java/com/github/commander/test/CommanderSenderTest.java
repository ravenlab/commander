package com.github.commander.test;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.github.commander.test.platform.TestCommanderSender;
import com.github.commander.test.platform.TestSender;

public class CommanderSenderTest {

	@Test
	public void getNativeTest() {
		TestSender testSender = new TestSender("test");
		TestCommanderSender commanderSender = new TestCommanderSender(testSender);
		assertTrue(commanderSender.getNative().equals(testSender));
	}
}