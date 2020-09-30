package pack5.pack3D;

import pack5.pack2D.Circulo;

public class Esfera {
	private double radio, altura;
	private Circulo c1;
	
	public Esfera(double radio) {
		this.radio = radio;
		altura = radio*2;
		c1 = new Circulo(radio);
	}
	
	public double volumen() {
		return (c1.area()*altura)*2/3;
	}
}
