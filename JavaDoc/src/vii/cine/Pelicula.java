package vii.cine;

/**
 * <h2>Clase: Pelicula</h2>
 * Clase utilizada para almacenar información sobre una película concreta.
 * Utilizada principalmente por la clase Cine
 * 
 * @see vii.cine.Cine
 * @author Iker
 * @version v1
 */

public class Pelicula {
	
	/**Nombre de la película*/
	private String nombre;
	/**Duración de la película.*/
	private int duracion;
	/** Año de producción*/
	private int anio;
	
	/**
	 * Constructor que inicializa una nueva instancia con los parámetros dados
	 * @param nombre nombre de película
	 * @param anio Año de producción
	 * @param duracion Duración de la película
	 */
	
	public Pelicula(String nombre, int anio, int duracion) {
		this.nombre = nombre;
		this.anio = anio;
		this.duracion = duracion;
	}

	@Override
	public String toString() {
		return nombre;
	}
	
	/**
	 * Método para comparar película.
	 * @param other película con la que comparar.
	 * @return devuelve si las películas son iguales o no.
	 */
	
	public boolean esIgual(Pelicula other) {
		if (this == null || other == null)
			return false;
		if(this.nombre.equalsIgnoreCase(other.nombre) && this.anio == other.anio && Math.abs(this.duracion - other.duracion) <= 5)
			return true;
		return false;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public int getDuracion() {
		return duracion;
	}
}
