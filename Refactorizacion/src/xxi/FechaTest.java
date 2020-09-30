package xxi;

import junit.framework.TestCase;

public class FechaTest extends TestCase {

	private Fecha fechaCorrecta = new Fecha (20, 6, 2008);
	private Fecha mesMal1 = new Fecha (21, 0, 3000);
	private Fecha mesMal2 = new Fecha (21, 13, 3000);
	private Fecha diaMal1 = new Fecha (0, 11, 2000);
	private Fecha diaMal2 = new Fecha (32, 11, 2000);
	private Fecha diaMalNoviembre = new Fecha (31, 11, 2000);
	private Fecha diaBienDiciembre = new Fecha (31, 12, 2000);
	private Fecha diaMalFebrero = new Fecha (30, 2, 2008);
	private Fecha diaBienFebreroBisiesto1 = new Fecha (29, 2, 2008);
	private Fecha diaBienFebreroBisiesto2 = new Fecha (29, 2, 2000);
	private Fecha diaMalFebreroBisiesto1 = new Fecha (29, 2, 2007);
	private Fecha diaMalFebreroBisiesto2 = new Fecha (29, 2, 1900);

	public void testValida( ) {
		assertTrue(fechaCorrecta.valida());
		assertFalse(mesMal1.valida());
		assertFalse(mesMal2.valida());
		assertFalse(diaMal1.valida());
		assertFalse(diaMal2.valida());
		assertFalse(diaMalNoviembre.valida());
		assertTrue(diaBienDiciembre.valida());
		assertFalse(diaMalFebrero.valida());
		assertTrue(diaBienFebreroBisiesto1.valida());
		assertTrue(diaBienFebreroBisiesto2.valida());
		assertFalse(diaMalFebreroBisiesto1.valida());
		assertFalse(diaMalFebreroBisiesto2.valida());
	}
}
