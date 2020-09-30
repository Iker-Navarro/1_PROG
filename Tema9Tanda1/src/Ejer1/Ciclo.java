package Ejer1;

public class Ciclo {
	private String nombre, tipo;

	public Ciclo(String nombre, String tipo) {
		this.nombre = nombre;
		this.tipo = tipo;
	}

	@Override
	public String toString() {
		return nombre + "(" + tipo + ")";
	}
}
