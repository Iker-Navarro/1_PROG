package tanda3;

import tanda1.Consola;

public class Tanda3Eje10 {
	public static void main(String[] args) {
		
		int dimension, ctr1, ctr2, dimensionactual;
		boolean esMitad = false;
		
		do {
			System.out.print("Dimensión de la serie: ");
			dimension = Consola.leeInt();
		}while(dimension < 6 || dimension % 2 != 0);
		
		ctr1 = dimension/2;
		ctr2 = dimension/2+1;
		dimensionactual = dimension;
		
		for(int i = 1; i <= dimension; i++) {
			for(int j = 1; j <= dimension; j++) {
				if(j == (ctr1 - (dimensionactual/2 - 1)) || j == (ctr1 - (dimensionactual/2 - 1) + 1)  ||j == (ctr2 + (dimensionactual/2 - 1))|| j == (ctr2 + (dimensionactual/2 - 1) -1)) {
					System.out.print("X");
				}
				else {
					System.out.print(" ");
				}
			}
			
			if(dimensionactual == 2 && !esMitad) {
				esMitad = true;
			}
			else {
				dimensionactual -= 2;
			}
			
			System.out.print("\n");
		}
		
	}
}
