package tanda2;

import tanda1.Consola;

public class Tanda2Eje5 {
	public static void main(String[] args) {
		final int N = 3;
		
		boolean hayB = false;
		int id, maxID = -1, minID = -1, ventas, ventasB = 0, maxVentas = -1, minVentas = -1; // o minVentas = Integer.MAX_VALUE
		char tipo;
		
		
		for(int i = 1; i <= N; i++) {
			System.out.print("ID del producto n�" + i + ": ");
			id = Consola.leeInt();
			
			do {
				System.out.print("Tipo del producto " + id + " [A,B,C]: ");
				tipo = Consola.leeChar();
			}while ( tipo != 'A' && tipo != 'a' && tipo != 'B' && tipo != 'b' && tipo != 'C' && tipo != 'c' );
			
			System.out.print("N�mero de ventas del producto " + id + ": ");
			ventas = Consola.leeInt();
			
			//--
			
			if ( tipo == 'B' || tipo == 'b' ) {
				ventasB += ventas;
				hayB = true;
			}
			
			if ( ventas > maxVentas ) {
				maxVentas = ventas;
				maxID = id;
			}
			
			if ( ventas < minVentas || minVentas == -1) {
				minVentas = ventas;
				minID = id;
			}
			
			System.out.println();
		}
		
		if(hayB) System.out.println("N�mero de ventas totales de productos de tipo \"B\" " + ventasB);
		else System.out.println("No hay productos de tipo \"B\" en la lista dada");
		
		System.out.println("C�digo del producto con m�s ventas: " + maxID);
		System.out.println("C�digo del producto con menos ventas: " + minID);
		
	}
}
