package mytest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DivisionTest {

	@Test
	void testDivision_ExcepNull() {
		Division div = new Division(5.0, null);
		try {
			div.division();
			fail("Deberia haberse ejecutado una excepcion");
		} catch (java.lang.IllegalArgumentException e) {
			//Successful test
		}
	}
	
	@Test
	void testDivision_ExcepNull_v2() {
		Division div = new Division(null, 3.0);
		try {
			div.division();
			fail("Deberia haberse ejecutado una excepcion");
		} catch (java.lang.IllegalArgumentException e) {
			//Successful test
		}
	}
	
	@Test
	void testDivision_ExcepZeroDiv() {
		Division div = new Division(5.0, 0.0);
		try {
			div.division();
			fail("Deberia haberse ejecutado una excepcion");
		} catch (java.lang.ArithmeticException e) {
			//Successful test
		}
	}
	
	@Test
	void testDivision() {
		Division div = new Division(6.0, 2.0);
		assertEquals(3, (int) div.division());
	}
	
	@Test
	void testDivision_negat() {
		Division div = new Division(-6.0, 2.0);
		assertEquals(-3, (int) div.division());
	}
	
	@Test
	void testDivision_0() {
		Division div = new Division(0.0, 2.0);
		assertEquals(0, (int) div.division());
	}
}
