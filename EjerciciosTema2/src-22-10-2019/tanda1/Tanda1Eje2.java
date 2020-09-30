package tanda1;

public class Tanda1Eje2 {
	public static void main(String[] args) {
		
		final double PI = 3.14159;
		int r, l1, l2;
		char figura;
		
		do {
			System.out.print("Introduce una forma [C/R]");
			figura = Consola.leeChar();
		}while(figura != 'C' && figura != 'c' && figura != 'R' && figura != 'r');
		
		if (figura == 'R' ) {
			System.out.println("Introduce los lados del rectangulo: ");
			System.out.println("l1: ");
			l1 = Consola.leeInt();	
			System.out.println("l2: ");
			l2 = Consola.leeInt();
			System.out.print("Area del rectangulo = " + (l1*l2));
		}
		else {
			System.out.print("Introduce el radio del circulo: ");
			r = Consola.leeInt();
			System.out.print("Area del circulo = " + (PI*r*r));
		}
	}
}
