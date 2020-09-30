package beans;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaccion {
	private int id_transaccion;
	private Date fecha;
	
	public Transaccion(int id_transaccion, Date fecha) {
		this.id_transaccion = id_transaccion;
		this.fecha = fecha;
	}
	
	@Override
	public String toString() {
		SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/YYYY");
		return "Transaccion " + id_transaccion + " - " + formateador.format(fecha);
	}
	public int getId_transaccion() {
		return id_transaccion;
	}
	public Date getFecha() {
		return fecha;
	}
	
	
}
