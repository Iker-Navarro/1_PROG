package pack3;

public class Persona {
	private String nombre, tlf, lugar;
	private int edad;
	
	public Persona(String nombre, String tlf, String lugar, int edad) {
		this.nombre = nombre;
		this.tlf = tlf;
		this.lugar = lugar;
		this.edad = edad;
	}

	@Override
	public String toString() {
		return tlf + "\t" + edad + "\t" + nombre + "\t" + lugar;
	}
}
