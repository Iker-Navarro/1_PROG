package pack5.pack2D;

public class Circulo {
	private double radio;
	private static final double PI = 3.14159;
	
	public Circulo(double radio) {
		this.radio = radio;
	}
	
	public double area() {
		return PI * radio * radio;
	}
}
