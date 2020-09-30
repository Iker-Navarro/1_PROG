package tanda3;

import tanda1.Consola;

public class Tanda3Eje2 {

	public static void main(String[] args) {
		int n,i;
		
		long factoriali = 1, factorialn = 1, factorialn_i = 1;
		
		do {
			System.out.println("Valores para realizar la operación:");
			System.out.print("n = ");
			n = Consola.leeInt();
			
			System.out.print("i = ");
			i = Consola.leeInt();
			
		}while(n <= 0 || i <= 0 || i > n);	
		
		for (int j = 2;  j <= i; j++) {
			factoriali*=j;
		}
		for (int j = 2;  j <= n; j++) {
			factorialn*=j;
		}
		for (int j = 2;  j <= n-i; j++) {
			factorialn_i*=j;
		}
		
		System.out.println("n! /( i! * (n-i)!) = " + (factorialn/(double)((factoriali) * factorialn_i)));		
	}
}
