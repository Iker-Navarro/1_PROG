package museo;

import java.io.Serializable;

public class Visita implements Serializable{
	private String nombre;
	private int cantidad;
	private Hora hora;
	
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
