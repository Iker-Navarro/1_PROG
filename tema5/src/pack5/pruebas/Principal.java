package pack5.pruebas;

import pack5.pack2D.*;
import pack5.pack3D.*;

public class Principal {
	public static void main(String[] args) {
		
		Circulo cir1 = new Circulo(10);
		Rectangulo rec1 = new Rectangulo(5, 12);
		Cilindro cil1 = new Cilindro(4, 8);
		Esfera esf1 = new Esfera(6);
		
		System.out.println(cir1.area());
		System.out.println("----");
		
		System.out.println(rec1.area());
		System.out.println("----");
		
		System.out.println(cil1.volumen());
		System.out.println("----");
		
		System.out.println(esf1.volumen());		
	}
}
