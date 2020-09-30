package tanda2;

import tanda1.Consola;

public class Tanda2Eje7 {
	
	static void ciComp(int ci, int comp) {
		if (ci < comp) {
			System.out.println("El coeficiente intelectual es inferior a la media de tu ciudad/sexo.");
		}
		else if(ci == comp) {
			System.out.println("El coeficiente intelectual es igual a la media de tu ciudad/sexo.");
		}
		else {
			System.out.println("El coeficiente intelectual es superior a la media de tu ciudad/sexo.");
		}	
	}
	
	public static void main(String[] args) {
		final int N = 10, MM = 102, MS = 106, HM = 101, HS = 105;
		
		int edad, ci, maxCi = 0, sumCiMan = 0, cantCiMan = 0;
		char sexo, ciudad;
		
		for (int i = 1; i <= N; i++) {
			
			do {
				System.out.print("Introduce el sexo [H/M]: ");
				sexo = Consola.leeChar();
			}while(sexo != 'h' && sexo != 'H' && sexo != 'M' && sexo != 'm');
			
			do {
				System.out.print("Introduce la edad: ");
				edad = Consola.leeInt();
			}while(edad < 0 || edad > 150);
			
			do {
				System.out.print("Introduce la ciudad: ");
				ciudad = Consola.leeChar();
			}while(ciudad != 'M' && ciudad != 'm' && ciudad != 'S' && ciudad != 's');
			
			do {
				System.out.print("Introduce el coeficiente intelectual: ");
				ci = Consola.leeInt();
			}while(ci < 50 || ci > 250);
			
			//--
			
			switch (ciudad) {
			case 'M':
			case 'm':
				if (sexo == 'h' || sexo == 'H') {
					ciComp(ci, HM);
				}
				else {
					ciComp(ci, MM);
				}
				sumCiMan+=ci;
				cantCiMan++;
				break;
			case 'S':
			case 's':
				if (sexo == 'h' || sexo == 'H') {
					ciComp(ci, HS);
					if (edad > 20 && edad < 30) {
						if (ci > maxCi) {
							maxCi = ci;
						}
					}
				}
				else {
					ciComp(ci, MS);
				}
				break;
			}
			System.out.println();
		}
		System.out.println("-----------------------");
		if (maxCi == 0) {
			System.out.println("No hay datos suficientes para obtener el coeficiente maximo de los hombre de Sheffield");
		}
		else {
			System.out.println("El coeficiente maximo de los hombre de Sheffield es: " + maxCi);
		}
		
		if (cantCiMan == 0) {
			System.out.println("No hay datos suficientes para obtener el ci medio de las personas de Manchester");
		}
		else {
			System.out.println("El coeficiente medio de las personas de Manchester dadas es " + sumCiMan/(cantCiMan*1.0));
		}
	}
}
