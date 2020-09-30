package cuentarojos;

public class CuentaBancaria {
	
	String nombre;
	double saldo;
	boolean enDeuda;
	
	CuentaBancaria(String nombre) {
		this.nombre = nombre;
		saldo = 0;
		enDeuda = false;
	}
	
	CuentaBancaria(String nombre, double saldo) {
		this.nombre = nombre;
		this.saldo = saldo;
		enDeuda = saldo >= 0 ? false : true;
	}
	
	void ingresar(double cantidad) {
		saldo+=cantidad;
		System.out.println(cantidad + "€ ingresados.");
		enDeuda = saldo >= 0 ? false : true;
	}
	
	void gastar (double cantidad) {
		saldo-=cantidad;
		System.out.println(cantidad + "€ retirados.");
		enDeuda = saldo >= 0 ? false : true;

	}
	
	void ver() {
		if(saldo > 1000000) System.out.println("--CUENTA VIP--");
		System.out.println("Usuario: " + nombre + " Saldo: " + saldo + "€");
	}
}
