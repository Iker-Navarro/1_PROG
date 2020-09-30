package tanda3;

import tanda1.Consola;

public class Tanda3Eje7 {
	public static void main(String[] args) {
		
		long numero, actual;
		int suma = 0;
		
		System.out.print("Introduce el número de la tarjeta: ");
		numero = Consola.leeLong();
		
		for(long i = numero, sw = 1; i != 0; i /= 10) {
			actual = (i % 10) * sw;
			if(actual > 9) {
				actual-=9;
			}
			
			suma += actual;
			
			if(sw == 1) {
				sw = 2;
			}
			else {
				sw = 1;
			}
			
		}

		if(suma % 10 == 0) {
			System.out.println("El número introducido es correcto");
		}
		else {
			System.out.println("El número introducido no es correcto");
		}
	}
}
