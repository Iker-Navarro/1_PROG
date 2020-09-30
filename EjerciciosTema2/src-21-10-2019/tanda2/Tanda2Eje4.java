package tanda2;

import tanda1.Consola;

public class Tanda2Eje4 {
	public static void main(String[] args) {
		int n;
		char origen, destino, minO = 0, minD = 0;
		double distancia, minDist = -1;	
		
		System.out.print("�N�mero de trayectos? ");
		n = Consola.leeInt();
		
		for(int i = 1; i <= n; i++) {
			System.out.print("Origen del trayecto n�" + i + ": ");
			origen = Consola.leeChar();
			System.out.print("Destino del trayecto n�" + i + ": ");
			destino = Consola.leeChar();
			System.out.print("Distancia entre " + origen + " y " + destino + ": ");
			distancia = Consola.leeDouble();
			
			if(distancia < minDist || minDist == -1) {
				minDist = distancia;
				minO = origen;
				minD = destino;
			}
			System.out.print("\n");
		}
		
		System.out.print("La distancia m�s corta es entre " + minO + " y " + minD + " y es de " + minDist +"Km.");
	}	
}
