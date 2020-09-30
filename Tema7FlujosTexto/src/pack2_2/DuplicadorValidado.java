package pack2_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DuplicadorValidado extends DuplicadorDeArchivo {
	private final static char[] IGNORADOS = {'A', 'B', 'a', 'b'};

	public DuplicadorValidado() {
		super();
	}
	
	public DuplicadorValidado(String ruta) {
		super(ruta);
	}
	
	private boolean esIgnorado(char car) {
		for (char ignorado : IGNORADOS) {
			if(ignorado == car)
				return true;
		}
		return false;
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
			if(!esIgnorado((char)actual))
				bw.write(actual);
			actual = br.read();
		}
		
		br.close();
		bw.close();
		return true;
	}

}
