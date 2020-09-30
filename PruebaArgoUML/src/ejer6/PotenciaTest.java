package ejer6;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PotenciaTest {

	@Test
	void testPovOf2() {
		assertEquals("256", Potencia.povOf2(8));
	}
	
	@Test
	void testPovOf2_v2() {
		assertEquals("1048576", Potencia.povOf2(20));
	}
	
	@Test
	void testPovOf2_v3() {
		assertEquals("1125899906842624", Potencia.povOf2(50));
	}
	
	@Test
	void testPovOf2_0() {
		assertEquals("1", Potencia.povOf2(0));
	}
	
	@Test
	void testPovOf2_negativ() {
		assertEquals("0.5", Potencia.povOf2(-1));
	}
}
