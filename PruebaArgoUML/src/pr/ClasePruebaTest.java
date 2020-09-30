package pr;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ClasePruebaTest {
	@Test
	void testDivision() {
		ClasePrueba pr = new ClasePrueba(6.0, 2.0);
		assertEquals(3,(int) pr.division(1));
	}
	
	@Test
	void testDivision2() {
		ClasePrueba pr = new ClasePrueba(6.0, 3.0);
		assertEquals(0.5, pr.division(2));
	}
	
	@Test
	void testDivision_rang() {
		ClasePrueba pr = new ClasePrueba(6.0, 2.0);
		try {
			pr.division(3);
			fail("No ha saltado la excepción esperada");
		} catch (java.lang.IllegalArgumentException e) {
			// TODO: handle exception
		}
	}
	
	@Test
	void testDivision3() {
		ClasePrueba pr = new ClasePrueba(6.0, 0.0);
		try {
			pr.division(1);
			fail("No ha saltado la excepción esperada");
		} catch (java.lang.ArithmeticException e) {
			// TODO: handle exception
		}
	}
	
	@Test
	void testDivision4() {
		ClasePrueba pr = new ClasePrueba(6.0, 0.0);
		assertEquals(0.0,(int) pr.division(2));
	}
	
	@Test
	void testDivision5() {
		ClasePrueba pr = new ClasePrueba(0.0, 6.0);
		try {
			pr.division(2);
			fail("No ha saltado la excepción esperada");
		} catch (java.lang.ArithmeticException e) {
			// TODO: handle exception
		}
	}
	
	@Test
	void testDivision6() {
		ClasePrueba pr = new ClasePrueba(0.0, 6.0);
		assertEquals(0.0,(int) pr.division(1));
	}
	
	@Test
	void testDivision7() {
		ClasePrueba pr = new ClasePrueba(null, 6.0);
		try {
			pr.division(2);
			fail("No ha saltado la excepción esperada");
		} catch (java.lang.IllegalArgumentException e) {
			// TODO: handle exception
		}
	}
	
}
