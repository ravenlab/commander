package com.github.commander.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Optional;

import org.junit.Test;

import com.github.ravenlab.commander.util.ChatColor;

public class ChatColorTest {

	@Test
	public void isColorTest() {
		assertTrue(ChatColor.RED.isColor());
	}
	
	@Test
	public void colorToStringTest() {
		assertTrue(ChatColor.RED.toString().equals(ChatColor.FORMATTING_CODE + "c"));
	}
	
	@Test
	public void isFormattingTest() {
		assertTrue(ChatColor.BOLD.isFormatting());
	}
	
	@Test
	public void isNotFormattingTest() {
		assertFalse(ChatColor.BOLD.isColor());
	}
	
	@Test
	public void rgbTest() {
		ChatColor color = ChatColor.RED;
		int red = color.getRed();
		int green = color.getGreen();
		int blue = color.getBlue();
		assertTrue(red == 255);
		assertTrue(green == 85);
		assertTrue(blue == 85);
	} 
	
	@Test
	public void javaColorTest() {
		ChatColor color = ChatColor.RED;
		Optional<Color> javaColor = color.getJavaColor();
		assertTrue(javaColor.isPresent());
	} 
	
	@Test
	public void javaNoColorTest() {
		ChatColor color = ChatColor.BOLD;
		Optional<Color> javaColor = color.getJavaColor();
		assertFalse(javaColor.isPresent());
	} 
	
	@Test
	public void translateTest() {
		String message = ChatColor.translateAlternateColorCodes("&c");
		assertTrue(message.equals(ChatColor.FORMATTING_CODE + "c"));
	} 
	
	@Test
	public void translateTestAndAtEnd() {
		String message = ChatColor.translateAlternateColorCodes("&c&");
		assertTrue(message.equals(ChatColor.FORMATTING_CODE + "c&"));
	} 
}