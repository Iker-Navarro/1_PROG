package tanda1;

public class Tanda1Eje6 {
	public static void main(String[] args) {
		int h, m;
		
		do {
			
			System.out.print("Introduce la hora: ");
			h = Consola.leeInt();
			
			System.out.print("Introduce los minutos: ");
			m = Consola.leeInt();
			
		}while(h < 0 && h > 23 && m < 0 && m > 59 );
		
		String m2 = (h < 10 ? "0" : "") + m;
		
		if ( h < 12 ) {
			String h2 = (h < 10 ? "0" : "") + h;
			System.out.print(h2 + ":" + m2 + " AM");
		}
		else {
			h-=12;
			String h2 = (h < 10 ? "0" : "") + h;
			System.out.print(h2 + ":" + m2 + " PM");
		}
		
	}
}
