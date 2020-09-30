package Pruebas;

import tanda1.Consola;

public class Prueba2 {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
	
		1
		2	4
		3	6	9
		4	8	12	16
		
		
		*/
		int n;
		System.out.print("Altura = ");
		n = Consola.leeInt();
		
		for(int i = 1; i<=n; i++) {
			for(int j = i; j <= (i*i); j+=i) {
				System.out.print(j+"\t");
			}
			System.out.print("\n");
		}
	}
}
