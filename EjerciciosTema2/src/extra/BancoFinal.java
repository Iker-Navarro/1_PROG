package extra;

import tanda1.Consola;

public class BancoFinal {
	public static void main(String[] args) {
		// Constantes
		final int N20 = 3, N10 = 4, N5 = 4, N2 = 2, N1 = 3;
		// Variables
		int n20 = N20, n10 = N10, n5 = N5, n2 = N2, n1 = N1;
		int total, ctd;
		char continuar = 's';
		
		// Calcular total inicial
		total =  n1 + n2 * 2 + n5 * 5 + n10 * 10 + n20 * 20;
		
		//bucle continuar
		while((continuar == 's' || continuar == 's') && total > 0) {
			
			System.out.println("Dinero disponible: " + total);
			// Peticion de datos
			do{
				System.out.print("Introduce la cantidad de dinero a retirar : ");
				ctd = Consola.leeInt();
			}while(ctd <= 0);
			
			// Reiniciar cantidades de cambios
			int c1 = 0, c2 = 0, c5 = 0, c10 = 0, c20 = 0;
			
			// Comprobar cantidad dada respecto a total
			if(total >= ctd) {
				
				// comprobar si los cambios son posibles
				int tipo = 20, ctdAct = ctd, cantBillete = -1;
				while(ctdAct > 0 && tipo != 0) {
					
					cantBillete = ctdAct/tipo;
					
					// Comprobar si se necesitan billetes de este tipo
					// Y guardar cantidades de cada tipo de billete/moneda necesarios
					if(cantBillete != 0) {
						switch(tipo) {
						case 20:
							c20 = cantBillete > n20 ? n20 : cantBillete;
							ctdAct -= c20 * tipo;
							break;
						case 10:
							c10 = cantBillete > n10 ? n10 : cantBillete;
							ctdAct -= c10 * tipo;
							break;
						case 5:
							c5 = cantBillete > n5 ? n5 : cantBillete;
							ctdAct -= c5 * tipo;
							break;
						case 2:
							c2 = cantBillete > n2 ? n2 : cantBillete;
							ctdAct -= c2 * tipo;
							break;
						case 1:
							c1 = cantBillete > n1 ? n1 : cantBillete;
							ctdAct -= c1 * tipo;
						}	
					}
					// Cambio de tipo
					tipo/=2;
				}
				
				// Si cantidad actual es 0 se ha conseguido alcanzar el objetivo con los cambios disponibles
				if(ctdAct == 0) {
					//mostrar cambios por pantalla y decremento de cantidades de cambio en caja
					for(tipo = 20; tipo != 0; tipo/=2){
						switch(tipo) {
						case 20:
							n20 -= c20;
							total -= c20 * tipo;
							System.out.print(c20 != 0 ? c20 + " billete/s de 20€\n" : "");
							break;
						case 10:
							n10 -= c10;
							total -= c10 * tipo;
							System.out.print(c10 != 0 ? c10 + " billete/s de 10€\n" : "");
							break;
						case 5:
							n5 -= c5;
							total -= c5 * tipo;
							System.out.print(c5 != 0 ? c5 + " billete/s de 5€\n" : "");
							break;
						case 2:
							n2 -= c2;
							total -= c2 * tipo;
							System.out.print(c2 != 0 ? c2 + " moneda/s de 2€\n" : "");
							break;
						case 1:
							n1 -= c1;
							total -= c1 * tipo;
							System.out.print(c1 != 0 ? c1 + " moneda/s de 1€\n" : "");
						}
					}
					System.out.println("----------\nDinero retirado\n----------");
				}
			
				else {
					System.out.println( "------------------------\nNo hay cambios adecuados para la cantidad solicitada\n------------------------");
				}
			}
			
			else {
				System.out.println("------------------------\nNo hay dinero suficiente en caja\n------------------------");
			}
			
			System.out.print(total > 0 ? "Continuar? [S/N]: " : "----------\nCaja vacia\n----------");
			continuar = Consola.leeChar();
		}		
	}
}
