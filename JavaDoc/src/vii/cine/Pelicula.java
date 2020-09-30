package vii.cine;

/**
 * <h2>Clase: Pelicula</h2>
 * Clase utilizada para almacenar informaci�n sobre una pel�cula concreta.
 * Utilizada principalmente por la clase Cine
 * 
 * @see vii.cine.Cine
 * @author Iker
 * @version v1
 */

public class Pelicula {
	
	/**Nombre de la pel�cula*/
	private String nombre;
	/**Duraci�n de la pel�cula.*/
	private int duracion;
	/** A�o de producci�n*/
	private int anio;
	
	/**
	 * Constructor que inicializa una nueva instancia con los par�metros dados
	 * @param nombre nombre de pel�cula
	 * @param anio A�o de producci�n
	 * @param duracion Duraci�n de la pel�cula
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
	 * M�todo para comparar pel�cula.
	 * @param other pel�cula con la que comparar.
	 * @return devuelve si las pel�culas son iguales o no.
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
