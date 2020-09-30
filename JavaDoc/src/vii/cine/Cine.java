package vii.cine;

import java.util.ArrayList;

/**
 * <h2>Clase: Cine</h2>
 * Clase encargada de almacenar y gestionar las películas proyectadas en un cine concreto. 
 * 
 * @author Iker
 * @version v1
 */

public class Cine {
	
	/**Nombre del cine*/
	private String nombre;
	/**ArrayList de películas que están siendo proyectadas actualmente*/
	private ArrayList<Pelicula> pelis;
	/**Array de películas proyectadas, el índice del array identifica también la sala en la que está siendo proyectada*/
	private Pelicula[] salasOcupadas;
	
	/**
	 * Constructor que crea un nuevo cine dándole un nombre y la cantidad de salas que dispone.
	 * Inicializa también ambos almacenes de películas (vacios)
	 * @param nombre nombre del cine
	 * @param salas cantidad total de salas
	 */
	
	public Cine(String nombre, int salas) {
		this.nombre = nombre;
		salasOcupadas = new Pelicula[salas];
		pelis = new ArrayList<Pelicula>();
	}
	
	/**
	 * Metodo para obtener el numero de sala donde se está proyectando una película. 
	 * @param nombre película buscada
	 * @param anio año de producción de película buscada
	 * @param duracion duración de película buscada
	 * @return numero de sala donde esta siendo proyectada o -1 si no se ha encontrado
	 */
	
	public int salaPeli(String nombre, int anio, int duracion) {
		for (Pelicula pelicula : salasOcupadas) {
			if (pelicula.esIgual(new Pelicula(nombre, anio, duracion))) {
				return pelis.indexOf(pelicula) + 1;
			}
		}
		return -1;
	}
	
	/**
	 * Metodo para añadir películas a nuestro cine
	 * @param nuevaPeli película a añadir
	 */

	public void aniadePeli(Pelicula nuevaPeli) {
		if (estaEnElCine(nuevaPeli)) {
			System.out.println("[Error]: Pelicula ya está en el cine");
			return;
		}
		for (int i = 0; i < salasOcupadas.length; i++) {
			if (salasOcupadas[i] == null) {
				salasOcupadas[i] = nuevaPeli;
				pelis.add(nuevaPeli);
				System.out.println("Pelicula Añadida");
				return;
			}	
		}
		System.out.println("[Error]: Todas las salas ocupadas");
	}
	
	
	/**
	 * Metodo para añadir películas a nuestro cine en una sala concreta (si no está ocupada).
	 * @param nuevaPeli película a añadir.
	 * @param sala sala donde añadir la película.
	 */
	public void aniadePeli(Pelicula nuevaPeli, int sala) {
		if (estaEnElCine(nuevaPeli)) {
			System.out.println("[Error]: Pelicula ya está en el cine");
			return;
		}
		if (sala > 0) {
			if (salasOcupadas[sala-1] == null) {
				salasOcupadas[sala-1] = nuevaPeli;
				pelis.add(nuevaPeli);
				System.out.println("Pelicula Añadida");
				return;
			}
			System.out.println("[ERROR]: Sala "+ sala + " ocupada");
		}
	}
	
	/**
	 * Metodo para comprobar si una película está siendo proyectada.
	 * @param comparar película a buscar.
	 * @return si se ha encontrado la película o no.
	 */
	
	private boolean estaEnElCine(Pelicula comparar) {
		if (pelis.size() > 0) {
			for (Pelicula pelicula : pelis) {
				if (pelicula.esIgual(comparar))
					return true;
			}
		}
		return false;
	}
	
	/**
	 * Método para visualizar todas las películas
	 * Formato: pelicula.toString - Sala numero de sala
	 */
	
	public void ver() {
		System.out.println("CINES " + nombre);
		for (int i = 0; i < salasOcupadas.length; i++) {
			if(salasOcupadas[i] != null)
				System.out.println(salasOcupadas[i] + " - Sala " + (i+1));
		}
	}
	
	/**
	 * Método para eliminar todas las películas que empiezen por el String pasado de nuestro cine
	 * @param empieza expresión regular por la que deben empezar las películas para ser borradas.
	 */
	
	public void quitaPeli(String empieza) {
		for (int i = 0; i < salasOcupadas.length; i++) {
			if(salasOcupadas[i] != null && salasOcupadas[i].getNombre().substring(0, empieza.length()).equalsIgnoreCase(empieza)) {
				pelis.remove(salasOcupadas[i]);
				salasOcupadas[i] = null;
			}
		}
	}
	
	/**
	 * Método para obtener un ArrayList con las salas que estan libres.
	 * @return lista de salas libres
	 */
	
	public ArrayList<Integer> libres(){
		ArrayList<Integer> vuelta = new ArrayList<Integer>();
		for (int i = 0; i < salasOcupadas.length; i++) {
			if(salasOcupadas[i] == null)
				vuelta.add(i+1);
		}
		return vuelta;
	}
	
	/**
	 * Método para cambiar una pelicula de sala. 
	 * <br>
	 * Proceso:
	 * <ol>
	 * 	<li>Comprobar que la película está siendo proyectada</li>
	 * 	<li>Comprobar que hay salas libres para realizar el cambio de sala</li>
	 * 	<li>mostrar salas disponibles para realizar el cambio</li>
	 * 	<li>Pedir a usuario la sala para realizar el cambio</li>
	 * 	<li>Mover película de sala</li>
	 * </ol>
	 * @param peli película a modificar
	 */
	
	public void cambiaDeSala(Pelicula peli) {
		ArrayList<Integer> salasLibres = libres();
		for (int i = 0; i < salasOcupadas.length; i++) {
			if(salasOcupadas[i] != null && salasOcupadas[i].esIgual(peli)) {
				System.out.println("pelicula " + peli + " encontrada en la sala " + (i+1));
				if(salasLibres.size() == 0) {
					System.out.println("No hay salas disponibles para realizar el movimiento");
					return;
				}
				else {
					System.out.print("Salas disponibles: ");
					for (Integer salaLibre : salasLibres) {
						System.out.print(salaLibre + " ");
					}
					System.out.println();
					int salaEscogida;
					do {
						System.out.print("A que sala desea mover la pelicula " + peli + ": ");
						salaEscogida = Consola.leeInt();
					}while(!salasLibres.contains(salaEscogida));
					salasOcupadas[salaEscogida - 1] = salasOcupadas[i];
					salasOcupadas[i] = null;
					return;
				}
			}
		}
		System.out.println("Pelicula " + peli + " no encontrada");
	}
	
	/**
	 * Método para devolver el cine a su estado original (vacio)
	 */
	
	public void reseteaCine() {
		for (int i = 0; i < salasOcupadas.length; i++) {
			salasOcupadas[i] = null;
		}
		pelis.clear();
		System.out.println("Se ha reseteado el cine");
	}
	
	/**
	 * Método que devuelve una lista de las películas mas cortas de la duración pasada como parámetros
	 * @param horas duración (parte de las horas)
	 * @param minutos duración (parte de los minutos)
	 * @return lista con peliculas más cortas que la duración especificada.
	 */
	
	public ArrayList<String> pelisMasCortas(int horas, int minutos){
		
		int duracionMaxima = (horas*60) + minutos;
		ArrayList<String> vuelta = new ArrayList<String>();
		for (Pelicula peli : pelis) {
			if(peli.getDuracion() < duracionMaxima)
				vuelta.add(peli.getNombre());
		}
		return vuelta;
	}
}
