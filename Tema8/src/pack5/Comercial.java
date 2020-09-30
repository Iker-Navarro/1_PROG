package pack5;

import java.io.Serializable;

public class Comercial implements Serializable {
	private String nombre;
	private double salario;
	private TelefonoMovil tlf;
	
	public Comercial(String nombre, double salario, TelefonoMovil tlf) {
		this.nombre = nombre;
		this.salario = salario;
		this.tlf = tlf;
	}
	
	public Comercial(String nombre, double salario) {
		this.nombre = nombre;
		this.salario = salario;
	}
	
	@Override
	public String toString() {
		return nombre + " salario:" + salario + "€, " + tlf;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comercial other = (Comercial) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}

	public double getSalario() {
		return salario;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public TelefonoMovil getTlf() {
		return tlf;
	}
	
	public void asignarTlf(TelefonoMovil tlf) {
		this.tlf = tlf;
	}
	
	public void ver() {
		System.out.println(this);
	}
}
