package cuenta1;

public class CuentaBancaria {
	
	String nombre;
	double saldo;
	
	CuentaBancaria(String nombre) {
		this.nombre = nombre;
		saldo = 0;
	}
	
	CuentaBancaria(String nombre, double saldo) {
		this.nombre = nombre;
		this.saldo = saldo;
	}
	
	void ingresar(double cantidad) {
		saldo+=cantidad;
		System.out.println(cantidad + "€ ingresados.");
	}
	
	// Gastar funciona de la manera requerida en los ejercicios.
	// Prueba hecha en Principal.java
	void gastar (double cantidad) {
		if (cantidad > saldo) {
			System.out.println("Error, no dispones de suficiente saldo");
		}
		else {
			saldo-=cantidad;
			System.out.println(cantidad + "€ retirados.");
		}
	}
	
	void ver() {
		if(saldo > 1000000) System.out.println("--CUENTA VIP--");
		System.out.println("Usuario: " + nombre + " Saldo: " + saldo + "€");
	}
}
