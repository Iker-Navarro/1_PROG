package pack1;

public class TestRectangulo {
	public static void main(String[] args) {
		Rectangulo r1 = new Rectangulo(new Punto(5,-1), 10, 10);
		Rectangulo r2 = new Rectangulo(10, 8);
		Rectangulo r3 = new Rectangulo(6, -2, 10, 15);
		
		r1.visualizar();
		System.out.println(r1.esCuadrado() ? "Es un cuadrado" : "No es un cuadrado");
		
		r2.visualizar();
		System.out.println(r1.esCuadrado() ? "Es un cuadrado" : "No es un cuadrado");
		
		r3.visualizar();
		System.out.println(r1.esCuadrado() ? "Es un cuadrado" : "No es un cuadrado");
	}
}
