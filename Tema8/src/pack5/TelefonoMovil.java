package pack5;

import java.io.Serializable;

public class TelefonoMovil implements Serializable {
	private String numero;
	private double saldo;


	public TelefonoMovil(String numero, double saldo) {
		this.numero = numero;
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return numero + " - " + saldo + "€";
	}
	
	public String getNumero() {
		return numero;
	}	
}
