package pack3;

import java.io.Serializable;

public class TelefonoMovil implements Serializable {
	String numero;
	double saldo;
	
	public TelefonoMovil(String numero, double saldo) {
		this.numero = numero;
		this.saldo = saldo;
	}
	
	public void ver() {
		System.out.print( numero + " " + saldo + " euros");
	}
	
	public void cargar(int s) {
		saldo+=s;
	}
	
	public boolean llamar(int minutos) {
		int coste = 2*minutos;
		if(coste > saldo)
			return false;
		saldo -= coste;
		return true;
	}
}
