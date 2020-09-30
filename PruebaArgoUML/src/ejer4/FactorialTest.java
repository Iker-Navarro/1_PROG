package ejer4;


import static org.junit.Assert.*;
import org.junit.Test;

public class FactorialTest {

	@Test
	public void testCalculo() {
		assertEquals(120, Factorial.calculo(5));
	}
	
	@Test(expected = java.lang.IllegalArgumentException.class)
	public void testCalculoException() {
		int resultado = Factorial.calculo(-4);
	}

}
