package pack2;

import java.io.File;
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
	
	public boolean copiaPosible(String destino) {
		File f = new File(ruta);
		if(!f.exists())
			return false;
		if(getRuta().equals(destino))
			return false;
		return true;
	}
	
	public abstract boolean duplicar(String destino) throws IOException;
	
	public String getRuta() {
		return ruta;
	}
}
