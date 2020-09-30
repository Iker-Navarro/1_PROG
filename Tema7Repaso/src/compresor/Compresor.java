package compresor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Compresor {
	public static void comprimir(String nomFich) throws IOException {
		File f = new File(nomFich);
		if(!f.exists() || f.length() == 0l)
			return;
		
		FileInputStream fis = new FileInputStream(nomFich);
		DataOutputStream dos = new DataOutputStream(new FileOutputStream("comprimido.bin"));
		
		int siguiente, repeticiones = 1, actual = fis.read();
		
		while (fis.available() > 0) {
			siguiente = fis.read();
			if(siguiente == actual)
				repeticiones++;
			else {
				dos.writeByte(actual);
				dos.writeInt(repeticiones);
				actual = siguiente;
				repeticiones = 1;
			}
		}
		dos.writeByte(actual);
		dos.writeInt(repeticiones);
		
		fis.close();
		dos.close();
		
	}

	public static void desComprimir(String nomFich) throws IOException {
		File f = new File(nomFich);
		if(!f.exists() || f.length() == 0l)
			return;
		
		System.out.println("nombre de archivo descomprimido? ");
		String descomprimido = Consola.leeString();
		
		DataInputStream dis = new DataInputStream(new FileInputStream(f));
		FileOutputStream fos = new FileOutputStream(descomprimido);
		
		while(dis.available() > 0) {
			byte b = dis.readByte();
			int cant = dis.readInt();
			
			byte[] contenido = new byte[cant];
			for(int i = 0; i < cant; i++) {
				contenido[i] = b;
			}
			
			fos.write(contenido);
		}
		
		dis.close();
		fos.close();
	}
	
	public static void crearBinarioAleatorio(String nomFich) throws IOException {
		FileOutputStream fos = new FileOutputStream(nomFich);
		for (int i = 0; i < 100; i++) {
			byte num = (byte) aleatorio(1,127);
			int repeticiones = aleatorio(1, 500);
			
			byte[] contenido = new byte[repeticiones];
			for (int j = 0; j < repeticiones; j++) {
				contenido[j] = num;
			}
			fos.write(contenido);
		}
		fos.close();
	}
	
	public static String contenidoArchivo(String nomFich) throws IOException {
		File f = new File(nomFich);
		if(!f.exists())
			return null;
		
		FileInputStream fis = new FileInputStream(nomFich);
		String contenido = "";
		while(fis.available() > 0) {
			contenido+=fis.read();
		}
		fis.close();
		return contenido;
	}
	
	private static int aleatorio(int min, int max) {
		return (int) (Math.random() *(max - min + 1) + min);
	}
}
