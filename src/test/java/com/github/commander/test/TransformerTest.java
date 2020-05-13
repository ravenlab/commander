package com.github.commander.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.github.ravenlab.commander.transform.DoubleTransformer;
import com.github.ravenlab.commander.transform.IntegerTransformer;

public class TransformerTest {

	@Test
	public void doubleTransformerTestInvalid() {
		DoubleTransformer transformer = new DoubleTransformer();
		Optional<Double> value = transformer.transform("a");
		assertFalse(value.isPresent());
	}
	
	@Test
	public void doubleTransformerTestValid() {
		DoubleTransformer transformer = new DoubleTransformer();
		Optional<Double> value = transformer.transform("1.0");
		assertTrue(value.isPresent());
		assertTrue(value.get() == 1.0);
	}
	
	@Test
	public void integerTransformerTestInvalid() {
		IntegerTransformer transformer = new IntegerTransformer();
		Optional<Integer> value = transformer.transform("a");
		assertFalse(value.isPresent());
	}
	
	@Test
	public void integerTransformerTestValid() {
		IntegerTransformer transformer = new IntegerTransformer();
		Optional<Integer> value = transformer.transform("1");
		assertTrue(value.isPresent());
		assertTrue(value.get() == 1);
	}
}