package tanda3;

import tanda1.Consola;

public class Tanda3Eje10v2 {
	public static void main(String[] args) {
		
		int dimension;
		
		do {
			System.out.print("Dimensión de la serie: ");
			dimension = Consola.leeInt();
		}while(dimension < 6 || dimension % 2 != 0);
		
		for(int i = 1; i <= dimension/2; i++) {
			for(int j = 1; j <= dimension; j++) {
				if( j == i || j == dimension - i) {
					System.out.print("X ");
					System.out.print("X ");
					j++;
				}
				else {
					System.out.print("  ");
				}
			}
			System.out.print("\n");
		}
		
		for(int i = dimension/2 ; i > 0; i--) {
			for(int j = 1; j <= dimension; j++) {
				if( j == i || j == dimension - i) {
					System.out.print("X ");
					System.out.print("X ");
					j++;
				}
				else {
					System.out.print("  ");
				}
			}
			System.out.print("\n");
		}
	}
}
