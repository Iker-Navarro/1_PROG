package pack3_2;

import java.io.Serializable;

public class Comercial implements Serializable{
	String nombre;
	double salario;
	TelefonoMovil tlf;
	
	public Comercial(String nombre, double salario, TelefonoMovil tlf) {
		this.nombre = nombre;
		this.salario = salario;
		this.tlf = tlf;
	}
	
	public void ver() {
		System.out.print(nombre + " " + salario + " euros, telefono: ");
		tlf.ver();
		System.out.println();
	}
	
	public void trabajar() {
		trabajar(10, 15);
	}
	
	public void trabajar(int g, int m) {
		if(tlf.llamar(m))
			salario+=g;
		
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public TelefonoMovil getTlf() {
		return tlf;
	}
}
