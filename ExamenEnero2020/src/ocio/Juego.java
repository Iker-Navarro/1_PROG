package ocio;

public abstract class Juego {
	private int duracion;
	private Jugador j;
	
	public Juego(int duracion) {
		this.duracion = duracion;
	}
	
	public Juego(int duracion, Jugador j) {
		this.duracion = duracion;
		this.j = j;
	}
	
	public Jugador getJ() {
		return j;
	}
	
	@Override
	public String toString() {
		int t = duracion;
		String tiempoDesglosado = "";
		
		tiempoDesglosado += t%60 + "s ";
		t/=60;
		tiempoDesglosado += t%60 + "m ";
		t/=60;
		tiempoDesglosado += t%60 + "h ";
		
		return "Duracion: " + tiempoDesglosado + "\nJugador: " + getJ()+ "\n";
	}
	
	public abstract boolean jugar();
}
