package ejer5.test;

import ejer5.figuras.*;

public class TestFigura {
	public static void main(String[] args) {
		Circulo c1 = new Circulo(2);
		System.out.println(c1.getArea());
		
		Cuadrado cdr1 = new Cuadrado(3);
		System.out.println(cdr1.getArea());
		
		Rectangulo r1 = new Rectangulo(4, 5);
		System.out.println(r1.getArea());
		
		System.out.println(Figura.getAreaTotal());
		
		Circulo c2 = new Circulo(3);
		Circulo c3 = new Circulo(1);
		
		System.out.println(Circulo.getAreaTotal());
	}
}
