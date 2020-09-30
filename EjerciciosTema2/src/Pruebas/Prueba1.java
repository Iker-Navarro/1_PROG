package Pruebas;

import tanda1.Consola;

public class Prueba1 {
	public static void main(String[] args) {
		int n1, n2;
		
		System.out.print("Introduce un numero: ");
		n1 = Consola.leeInt();
		
		System.out.print("Introduce otro numero: ");
		n2 = Consola.leeInt();
		for(int i = n1; i<=n2; i++) {
			System.out.print(i + "\n");
		}
	}
}
