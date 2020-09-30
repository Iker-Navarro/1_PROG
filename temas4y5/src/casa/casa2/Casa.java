package casa2;

public class Casa { 

	static int casasCreadas = 0;
	
	String direccion;
	double superficie;
	char tipo;
	
	Casa(String direccion, double superficie, char tipo){
		this.direccion = direccion;
		this.superficie = superficie;
		this.tipo = tipo;
		
		casasCreadas++;
		System.out.println("INSTANCIADA LA CASA NUMERO "+ casasCreadas);
	}
	
	void mostrar() {
		switch (tipo) {
		case 'p':
			System.out.println("Piso en " + direccion + ", Superficie: " + superficie + "m^2");
			break;
		case 'u':
			System.out.println("Unifamiliar en " + direccion + ", Superficie: " + superficie + "m^2");
			break;
		default:
			System.out.println("Tipo de domicilio indefinido");
			System.out.println(direccion + ", Superficie: " + superficie + "m^2");	
		}
	}
	
	double dameImpuesto() {
		final double IMP_INTER = 4.5, IMP_GRAN = 5.5;
		
		double impuesto = 0;
		
		if(superficie >= 60 && superficie <= 80) {
			impuesto = superficie * IMP_INTER;
		}
		else {
			impuesto = superficie * IMP_GRAN;
		}
		
		return impuesto;
	}
	
	boolean esVendible() {
		if(tipo=='u' || (tipo == 'p' && superficie > 100)) {
			return true;
		}
		
		return false; 
	}
	
	boolean esSimilar(Casa other) {
		if (this.tipo == other.tipo && Math.abs(this.superficie - other.superficie) < 10) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) {
		
		/*
		Casa c1 = new Casa(), c2 = new Casa(), c3 = new Casa();
		
		c1.tipo = 'p';
		c1.direccion = "C/Rioja, 18m" ;
		c1.superficie = 65;
		
		c2.tipo = 'u';
		c2.direccion = "C/Alava, 5" ;
		c2.superficie = 150;
		
		c3.tipo = 'p';
		c3.direccion = "C/Gorbea, 23" ;
		c3.superficie = 105.5;
		*/
		Casa c1 = new Casa("C/Rioja, 18m", 65, 'p');
		Casa c2 = new Casa("C/Alava, 5", 150, 'u');
		Casa c3 = new Casa("C/Gorbea, 23", 105.5, 'p');
		
		c1.mostrar();
		c2.mostrar();
		c3.mostrar();
		System.out.println();
		
		c2.superficie -= 5;
		
		c1.mostrar();
		c2.mostrar();
		c3.mostrar();
		System.out.println();
		
		c1.dameImpuesto();
		c2.dameImpuesto();
		c3.dameImpuesto();
		System.out.println();
		
		if (c1.esVendible()) c1.mostrar();
		if (c2.esVendible()) c2.mostrar();
		if (c3.esVendible()) c3.mostrar();
		System.out.println();
		
		System.out.println(c2.esSimilar(c3) ? "Son similares" : "No son similares");
		
		System.out.println(c1.esSimilar(c3) ? "Son similares" : "No son similares");
		
		c1.superficie += 35;
		
		System.out.println(c1.esSimilar(c3) ? "Son similares" : "No son similares");
	}
}
