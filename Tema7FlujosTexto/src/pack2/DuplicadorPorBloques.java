package pack2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DuplicadorPorBloques extends DuplicadorDeArchivo{
	private final static int BLOQUE = 20;
	
	public DuplicadorPorBloques() {
		super();
	}
	
	public DuplicadorPorBloques(String ruta) {
		super(ruta);
	}
	
	@Override
	public boolean duplicar(String destino) throws IOException {
		if(!copiaPosible(destino))
			return false;
		
		char[] bloqueLeido = new char[BLOQUE];
		BufferedReader br = new BufferedReader(new FileReader(getRuta()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(destino));
		
		int leidos = br.read(bloqueLeido);
		
		while(leidos != -1) {
			bw.write(bloqueLeido, 0, leidos);
			leidos = br.read(bloqueLeido);
		}
		
		br.close();
		bw.close();
		return true;
	}
}
