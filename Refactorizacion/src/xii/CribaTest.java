package xii;

import junit.framework.*;   //  JUnit

// Clase con casos de prueba para Criba

public class CribaTest extends TestCase {

	//  Programa principal (usa un componente de Junit

	public static void main (String args[])
	{
	/*	junit.swingui.TestRunner.main (
				new String[] {"CribaTest"}); */
	}

	
	// Constructor
	
	public CribaTest (String nombre) {
		super(nombre);
	}
	
	// Casos de prueba
	
	public void testPrimos()
	{
		int[] nullArray = Criba.generarPrimos(0);
		assertEquals(nullArray.length, 0);
		
		int[] minArray = Criba.generarPrimos(2);
		assertEquals(minArray.length, 1);
		assertEquals(minArray[0], 2);
		
		int[] threeArray = Criba.generarPrimos(3);
		assertEquals(threeArray.length, 2);
		assertEquals(threeArray[0], 2);
		assertEquals(threeArray[1], 3);
		
		int[] centArray = Criba.generarPrimos(100);
		assertEquals(centArray.length, 25);
		assertEquals(centArray[24], 97);
	}
}