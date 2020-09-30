package compresor;

import java.io.File;
import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		Compresor.crearBinarioAleatorio("inicial.bin");
		
		Compresor.comprimir("inicial.bin");
		
		Compresor.desComprimir("comprimido.bin");
		
		File inicial = new File("inicial.bin");
		System.out.println(inicial.length());
		
		File comprimido = new File("comprimido.bin");
		System.out.println(comprimido.length());
		
		File descomprimido = new File("final.bin");
		System.out.println(descomprimido.length());
		
		if(Compresor.contenidoArchivo("inicial.bin").equals(Compresor.contenidoArchivo("final.bin"))){
			System.out.println("correcto");
		}
		else {
			System.out.println("incorrecto");
		}
		
	}
}
