package pack2;

public class Pato {
	private String nombre;
	private int edad;
	
	public Pato(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}

	@Override
	public String toString() {
		return nombre + ", " + edad + " años";
	}
	
	
}
