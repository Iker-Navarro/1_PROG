package tanda2;

import tanda1.Consola;

public class Tanda2Eje2 {
	public static void main(String[] args) {
		char opcion;
		int num;
		
		do {
			System.out.print("A - Calcular raíz cuadrada\nB - Calcular cubo\nC - Calcular sumatorio\nD - Calcular factorial\nE - Salir\n");
			System.out.print(">>");
			opcion = Consola.leeChar();
			
			switch(opcion) {
			case 'A':
			case 'a':
				System.out.print("Introduce un numero: ");
				num = Consola.leeInt();
				System.out.print("El cuadrado de " + num + " es " + (num * num) + "\n");
				break;
			case 'B':
			case 'b':
				System.out.print("Introduce un numero: ");
				num = Consola.leeInt();
				System.out.print("El cubo de " + num + " es " + (num * num * num) + "\n");
				break;
			case 'C':
			case 'c':
				int respuestaSum = 0;
				System.out.print("Introduce un numero: ");
				num = Consola.leeInt();
				for(int i = 1; i <= num; i++) {
					respuestaSum+=i;
				}
				System.out.print("El sumatorio de " + num + " es " + respuestaSum + "\n");
				break;
			case 'D':
			case 'd':
				int respuestafact = 1;
				System.out.print("Introduce un numero: ");
				num = Consola.leeInt();
				for(int i = 2; i <= num; i++) {
					respuestafact *= i;
				}
				System.out.print("El sumatorio de " + num + " es " + respuestafact + "\n");
				break;
			case 'E':
			case 'e':
				System.out.println("Adios!");
				break;
			default: System.out.println("Error: Opción invalida.");
			}
		}while(opcion != 'E' && opcion != 'e');

	}
}
