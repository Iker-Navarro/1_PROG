package vii.museo;

import java.io.Serializable;

/**
 * <h2>Clase: Visita</h2>
 * Clase que almacena la información sobre una visita concreta.
 * Utilizado principalmente por la clase VisitasDia
 * @see vii.museo.VisitasDia
 * @author Iker
 * @version v1
 */

public class Visita implements Serializable{
		
	/** Nombre de persona que realiza la reserva*/
	private String nombre;
	/** Cantidad de personas que acudirán con esta reserva*/
	private int cantidad;
	/** Hora a la que comienza la visita reservada*/
	private Hora hora;
	
	/**
	 * Constructor
	 * Crea objetos inicializando todos las propiedades con los valores dados.
	 * @param nombre Persona que realiza la reserva
	 * @param cantidad Reservado para esta cantidad de personas
	 * @param hora hora de la visita
	 */
	
	public Visita(String nombre, int cantidad, Hora hora) {
		this.nombre = nombre;
		this.cantidad = cantidad;
		this.hora = hora;
	}
	
	@Override
	public String toString() {
		return "Reserva para " + cantidad + " para las " + hora + ", solicitante: " + nombre;
	}
	
	public Hora getHora() {
		return hora;
	}
	
	public int getCantidad() {
		return cantidad;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setHora(Hora hora) {
		this.hora = hora;
	}
	
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
