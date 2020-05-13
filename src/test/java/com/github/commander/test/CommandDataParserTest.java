package com.github.commander.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Optional;

import org.junit.Test;

import com.github.commander.test.command.ChildCommand;
import com.github.commander.test.command.NoAnnotationCommand;
import com.github.commander.test.command.ParentCommand;
import com.github.ravenlab.commander.command.CommandData;
import com.github.ravenlab.commander.command.CommanderCommand;
import com.github.ravenlab.commander.command.parser.CommandDataParser;
import com.github.ravenlab.commander.util.ChatColor;

public class CommandDataParserTest {

	@Test
	public void testName() {
		CommanderCommand parent = new ParentCommand();
		CommandData data = parent.getData().get();
		assertTrue(data.getName().equals("parent"));
	}
	
	@Test
	public void testAliases() {
		CommanderCommand parent = new ParentCommand();
		CommandData data = parent.getData().get();
		Collection<String> aliases = data.getAliases();
		assertTrue(aliases.size() == 3);
		assertTrue(aliases.contains("parent"));
		assertTrue(aliases.contains("mother"));
		assertTrue(aliases.contains("father"));
	}
	
	@Test
	public void testNoPermission() {
		CommanderCommand child = new ChildCommand();
		CommandData data = child.getData().get();
		String permission = data.getPermission();
		assertTrue(permission.equals(""));
	}
	
	@Test
	public void testPermission() {
		CommanderCommand parent = new ParentCommand();
		CommandData data = parent.getData().get();
		String permission = data.getPermission();
		assertTrue(permission.equals("parent.use"));
	}
	
	@Test
	public void testNoPermissionMessage() {
		CommanderCommand child = new ChildCommand();
		CommandData data = child.getData().get();
		String permissionMessage = data.getNoPermissionMessage();
		String checkAgainst = ChatColor.translateAlternateColorCodes(CommandDataParser.NO_PERMISSION_MESSAGE);
		assertTrue(permissionMessage.equals(checkAgainst));
	}
	
	@Test
	public void testPermissionMessage() {
		CommanderCommand parent = new ParentCommand();
		CommandData data = parent.getData().get();
		String permissionMessage = data.getNoPermissionMessage();
		String checkAgainst = ChatColor.translateAlternateColorCodes("&cNo permission for that action");
		assertTrue(permissionMessage.equals(checkAgainst));
	}
	
	@Test
	public void testNoAnnotation() {
		CommanderCommand parent = new NoAnnotationCommand();
		Optional<CommandData> data = parent.getData();
		assertFalse(data.isPresent());
	}
}