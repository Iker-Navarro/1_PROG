package pack1;

public class Vaca {
	private String colorPelo;
	private int edad;
	protected String nombre;
	
	public Vaca() {
		this.colorPelo = "INDEFINIDO";
		this.edad = 0;
		this.nombre = "INDEFINIDO";
		System.out.println("Se ha creado " + this.nombre);
	}

	public Vaca(String nombre, int edad, String colorPelo) {
		this.colorPelo = colorPelo;
		this.edad = edad;
		this.nombre = nombre;
		System.out.println("Se ha creado " + this.nombre);
	}
	
	public Vaca(String nombre, int edad) {
		this.colorPelo = "INDEFINIDO";
		this.edad = edad;
		this.nombre = nombre;
		System.out.println("Se ha creado " + this.nombre);
	}	
	
	public void muu() {
		System.out.println("Muuu... Mi nombre es " + nombre + " y mi color de pelo es " + colorPelo);
	}
	
	public void compararEdad(Vaca other){
		if(this.edad > other.edad) 
			System.out.println("La vaca " + this.nombre + " es más vieja que la vaca " + other.nombre);
		else if (other.edad > this.edad) 
			System.out.println("La vaca " + other.nombre + " es más vieja que la vaca " + this.nombre);
		else
			System.out.println("Las vacas " + this.nombre + " y " + other.nombre + " tienen la misma edad.");
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void sumaEdad(int anios) {
		edad += anios;
	}
	
	
}
