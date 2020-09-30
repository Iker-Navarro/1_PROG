package tanda3;

import tanda1.Consola;

public class Tanda3Eje5 {
	public static void main(String[] args) {
		int nAnio, nMeses;  
		double capital, interes, pot = 1, mensual;

		System.out.print("Capital a pagar: ");
		capital = Consola.leeDouble();
		
		System.out.print("Porcentaje de interés mensual: ");
		interes = Consola.leeDouble();
		interes /= 100;
				
		System.out.print("numero de años: ");
		nAnio = Consola.leeInt();
		nMeses = nAnio * 12;
		
		for (int i = 1; i <= nMeses; i++) {
			pot *= (1/(1 + interes));
		}
		mensual = (capital * interes) / (1-pot);
		
		System.out.println("Quota mensual = " +  Math.floor(mensual * 100) / 100 + "€");
		System.out.println("Pago total = " + Math.floor((mensual * nMeses) * 100) / 100 + "€");
		
	}
}
