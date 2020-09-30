package pack2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DuplicadorCar extends DuplicadorDeArchivo{
	
	public DuplicadorCar() {
		super();
	}
	
	public DuplicadorCar(String ruta) {
		super(ruta);
	}
	
	@Override
	public boolean duplicar(String destino) throws IOException {	
		
		if(!copiaPosible(destino))
			return false;
		
		BufferedReader br = new BufferedReader(new FileReader(getRuta()));
		BufferedWriter bw = new BufferedWriter(new FileWriter(destino));
		
		int actual = br.read();
		while(actual != -1) {
			bw.write(actual);
			actual = br.read();
		}
		
		br.close();
		bw.close();
		return true;
	}
	
}
