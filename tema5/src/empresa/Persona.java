package empresa;

public class Persona {
	private String nombre, dni;
	private int altura, peso;
	
	public Persona(String nombre, String dni, int altura, int peso){
		this.nombre = nombre;
		this.dni = dni;
		this.altura = altura;
		this.peso = peso;
	}
	
	public int getAltura() {
		return altura;
	}
	
	public String getDni() {
		return dni;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getPeso() {
		return peso;
	}
	
	public Persona(String dni) {
		this.dni = dni;
		nombre = "NO DEFINIDO";
		altura = 0;
		peso = 0;
	}
	
	@Override
	public String toString() {
		return nombre + ", " + peso + "Kg";
	}
	
}
