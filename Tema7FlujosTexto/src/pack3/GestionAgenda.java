package pack3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestionAgenda {
	private String nomFich;

	public GestionAgenda(String nomFich) {
		this.nomFich = nomFich;
	}
	
	public void ver() throws IOException {
		File f = new File(nomFich);
		if(!f.exists())
			return;
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		String linea = br.readLine();
		while(linea != null) {
			System.out.println(linea);
			linea = br.readLine();
		}
		br.close();
	}
	
	public void aniadePersona(Persona p) throws IOException {
		if(p == null)
			return;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(nomFich, true));
		bw.write(p.toString() + "\n");
		bw.close();
	}

	public Persona buscaPersona(String nombre) throws IOException {
		File f = new File(nomFich);
		if(!f.exists())
			return null;
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		String lineaActual = br.readLine();
		while(lineaActual != null) {
			if(nombreEnLinea(lineaActual, nombre)) {
				br.close();
				return transforma(lineaActual);
			}
			lineaActual = br.readLine();
		}
		br.close();
		return null;
	}

	private boolean nombreEnLinea(String linea, String nombre) {
		String[] separado = linea.split("\t");
		if(separado.length != 4)
			return false;
		
		String nomBuscado = separado[2].trim();
		return nomBuscado.equals(nombre) ? true : false;
	}
	
	private Persona transforma(String linea) {
		String[] propiedades = linea.split("\t");
		if(propiedades.length != 4)
			return null;
		
		return new Persona(propiedades[2].trim(), propiedades[0].trim(), propiedades[3].trim(), Integer.parseInt(propiedades[1].trim()));
	}
	
	public boolean eliminarPersona(Persona p) throws IOException {
		File f = new File(nomFich);
		if(!f.exists())
			return false;
		
		BufferedReader br = new BufferedReader(new FileReader(f));
		
		ArrayList<String> noBorrados = new ArrayList<String>();
		boolean borrados = false;
		
		String lineaActual = br.readLine();
		while(lineaActual != null) {
			if(!p.toString().equals(lineaActual)) 
				noBorrados.add(lineaActual);
			else
				borrados =  true;
			lineaActual = br.readLine();
		}
		br.close();
		
		if(!borrados)
			return false;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		for (String linea : noBorrados) {
			bw.write(linea + "\n");
		}
		
		bw.close();
		return true;
	}
}

