package cuentapersona;

public class CuentaBancaria {
	
	Persona titular;
	double saldo;
	boolean enDeuda;
	
	CuentaBancaria(Persona titular) {
		this.titular = titular;
		saldo = 0;
		enDeuda = false;
	}
	
	CuentaBancaria(Persona titular, double saldo) {
		this.titular = titular;
		this.saldo = saldo;
		enDeuda = saldo >= 0 ? false : true;
	}
	
	void ingresar(double cantidad) {
		saldo+=cantidad;
		System.out.println(cantidad + "€ ingresados.");
		if(enDeuda) {
			enDeuda = saldo >= 0 ? false : true;
		}
	}
	
	void gastar (double cantidad) {
		saldo-=cantidad;
		System.out.println(cantidad + "€ retirados.");
		if(!enDeuda){
			enDeuda = saldo >= 0 ? false : true;
		}
	}
	
	void ver() {
		if(saldo > 1000000) System.out.println("--CUENTA VIP--");
		System.out.println("Usuario: " + titular.nombre + " Saldo: " + saldo + "€");
	}
}
