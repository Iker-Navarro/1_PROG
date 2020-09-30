 package tanda2;

import tanda1.Consola;

public class Tanda2Eje3 {
	public static void main(String[] args) {
		int ant2 = 0, ant = 1, n, nuevo;
		
		do{
			System.out.print("Cantidad de numeros de la serie? ");
			n = Consola.leeInt();
		}while(n <= 0);
		
		if (n == 1) {
			System.out.print(ant2);
		}
		else {
			System.out.print(ant2 + ", " + ant);
			
			for(int i = 3; i <= n; i++) {
				System.out.print(", ");
				
				nuevo = 3 * ant + 2 * ant2;
				System.out.print(nuevo);
				
				ant2 = ant;
				ant = nuevo;
			}
		}
	}
}
