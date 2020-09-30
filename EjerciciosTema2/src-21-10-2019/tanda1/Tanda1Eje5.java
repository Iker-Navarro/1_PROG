package tanda1;

public class Tanda1Eje5 {

	public static void main(String[] args) {
		
		final double CR = 1.08, CD= 1.66; 
		int t;
		double consumo;
		char estado;
		
		do{
			System.out.print("Estado del paciente? (D/R): ");
			estado = Consola.leeChar();
		}while(estado != 'D' && estado != 'd' && estado != 'R' && estado != 'r');
		
		do {
			System.out.print("Tiempo: ");
			t = Consola.leeInt();
		}while(t < 0);
		
		if (estado == 'D' || estado == 'd') {
			consumo = CD * t;
		}
		else {
			consumo = CR * t;
		}
		System.out.print("El paciente consume " + consumo + "cal en el tiempo dado.");
	}

}
