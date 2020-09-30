package ocio;

public class Ahorcado extends Juego {
	private static int juegosExitosos = 0;
	
	private String palabra, mostrado = "";
	
	public Ahorcado(int duracion, Jugador j, String palabra) {
		super(duracion, j);
		this.palabra = palabra.toUpperCase();
		for(int i = 0; i < palabra.length(); i++) {
			mostrado += "_ ";
		}
	}
	
	public static int getJuegosExitosos() {
		return juegosExitosos;
	}
	
	@Override
	public boolean jugar() {
		int numIntentos = 0, vidas = getJ().getNumPremios() > 5 ? palabra.length()/2 : palabra.length();
		char intento;
		
		System.out.println("--AHORCADO--");
		System.out.println("Comenzando partida para el jugador " + getJ().getNombre());
		System.out.println("Vidas disponibles = " + vidas);
		System.out.println("\nPALABRA = " + mostrado + "\n");
		
		while(vidas > 0 && mostrado.indexOf("_") != -1 ) {
			numIntentos++;
			System.out.println("INTENTO NUMERO " + numIntentos);
			System.out.println("vidas restantes: " + vidas);
			
			intento = pideLetra();
			
			if(palabra.indexOf(intento) == -1) {
				vidas--;
			}
			else {
				actualizarMostrado(intento);
			}
			System.out.println(mostrado);
		}
		System.out.println("FIN DEL JUEGO");
		if(vidas == 0 ) {
			System.out.println("HAS AGOTADO TODOS TUS INTENTOS");
			return false;
		}
		else {
			System.out.println("VICTORIA\nAcertado en " + numIntentos + " intentos.");
			getJ().premiar();
			juegosExitosos++;
			return true;
		}
	}
	
	private static char pideLetra() {
		char letra = ' ';
		while(letra < 'A' || letra > 'Z' ) {
			System.out.print(">>>");
			letra = Character.toUpperCase(Consola.leeChar());
		}
		return letra;
	}
	
	private void actualizarMostrado(char intento) {
		String nuevoMostrado = "";
		for(int i = 0; i < palabra.length(); i++) {
			if(mostrado.charAt(i*2) == '_' && palabra.charAt(i) == intento)
				nuevoMostrado += palabra.charAt(i) + " ";
			else
				nuevoMostrado += mostrado.charAt(i*2) + " ";
		}
		mostrado = nuevoMostrado;
	}
	
	@Override
	public String toString() {
		return super.toString() + "Palabra a adivinar: " + palabra;
	}
}