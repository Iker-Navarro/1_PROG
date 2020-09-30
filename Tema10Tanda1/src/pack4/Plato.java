package pack4;


public class Plato {
	private String nombre, descripcion;
	private Double precio, calorias;
	
	public Plato(String nombre, String descripcion, Double precio, Double calorias) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.calorias = calorias;
	}
	
	@Override
	public String toString() {
		return nombre + " (" + calorias + "cal):\n" + descripcion + "\n" + precio + "€";
	}

	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public Double getCalorias() {
		return calorias;
	}	
	
}
