package ejer2;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class MayorNumeroTest {
	
	@Test
	void testObt_mayorNumero1() {
		assertEquals(9, MayorNumero.obt_mayorNumero(new int[] {3, 7, 9, 8}));
	}
	
	@Test
	void testObt_mayorNumero2() {
		assertEquals(9, MayorNumero.obt_mayorNumero(new int[] {9, 7, 8}));
		assertEquals(9, MayorNumero.obt_mayorNumero(new int[] {7, 9, 8}));
		assertEquals(9, MayorNumero.obt_mayorNumero(new int[] {7, 8, 9}));
	}
	
	@Test
	void testObt_mayorNumero3() {
		assertEquals(9, MayorNumero.obt_mayorNumero(new int[] {9, 7, 9, 8}));
	}
	
	@Test
	void testObt_mayorNumero4() {
		assertEquals(3, MayorNumero.obt_mayorNumero(new int[] {3}));
	}
	
	@Test
	void testObt_mayorNumero5() {
		assertEquals(-1, MayorNumero.obt_mayorNumero(new int[] {-22, -4, -1, -2}));
	}
}
