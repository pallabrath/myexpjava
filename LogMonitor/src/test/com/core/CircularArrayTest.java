package test.com.core;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.core.CircularArray;

public class CircularArrayTest {
	@Test
	public void testCircularArray() {
		CircularArray<Integer> ca = new CircularArray<Integer>(5);
		ca.set(3);
		ca.set(4);
		assertEquals(7, ca.sum());
		ca.set(1);
		ca.set(2);
		ca.set(5);
		assertEquals(15, ca.sum());
		ca.set(2);		
		// it should overwrite 3 with 2
		assertEquals(14, ca.sum());
	}	
}
