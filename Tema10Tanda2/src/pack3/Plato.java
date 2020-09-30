package pack3;

public class Plato {
	
	private String nombre;
	private double precio;
	private String imagen;
	
	public Plato(String nombre, double precio, String imagen) {
		this.nombre = nombre;
		this.precio = precio;
		this.imagen = imagen;
	}
	
	public String getNombre() {
		return nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public String getImagen() {
		return imagen;
	}
	
}