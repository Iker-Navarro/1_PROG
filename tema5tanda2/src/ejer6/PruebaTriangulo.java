package ejer6;

public class PruebaTriangulo {
	public static void main(String[] args) {
		Triangulo t1 = new Triangulo("t1", new Punto(0, 0), new Punto(3, 5.19), new Punto(6, 0));
		System.out.println(t1);
		System.out.println(t1.calcularArea());
	
	}
}
