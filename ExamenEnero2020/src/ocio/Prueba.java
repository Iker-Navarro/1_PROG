package ocio;

public class Prueba {
	public static void main(String[] args) {
		Jugador j1 = new Jugador("IKERR NAVVARRO2", 0);
		
		Ahorcado ah1 = new Ahorcado(366, j1, "PANTALLA");
		
		System.out.println(ah1.jugar()?"Victoria":"Derrota");
		System.out.println(ah1);
		System.out.println(Ahorcado.getJuegosExitosos());
		
		
		JuegoMatematico jm1 = new JuegoMatematico(354);
		System.out.println(jm1.jugar()?"Victoria":"Derrota");
		System.out.println(jm1);
		JuegoMatematico jm2 = new JuegoMatematico(2003, j1, 1);
		System.out.println(jm2.jugar()?"Victoria":"Derrota");
		System.out.println(jm2);
		
	}
}
