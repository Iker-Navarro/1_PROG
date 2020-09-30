package tanda3;

import tanda1.Consola;

public class Tanda3Eje6 {

	public static void main(String[] args) {
		int mes, comienza, fin = -1;
		
		System.out.print("Mes [1-12] ");
		mes = Consola.leeInt();
		
		System.out.print("En que día comienza? [1-7] ");
		comienza = Consola.leeInt();

		switch(mes) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			fin = 31; 
			break;
		case 2:
			fin = 28; // No hay bisiestos
			break;
		default: 
			fin = 30;
		}
		
		//fin+=comienza-1;
		
		System.out.println();
		System.out.println("L\tM\tX\tJ\tV\tS\tD\t");
		
		// Espacios iniciales
		for(int i = 1; i <= (comienza - 1); i++) {
			System.out.print("\t");
		}
		
		// Días del mes
		for(int i = comienza, actual = 1; actual <= fin; i++, actual++) {
			System.out.print(actual + "\t");
			if(i % 7 == 0) {
				System.out.println();
			}
		}
		
	}
}
