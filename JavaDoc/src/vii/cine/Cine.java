package vii.cine;

import java.util.ArrayList;

/**
 * <h2>Clase: Cine</h2>
 * Clase encargada de almacenar y gestionar las pel�culas proyectadas en un cine concreto. 
 * 
 * @author Iker
 * @version v1
 */

public class Cine {
	
	/**Nombre del cine*/
	private String nombre;
	/**ArrayList de pel�culas que est�n siendo proyectadas actualmente*/
	private ArrayList<Pelicula> pelis;
	/**Array de pel�culas proyectadas, el �ndice del array identifica tambi�n la sala en la que est� siendo proyectada*/
	private Pelicula[] salasOcupadas;
	
	/**
	 * Constructor que crea un nuevo cine d�ndole un nombre y la cantidad de salas que dispone.
	 * Inicializa tambi�n ambos almacenes de pel�culas (vacios)
	 * @param nombre nombre del cine
	 * @param salas cantidad total de salas
	 */
	
	public Cine(String nombre, int salas) {
		this.nombre = nombre;
		salasOcupadas = new Pelicula[salas];
		pelis = new ArrayList<Pelicula>();
	}
	
	/**
	 * Metodo para obtener el numero de sala donde se est� proyectando una pel�cula. 
	 * @param nombre pel�cula buscada
	 * @param anio a�o de producci�n de pel�cula buscada
	 * @param duracion duraci�n de pel�cula buscada
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
	 * Metodo para a�adir pel�culas a nuestro cine
	 * @param nuevaPeli pel�cula a a�adir
	 */

	public void aniadePeli(Pelicula nuevaPeli) {
		if (estaEnElCine(nuevaPeli)) {
			System.out.println("[Error]: Pelicula ya est� en el cine");
			return;
		}
		for (int i = 0; i < salasOcupadas.length; i++) {
			if (salasOcupadas[i] == null) {
				salasOcupadas[i] = nuevaPeli;
				pelis.add(nuevaPeli);
				System.out.println("Pelicula A�adida");
				return;
			}	
		}
		System.out.println("[Error]: Todas las salas ocupadas");
	}
	
	
	/**
	 * Metodo para a�adir pel�culas a nuestro cine en una sala concreta (si no est� ocupada).
	 * @param nuevaPeli pel�cula a a�adir.
	 * @param sala sala donde a�adir la pel�cula.
	 */
	public void aniadePeli(Pelicula nuevaPeli, int sala) {
		if (estaEnElCine(nuevaPeli)) {
			System.out.println("[Error]: Pelicula ya est� en el cine");
			return;
		}
		if (sala > 0) {
			if (salasOcupadas[sala-1] == null) {
				salasOcupadas[sala-1] = nuevaPeli;
				pelis.add(nuevaPeli);
				System.out.println("Pelicula A�adida");
				return;
			}
			System.out.println("[ERROR]: Sala "+ sala + " ocupada");
		}
	}
	
	/**
	 * Metodo para comprobar si una pel�cula est� siendo proyectada.
	 * @param comparar pel�cula a buscar.
	 * @return si se ha encontrado la pel�cula o no.
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
	 * M�todo para visualizar todas las pel�culas
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
	 * M�todo para eliminar todas las pel�culas que empiezen por el String pasado de nuestro cine
	 * @param empieza expresi�n regular por la que deben empezar las pel�culas para ser borradas.
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
	 * M�todo para obtener un ArrayList con las salas que estan libres.
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
	 * M�todo para cambiar una pelicula de sala. 
	 * <br>
	 * Proceso:
	 * <ol>
	 * 	<li>Comprobar que la pel�cula est� siendo proyectada</li>
	 * 	<li>Comprobar que hay salas libres para realizar el cambio de sala</li>
	 * 	<li>mostrar salas disponibles para realizar el cambio</li>
	 * 	<li>Pedir a usuario la sala para realizar el cambio</li>
	 * 	<li>Mover pel�cula de sala</li>
	 * </ol>
	 * @param peli pel�cula a modificar
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
	 * M�todo para devolver el cine a su estado original (vacio)
	 */
	
	public void reseteaCine() {
		for (int i = 0; i < salasOcupadas.length; i++) {
			salasOcupadas[i] = null;
		}
		pelis.clear();
		System.out.println("Se ha reseteado el cine");
	}
	
	/**
	 * M�todo que devuelve una lista de las pel�culas mas cortas de la duraci�n pasada como par�metros
	 * @param horas duraci�n (parte de las horas)
	 * @param minutos duraci�n (parte de los minutos)
	 * @return lista con peliculas m�s cortas que la duraci�n especificada.
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
