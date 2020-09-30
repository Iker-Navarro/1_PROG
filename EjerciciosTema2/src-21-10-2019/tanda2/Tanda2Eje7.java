package tanda2;

import tanda1.Consola;

public class Tanda2Eje7 {
	public static void main(String[] args) {
		final int N = 10, MM = 102, MS = 106, HM = 101, HS = 105;
		
		int edad, ci;
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
			}while(sexo != 'M' && sexo != 'm' && sexo != 'S' && sexo != 's');
			
			do {
				System.out.print("Introduce el coeficiente intelectual: ");
				ci = Consola.leeInt();
			}while(ci < 50 || edad > 250);
			
			//---------------
			
			switch() {
			case 'M':
			case 'm':
				
				break;
			case 'S':
			case 's':
				
				break;
			}
		}
	}
}
