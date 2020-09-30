package tanda1;

public class Tanda1Eje9 {
	public static void main(String[] args) {
		
		final int DIAS = 5;
		
		double tiempo, sumaTiempos = 0, media;
		
		boolean esApto = true, menos10 = false;
		
		
		for(int i = 1; i <= DIAS && esApto; i++) {
			System.out.print("Introduce el tiempo del dia " + i + ": ");
			tiempo = Consola.leeDouble();
			if(tiempo > 16) {
				esApto = false;
			}
			if(tiempo < 10) {
				menos10 = true;
			}
			sumaTiempos += tiempo;
		}
		media = sumaTiempos/DIAS;
		
		if(esApto && menos10 && media <= 15) {
			System.out.println("Esta persona es APTA");
		}
		else {
			System.out.println("Esta persona NO es APTA");
		}
	}
}
