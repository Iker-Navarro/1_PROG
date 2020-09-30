package pack2;

import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		DuplicadorCar dc = new DuplicadorCar();
		System.out.println(dc.duplicar("individual.txt") ? "Archivo duplicado" : "Error en la copia");
		
		DuplicadorPorBloques dpb = new DuplicadorPorBloques();
		System.out.println(dpb.duplicar("bloques.txt") ? "Archivo duplicado" : "Error en la copia");
		
		DuplicadorValidado dv = new DuplicadorValidado();
		System.out.println(dv.duplicar("validos.txt") ? "Archivo duplicado" : "Error en la copia");
	}
}
