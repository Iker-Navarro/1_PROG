package pack3;

public class Pelicula {
	private String nombre, anio;
	private int duracion;
	
	public Pelicula(String nombre, String anio, int duracion) {
		this.nombre = nombre;
		this.anio = anio;
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return "Pelicula [nombre=" + nombre + ", año=" + anio + ", duracion=" + duracion + "]";
	}
	
	public boolean esIgual(Pelicula other){
		if (this.nombre.equalsIgnoreCase(other.nombre) && this.anio == other.anio && Math.abs(this.duracion - other.duracion) <= 5)
			return true;
		return false;
	}
}
