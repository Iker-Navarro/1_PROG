package tanda2;

import tanda1.Consola;

public class Tanda2Eje5 {
	public static void main(String[] args) {
		final int N = 3;
		
		boolean hayB = false;
		int id, maxID = -1, minID = -1, ventas, ventasB = 0, maxVentas = -1, minVentas = Integer.MAX_VALUE;
		char tipo, maxTipo = ' ', minTipo = ' ';
		
		
		for(int i = 1; i <= N; i++) {
			System.out.print("ID del producto nº" + i + ": ");
			id = Consola.leeInt();
			
			do {
				System.out.print("Tipo del producto " + id + " [A,B,C]: ");
				tipo = Consola.leeChar();
			}while ( tipo != 'A' && tipo != 'a' && tipo != 'B' && tipo != 'b' && tipo != 'C' && tipo != 'c' );
			
			System.out.print("Número de ventas del producto " + id + ": ");
			ventas = Consola.leeInt();
			
			//--
			
			if ( tipo == 'B' || tipo == 'b' ) {
				ventasB += ventas;
				hayB = true;
			}
			
			if ( ventas > maxVentas ) {
				maxVentas = ventas;
				maxID = id;
				maxTipo = tipo;
			}
			
			if ( ventas < minVentas ) {
				minVentas = ventas;
				minID = id;
				minTipo = tipo;
			}
			
			System.out.println();
		}
		
		if(hayB) System.out.println("Número de ventas totales de productos de tipo \"B\" " + ventasB);
		else System.out.println("No hay productos de tipo \"B\" en la lista dada");
		
		System.out.println("El código del producto con más ventas es " + maxID + " cuya categoria es \"" + maxTipo + "\"");
		System.out.println("El código del producto con menos ventas es " + minID + " cuya categoria es \"" + minTipo + "\"");
		
	}
}
