package pack5.pack3D;

import pack5.pack2D.Circulo;

public class Cilindro {
	private double radio, altura;
	private Circulo cBase;
	
	public Cilindro(double radio, double altura) {
		this.radio = radio;
		cBase = new Circulo(radio);
		this.altura = altura;
	}
	
	public double volumen() {
		
		return cBase.area() * altura;
	}
}
