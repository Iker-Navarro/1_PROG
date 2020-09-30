package pack2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OperFicheros {
	public static long compruebaTamanio(String nomFich) {
		try {
			FileInputStream fis = new FileInputStream(nomFich);
			int libre = fis.available();
			fis.close();
			return libre;
		} catch (FileNotFoundException e) {
			System.out.println("Error archivo no encontrado");
			return -1;
		} catch(IOException e) {
			System.out.println("Error de Entrada/Salida");
			return -1;
		}
	}
	
	public static ArrayList<String> copiaLineas(String nomFich) throws FileNotFoundException, IOException {		
		ArrayList<String> vuelta = new ArrayList<String>();
		BufferedReader br = new BufferedReader(new FileReader(nomFich));
		String linea = br.readLine();
		while(linea != null) {
			vuelta.add(linea);
			br.readLine();
		}
		br.close();
		return vuelta;
	}
	
	public static void main(String[] args) {
		
		System.out.println("Nombre de fichero?");
		String nomFich = Consola.leeString();
		
		long tam = compruebaTamanio(nomFich);
		
		if(tam == -1)
			System.out.println("ha ocurrido algun error");
		else
			System.out.println(tam);
	
		
		System.out.println("Nombre de fichero?");
		nomFich = Consola.leeString();
		
		try {
			ArrayList<String> lineas = copiaLineas(nomFich);
			for (String linea : lineas) {
				System.out.println(linea);
			}
		} catch (FileNotFoundException e) {
			System.out.println("Error archivo no encontrado");
		} catch (IOException e) {
			System.out.println("Error");
			e.printStackTrace();
		}
	}
}
