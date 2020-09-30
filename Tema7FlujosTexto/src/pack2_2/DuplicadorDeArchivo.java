package pack2_2;

import java.io.IOException;

public abstract class DuplicadorDeArchivo {
	final static String POR_DEFECTO = "ORIGINAL.txt";
	
	private String ruta;
	
	public DuplicadorDeArchivo(String ruta) {
		this.ruta = ruta;
	}
	
	public DuplicadorDeArchivo() {
		ruta = POR_DEFECTO;
	}
	
	public abstract boolean duplicar(String destino) throws IOException;
	
	public String getRuta() {
		return ruta;
	}
}
