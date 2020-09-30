package tanda2;

import tanda1.Consola;

public class Tanda2Eje6 {
	public static void main(String[] args) {
		final int N = 3, IMPUESTO = 10;
		
		int nHijos, salario, maxSalario = -1, minSalario = -1, cantNum = 0, sumNum = 0; // o minSalario = Integer.MAX_VALUE
		double tasa;
		
		for(int i = 1; i <= N; i++) {
			
			System.out.print("Numero de hijos de la familia " + i + ": ");
			nHijos = Consola.leeInt();
			
			System.out.print("Salario de la familia " + i + ": ");
			salario = Consola.leeInt();
			
			tasa = salario * (IMPUESTO/100.0);
			
			if ( nHijos == 0 ) {
				if ( salario > maxSalario ) {
					maxSalario = salario;
				}
			}
			else if ( nHijos <= 2 ) {
				tasa = tasa - tasa*((5 * nHijos)/100.0);
			}
			else {
				tasa = tasa - tasa * ((10 * nHijos)/100.0);
				
				cantNum++;
				sumNum += salario;
			}
			
			if ( salario < minSalario || minSalario == -1) {
				minSalario = salario;
			}
			
			tasa = tasa < 0 ? 0 : tasa;
			
			System.out.println("Tasa: " + tasa);
			System.out.println();
		}
		
		System.out.println("----------------");	
		if (cantNum == 0) {
			System.out.println("No hay familias numerosas en la lista dada.");
		}
		else {
			System.out.println("El sueldo medio de las familias numerosas = " + sumNum/(double)cantNum + "€");
		}
		
		if ( maxSalario == -1 ) {
			System.out.println("No hay familias sin hijos en la lista dada.");
		}
		else {
			System.out.println("El sueldo máximo de las familias sin hijos es = " + maxSalario + "€");
		}
		System.out.println("El sueldo mínimo es = " + minSalario + "€");
	}
}
