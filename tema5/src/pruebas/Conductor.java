package pruebas;

public class Conductor {
	private String nombre;
	private int edad;
	
	public Conductor(String nombre, int edad) {
		this.nombre = nombre;
		this.edad = edad;
	}
	
	/*public void ver() {
		System.out.println("nombre: " + nombre);
		System.out.println("edad: " + edad);
	}*/
	
	public int getEdad() {
		return edad;
	}
	
	public String toString() {
		return "nombre: " + nombre + " edad: " + edad;
	}
	
}
