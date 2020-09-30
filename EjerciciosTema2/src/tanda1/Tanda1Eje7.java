package tanda1;

public class Tanda1Eje7 {
	static int mesADia(int mes, int anio) {
		int resultado = 0;
		for (int i = 1; i < mes; i++) {
			switch(i) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				resultado += 31; 
				break;
			case 2:
				if(anio % 400 == 0 || (anio % 4 == 0 && anio % 100 != 0)) {
					resultado += 29;
				}
				else {
					resultado += 28;
				}
				break;
			default: 
				resultado += 30;
			}
		}
		return resultado;
	}
	
	public static void main(String[] args) {
		
		int d_a, d_n, m_a, m_n, a_a, a_n, diff_a = 0, n_rest = 0, a_rest;

		System.out.print("Año de nacimiento: ");
		a_n = Consola.leeInt();

		System.out.print("Mes: ");
		m_n = Consola.leeInt();

		System.out.print("Día: ");
		d_n = Consola.leeInt();

		System.out.print("Año actual: ");
		a_a = Consola.leeInt();

		System.out.print("Mes: ");
		m_a = Consola.leeInt();

		System.out.print("Día: ");
		d_a = Consola.leeInt();
		
		for(int i = a_n + 1; i < a_a; i++) {
			if (i % 400 == 0 || (i % 4 == 0 && i % 100 != 0)) {
				diff_a += 366;
			}
			else {
				diff_a += 365;
			}
		}
		
		n_rest = (a_n % 400 == 0 || (a_n % 4 == 0 && a_n % 100 != 0)) ? (366 - (mesADia(m_n, a_n) + d_n)) : (365 - (mesADia(m_n, a_n) + d_n));
		a_rest = mesADia(m_a, a_a) + d_a;
		/*
		System.out.println("n_rest " + n_rest);
		System.out.println("a_rest " + a_rest);
		System.out.println("diff_a " + diff_a);
		*/
		System.out.println("--------");
		
		if (diff_a == 0) {
			System.out.println(diff_a+a_rest+n_rest-365 + " dias entre las fechas dadas.");
		}
		else {
			System.out.println(diff_a+a_rest+n_rest + " dias entre las fechas dadas.");
		}
	}
}
