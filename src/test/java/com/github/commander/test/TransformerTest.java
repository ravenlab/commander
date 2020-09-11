package com.github.commander.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.github.commander.test.testenum.Status;
import com.github.ravenlab.commander.transform.BooleanTransformer;
import com.github.ravenlab.commander.transform.DoubleTransformer;
import com.github.ravenlab.commander.transform.EnumTransformer;
import com.github.ravenlab.commander.transform.FloatTransformer;
import com.github.ravenlab.commander.transform.IntegerTransformer;
import com.github.ravenlab.commander.transform.LongTransformer;

public class TransformerTest {

	@Test
	public void booleanTransformerTestInvalid() {
		BooleanTransformer transformer = new BooleanTransformer();
		Optional<Boolean> value = transformer.transform(Boolean.class, "a");
		assertFalse(value.isPresent());
	}
	
	@Test
	public void booleanTransformerTestValid() {
		BooleanTransformer transformer = new BooleanTransformer();
		Optional<Boolean> trueValue = transformer.transform(Boolean.class, "true");
		Optional<Boolean> falseValue = transformer.transform(Boolean.class, "false");
		assertTrue(trueValue.isPresent());
		assertTrue(trueValue.get());
		assertTrue(falseValue.isPresent());
		assertFalse(falseValue.get());
	}
	
	@Test
	public void booleanTransformerNoResolverTest() {
		BooleanTransformer transformer = new BooleanTransformer();
		assertFalse(transformer.getResolver().isPresent());
	}
	
	@Test
	public void doubleTransformerTestInvalid() {
		DoubleTransformer transformer = new DoubleTransformer();
		Optional<Double> value = transformer.transform(Double.class, "a");
		assertFalse(value.isPresent());
	}
	
	@Test
	public void doubleTransformerTestValid() {
		DoubleTransformer transformer = new DoubleTransformer();
		Optional<Double> value = transformer.transform(Double.class, "1.0");
		assertTrue(value.isPresent());
		assertTrue(value.get() == 1.0);
	}
	
	@Test
	public void doubleTransformerNoResolverTest() {
		DoubleTransformer transformer = new DoubleTransformer();
		assertFalse(transformer.getResolver().isPresent());
	}
	
	@Test
	public void floatTransformerTestInvalid() {
		FloatTransformer transformer = new FloatTransformer();
		Optional<Float> value = transformer.transform(Float.class, "a");
		assertFalse(value.isPresent());
	}
	
	@Test
	public void floatTransformerTestValid() {
		FloatTransformer transformer = new FloatTransformer();
		Optional<Float> value = transformer.transform(Float.class, "1.0");
		assertTrue(value.isPresent());
		assertTrue(value.get() == 1.0);
	}
	
	@Test
	public void floatTransformerNoResolverTest() {
		FloatTransformer transformer = new FloatTransformer();
		assertFalse(transformer.getResolver().isPresent());
	}
	
	@Test
	public void integerTransformerTestInvalid() {
		IntegerTransformer transformer = new IntegerTransformer();
		Optional<Integer> value = transformer.transform(Integer.class, "a");
		assertFalse(value.isPresent());
	}
	
	@Test
	public void integerTransformerTestValid() {
		IntegerTransformer transformer = new IntegerTransformer();
		Optional<Integer> value = transformer.transform(Integer.class, "1");
		assertTrue(value.isPresent());
		assertTrue(value.get() == 1);
	}
	
	@Test
	public void integerTransformerNoResolverTest() {
		IntegerTransformer transformer = new IntegerTransformer();
		assertFalse(transformer.getResolver().isPresent());
	}
	
	@Test
	public void longTransformerTestInvalid() {
		LongTransformer transformer = new LongTransformer();
		Optional<Long> value = transformer.transform(Long.class, "a");
		assertFalse(value.isPresent());
	}
	
	@Test
	public void longTransformerTestValid() {
		LongTransformer transformer = new LongTransformer();
		Optional<Long> value = transformer.transform(Long.class, "1");
		assertTrue(value.isPresent());
		assertTrue(value.get() == 1);
	}
	
	@Test
	public void longTransformerNoResolverTest() {
		LongTransformer transformer = new LongTransformer();
		assertFalse(transformer.getResolver().isPresent());
	}
	
	//
	@Test
	public void enumTransformerTestInvalid() {
		EnumTransformer<Status> transformer = new EnumTransformer<>();
		Optional<Status> value = transformer.transform(Status.class, "a");
		assertFalse(value.isPresent());
	}
	
	@Test
	public void enumTransformerTestValid() {
		EnumTransformer<Status> transformer = new EnumTransformer<>();
		Optional<Status> value = transformer.transform(Status.class, Status.PASS.toString());
		assertTrue(value.isPresent());
		assertTrue(value.get() == Status.PASS);
	}
	
	@Test
	public void statusTransformerNoResolverTest() {
		EnumTransformer<Status> transformer = new EnumTransformer<>();
		assertFalse(transformer.getResolver().isPresent());
	}
}