package tanda1;

public class Tanda1Eje2 {
	public static void main(String[] args) {
		final double PI = 3.14159;
		int r, l1, l2;
		char figura;
		
		System.out.print("Introduce una forma [C/R]");
		figura = Consola.leeChar();
		if (figura == 'R' ) {
			System.out.print("Introduce los lados del rectangulo: ");
			l1 = Consola.leeInt();	
			l2 = Consola.leeInt();
			System.out.print("Area del rectangulo = " + (l1*l2));
		}
		else if (figura == 'C') {
			System.out.print("Introduce el radio del circulo: ");
			r = Consola.leeInt();
			System.out.print("Area del circulo = " + (PI*r*r));
		}
		
	}
}
