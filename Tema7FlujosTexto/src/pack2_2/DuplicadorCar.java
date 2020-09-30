package pack2_2;

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
		File f = new File(getRuta());
		if(!f.exists())
			return false;
		if(getRuta() == destino)
			return false;
		
		BufferedReader br = new BufferedReader(new FileReader(f));
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
