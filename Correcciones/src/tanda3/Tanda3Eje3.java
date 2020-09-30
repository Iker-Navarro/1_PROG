package tanda3;

import tanda1.Consola;

public class Tanda3Eje3 {
	public static void main(String[] args) {
		int n1, n2, aux, cantPrimos = 0, sumPrimos = 0, numero;
		boolean esPrimo;
		
		System.out.print("n1= ");
		n1 = Consola.leeInt();
		
		System.out.print("n2= ");
		n2 = Consola.leeInt();
		
		if (n2 < n1) {
			aux = n1;
			n1 = n2;
			n2 = aux;
		}
		numero = n1;
		if (numero >= 0 && numero <= 2) {
			numero = 3;
			sumPrimos+=2;
			cantPrimos+=1;
		}
		else if (numero % 2 == 0) {
			numero++; 
		}
		
		for (int i = numero; i <= n2; i+=2) {
			esPrimo = true;
			for (int j = 3; j <= Math.sqrt(i) && esPrimo; j+=2) {
				if (i % j == 0) {
					esPrimo = false;
				}
			}
			if (esPrimo) {
				cantPrimos++;
				sumPrimos+=i;
			}
		}
		
		if (cantPrimos == 0) {
			System.out.println("No hay numeros primos en el rango de números dado.");
		}
		else {
			System.out.println("La media de números primos es: " + sumPrimos/(cantPrimos * 1.0));
		}
	}
}