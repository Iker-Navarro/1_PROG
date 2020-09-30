package ejer6;

public class PruebaCuadrado {
	public static void main(String[] args) {
		Cuadrado c1 = new Cuadrado("c1", new Punto(3,3), new Punto(8,5));
		System.out.println(c1);
		System.out.println(c1.calcularArea());
		
		Cuadrado c2 = new Cuadrado("c2", new Punto(3,3), new Punto(3,5));
		System.out.println(c2);
		System.out.println(c2.calcularArea());
		
		Cuadrado c3 = new Cuadrado("c3", new Punto(3,3), new Punto(3,8));
		System.out.println(c3);
		System.out.println(c3.calcularArea());
		
		Cuadrado c4 = new Cuadrado("c4", new Punto(3,3), new Punto(6,6));
		System.out.println(c4);
		System.out.println(c4.calcularArea());
	}
}
