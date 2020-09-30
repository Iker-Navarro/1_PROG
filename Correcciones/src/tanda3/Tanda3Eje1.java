package tanda3;

import tanda1.Consola;

public class Tanda3Eje1 {
	public static void main(String[] args) {
		
		int n, codigo, maxCod = -1, salario, numH, edad, contH1015 = 0, minEdad, sumSalario = 0, contSalario = 0, maxHijos = 0;
		char sexo;
		
		System.out.print("Introduce la cantidad de familias: ");
		n = Consola.leeInt();
		
		for (int i = 1; i <= n; i++) {
			
			System.out.print("Introduce el codigo de la familia nº" + i + ": ");
			codigo = Consola.leeInt();
			
			System.out.print("Introduce el salario de la familia nº" + i + ": ");
			salario = Consola.leeInt();
			
			System.out.print("Introduce la cantidad de hijos de la familia nº" + i +": ");
			numH = Consola.leeInt();
			
			minEdad = 150;
			contH1015 = 0;
			
			for (int j = 1; j <= numH; j++) {
				
				do {
					System.out.print("Introduce la edad: ");
					edad = Consola.leeInt();
				}while(edad > 120 || edad < 0);
				
				do {
					System.out.print("Introduce el sexo [H/M]: ");
					sexo = Consola.leeChar();
				}while(sexo != 'H' && sexo != 'h' && sexo != 'm' && sexo != 'M');
				
				
				if (edad < minEdad) {
					minEdad = edad;
				}
				
				if ((sexo == 'h' || sexo == 'H') && (edad >= 10 && edad <= 15)) {
					contH1015++;
				}
			}
			
			System.out.println("La familia nº" + i + " tiene " + contH1015 + " hijos varones entre 10 y 15 años.");
			
			if (numH > 0) {
				System.out.println("La edad minima de los niños en la familia nº" + i + " es de " + minEdad + " años.");
			}
			
			if (numH >= 3) {
				sumSalario+=salario;
				contSalario++;
			}
			
			if (numH > maxHijos) {
				maxHijos = numH;
				maxCod = codigo;
			}
			
		}
		System.out.println("-------------------");
		
		if(contSalario != 0) {
			System.out.println("La media del salario de las familias numerosas es de " + (sumSalario/((double) contSalario)) + "€");
		}
		else {
			System.out.println("No ha habido familias numerosas en la tanda dada.");
		}
		
		if (maxCod != -1) {
			System.out.println("El codigo de la familia con mayor numero de hijos es " + maxCod);
		}
		else {
			System.out.println("No se han dado familias con hijos.");
		}

	}
}