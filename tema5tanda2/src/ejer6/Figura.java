package ejer6;

public abstract class Figura {
	private String nombre;
	
	public Figura(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public abstract double calcularArea();
	public abstract boolean esRegular();
}
