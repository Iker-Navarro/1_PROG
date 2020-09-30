package pack3;

import java.io.File;
import java.io.Serializable;

public class Imagen implements Serializable{

	private String nombre, extension;
	private long tamanio;

	public Imagen(String nombre, long tamanio) {
		this.nombre = nombre;
		this.extension = nombre.substring(nombre.lastIndexOf(".") + 1);
		this.tamanio = tamanio;
		
	}

	@Override
	public String toString() {
		return nombre + " ( " + desglosarTamanio() + ")";
	}

	private String desglosarTamanio() {
		String[] magnitud = {"B", "KB", "MB", "GB", "TB", "HB"};
		String vuelta = "";
		long tam = tamanio;
		for(int i = 0; tam!=0; tam/=1024, i++) {
			vuelta = (tam%1024) + magnitud[i] + " " + vuelta;
		}
		
		return vuelta;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Imagen other = (Imagen) obj;
		if (extension == null) {
			if (other.extension != null)
				return false;
		} else if (!extension.equals(other.extension))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		if (tamanio != other.tamanio)
			return false;
		return true;
	}

	public String getExtension() {
		return extension;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public static boolean esImagen(String nomFich) {
		File f = new File(nomFich);
		
		if(!f.exists() || f.isDirectory())
			return false;
		
		final String[] EXTENSIONES_PERMITIDAS = {".png", ".jpg", ".jpeg", ".gif"};
		
		for (String extension : EXTENSIONES_PERMITIDAS) {
			if(nomFich.substring(nomFich.lastIndexOf(".")).equalsIgnoreCase(extension))
				return true;
		}
		
		return false;
	}
}
