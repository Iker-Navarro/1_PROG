import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {
	private static String nuevoNombre(String original) {
		String[] splitted = original.split("\\.");
		String outName = "";
		if(splitted.length < 2){
			return null;
		}
		else {
			if(splitted.length > 2) {
				outName += splitted[0];
				for (int i = 1; i < splitted.length - 1; i++) {
					outName += "." + splitted[i];
				}
				outName += "_CPY." + splitted[splitted.length - 1];
			}
			else {
				outName += splitted[0] + "_CPY." + splitted[1];
			}
		}
		return outName;
	}
	
	public static void copiaImagen(String origen) throws IOException {
		File f = new File(origen);
		if(!f.exists())
			return;
		
		String nombreDestino = nuevoNombre(origen);
		if(nombreDestino == null)
			return;
		
		FileInputStream fis = new FileInputStream(origen);
		FileOutputStream fos = new FileOutputStream(nombreDestino);
		
		int actual = fis.read();
		while(actual != -1) {
			fos.write(actual);
			actual = fis.read();
		}
		
		fos.close();
		fis.close();
		
	}
	
	public static void copiaImagenBloques(String origen) throws IOException {
		
		File f = new File(origen);
		if(!f.exists())
			return;
		
		String nombreDestino = nuevoNombre(origen);
		if(nombreDestino == null)
			return;

		final int BLOQUE = 512;
		FileInputStream fis = new FileInputStream(origen);
		FileOutputStream fos = new FileOutputStream(nombreDestino);
		
		byte[] actuales = new byte[BLOQUE];
		int leidos = fis.read(actuales);
		while(leidos == BLOQUE) {
			fos.write(actuales);
			leidos = fis.read(actuales);
		}
		fos.write(actuales, 0, leidos);

		fos.close();
		fis.close();
	}
	
	public static void copiaImagenDirecta(String origen) throws IOException {
		File f = new File(origen);
		if(!f.exists())
			return;
		
		String nombreDestino = nuevoNombre(origen);
		if(nombreDestino == null)
			return;
		FileInputStream fis = new FileInputStream(origen);
		FileOutputStream fos = new FileOutputStream(nombreDestino);
		
		int cantByte = fis.available();
		byte[] contenido = new byte[cantByte];
		
		fis.read(contenido);
		fos.write(contenido);
		
		fos.close();
		fis.close();
	}
	
	public static void main(String[] args) throws IOException {
		//args[0] = nombre imagen
		if(args.length == 0)
			System.out.println("Sin nombre de archivo no se puede proceder");
		else {
			copiaImagenDirecta(args[0]);
			
		}
	}
}
