package pack5.pack2D;

public class Rectangulo {
	double base, altura;
	
	public Rectangulo(double base, double altura) {
		this.base = base;
		this.altura = altura;
	}
	
	public double area() {
		return base * altura;
	}
}
