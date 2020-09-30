package tanda3;

import tanda1.Consola;

public class Tanda3Eje2 {

	static double factorial(int x) {
		double respuesta = 1;
		for (int i = 2;  i <= x; i++) {
			respuesta*=i;
		}
		
		return respuesta;
	}
	
	public static void main(String[] args) {
		int n,i;

		do {
			System.out.println("Valores para realizar la operación:");
			System.out.print("n = ");
			n = Consola.leeInt();
			
			System.out.print("i = ");
			i = Consola.leeInt();
		}while(n <= 0 || i <= 0 || i > n);	
		
		System.out.println("n! /( i! * (n-i)!) = " + (factorial(n)/((factorial(i)) * factorial(n-i))));		
	}
}
