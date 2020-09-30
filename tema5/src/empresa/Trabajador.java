package empresa;

public class Trabajador extends Persona{
	
	private double dinero;
	
	private TelefonoMovil telefono;
	
	public Trabajador(String nombre, String dni, int altura, int peso, double dinero, TelefonoMovil telefono) {
		super(nombre, dni, altura, peso);
		this.dinero = dinero;
		this.telefono = telefono;
	}
	
	void ganarDinero(double dinero) {
		this.dinero += dinero;
	}
	
	void gastarDinero(double dinero){
		this.dinero -= dinero;
	}
	
	void trabajar() {
		telefono.usar();
		ganarDinero(10);
	}
	
	void verDatos() {
		System.out.println("Nombre: " + getNombre());
		System.out.println("DNI: " + getDni());
		System.out.println("Altura: " + getAltura());
		System.out.println("Peso " + getPeso());
		System.out.println("Dinero: " + dinero);
		System.out.println("Telefono:  " + telefono.getNumero());
		System.out.println("Bateria:  " + telefono.getBateria());
		
	}
	
	@Override
	public String toString() {
		return super.toString() + ", " + dinero + "€";
	}
}
