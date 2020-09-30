package tanda2;

import tanda1.Consola;

public class Tanda2Eje5 {
	public static void main(String[] args) {
		final int n = 3;
		
		int id, maxID = -1, minID = -1, ventas, ventasB = 0, maxVentas = -1, minVentas = -1;
		char tipo;
		
		
		for(int i = 1; i <= n; i++) {
			System.out.print("ID del producto nº" + i + ": ");
			id = Consola.leeInt();
			
			do {
				System.out.print("Tipo del producto " + id + " [A,B,C]: ");
				tipo = Consola.leeChar();
			}while ( tipo != 'A' && tipo != 'a' && tipo != 'B' && tipo != 'b' && tipo != 'C' && tipo != 'c' );
			
			System.out.print("Número de ventas del producto " + id + ": ");
			ventas = Consola.leeInt();
			
			
			if ( tipo == 'B' || tipo == 'b' ) {
				ventasB += ventas;
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
		
		System.out.println("Número de ventas de productos de tipo \"B\" " + ventasB);
		System.out.println("Producto con más ventas: " + maxID);
		System.out.println("Producto con menos ventas: " + minID);
		
	}
}
