package ocio;

public class Jugador implements Premiable{
	private static int jugadoresSinPremios = 0;
	
	private String nombre;
	private int numPremios;
	private boolean premiado;
	
	public Jugador(String nombre, int numPremios) {
		this.nombre = compactaNombre(nombre);
		this.numPremios = numPremios;
		if(numPremios == 0) {
			jugadoresSinPremios++;
			premiado = false;
		}
		else {
			premiado = true;
		}
	}
	
	public int getNumPremios() {
		return numPremios;
	}
	
	public String getNombre() {
		return nombre;
	}

	private static String compactaNombre(String nombre) {
		String returnString = "";
		nombre = nombre.toUpperCase();
		char anterior = ' ';
		for(int i = 0; i < nombre.length(); i++) {
			char actual = nombre.charAt(i);
			if((actual <= 'Z' && actual >= 'A') && anterior != actual) {
				returnString += actual;
				anterior = actual;
			}
		}
		return returnString;
	}

	@Override
	public void premiar() {
		if(!premiado) {
			premiado = true;
			jugadoresSinPremios--;
		}
		numPremios++;
		System.out.println("Enhorabuena. Es el premio numero " + numPremios);
	}
	
	public static void verJugadoresSinPremios() {
		System.out.println(jugadoresSinPremios);
	}
	
	@Override
	public String toString() {
		return "Jugador: " + nombre + ", Premios: " + numPremios;
	}
	
}
