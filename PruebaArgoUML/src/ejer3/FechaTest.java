package ejer3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class FechaTest {
	static Fecha f;
	
	@BeforeAll
	static void instanciarTest() {
		f = new Fecha();
		System.out.println("a");
	}
	
	@Test
	void testDevuelveFecha1() {
		assertEquals(f.devuelveFecha(1),"2020/02");
	}
	
	@Test
	void testDevuelveFecha2() {
		assertEquals(f.devuelveFecha(2),"02/2020");
	}
	
	@Test
	void testDevuelveFecha3() {
		assertEquals(f.devuelveFecha(3),"02/20");
	}
	
	@Test
	void testDevuelveFecha4() {
		assertEquals(f.devuelveFecha(4),"ERROR");
	}
	
	@Test
	void testDevuelveFecha5() {
		assertEquals(f.devuelveFecha(0),"ERROR");
	}
}
