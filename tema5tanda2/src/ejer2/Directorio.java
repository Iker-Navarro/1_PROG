package ejer2;

public class Directorio {
	private static final int MAXCAR = 40;
	
	private String nombre;
	private long tamanio;
	
	public Directorio(String nombre, long tamanio) {
		this.nombre = nombre;
		this.tamanio = tamanio;
	}
	
	public long getTamanio() {
		return tamanio;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setTamanio(long tamanio) {
		this.tamanio = tamanio;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void ver() {
		System.out.println("Directorio: " + nombre + "\t-\t" + tamanio + "B");
	}
	
	public int niveles() {
		String str = nombre.trim();
		int cont = 1;
		while(str.indexOf('\\') != -1) {
			str = str.substring(str.indexOf('\\')+1);
			cont ++;
		}
		return cont;
	}
	
	public String ultimo() {
		return nombre.substring(nombre.lastIndexOf('\\')+1);
	}
	
	private boolean carIlegal() {
		if(nombre.indexOf('*') != -1 || nombre.indexOf('#') != -1 || nombre.indexOf('@') != -1) {
			return true;
		}
		return false;
	}

	public void descomTamanio() {
		long tam = tamanio;
		for(int i = 1; tam!=0; tam/=1024, i++) {
			String unidad = "";
			switch(i) {
			case 1:
				unidad = "Byte";
				break;
			case 2:
				unidad = "Kb";
				break;
			case 3:
				unidad = "Mb";
				break;
			case 4:
				unidad = "Gb";
				break;
			case 5:
				unidad = "Tb";
				break;
			case 6:
				unidad = "Hb";
				break;
			}
			System.out.print(tam%1024 == 0 ? "" : tam%1024 + " " + unidad + " ");
		}
		System.out.println();
	}
	
	private boolean barrasContiguas() {
		String str = nombre;
		for(int i = 1; i < niveles()-1; i++) {
			if(str.charAt(str.indexOf('\\')+1) == '\\')
				return true;
			str = str.substring(str.indexOf('\\') + 1);
		}
		return false;
	}
	
	public String dirValido() {
		int nProblemas = 0; 
		String returnString = "Directorio invalido:\n";
		
		if(nombre.length() > MAXCAR) {
			nProblemas++;
			returnString += "["+ nProblemas + "]: Longitud máxima [" + MAXCAR + "] excedida [" + nombre.length() + "].\n";
		}
		if(carIlegal()) {
			nProblemas++;
			returnString += "["+ nProblemas + "]: Caracter ilegal encontrado.\n";
		}
		if(barrasContiguas()) {
			nProblemas++;
			returnString += "["+ nProblemas + "]: Dos caracteres \"\\\" contiguos encontrados.\n";
		}
		if(nombre.charAt(1) != ':' || !((nombre.charAt(0) >= 'A' && nombre.charAt(0) <= 'Z') || (nombre.charAt(0) >= 'a' && nombre.charAt(0) <= 'z'))) {
			nProblemas++;
			returnString += "["+ nProblemas + "]: El directorio debe comenzar por una unidad de disco.\n ";
		}
		
		return nProblemas == 0 ? "Directorio correcto." : returnString;
	}
	
	public void compactarDir() {
		String str = nombre.toLowerCase().trim();
		int nvl = niveles();
		nombre = "";
		
		for(int i = 0; i < nvl - 1; i++) {
			nombre+=str.substring(0, str.indexOf('\\')).trim() + "\\";
			str = str.substring(str.indexOf('\\') + 1);
		}
		nombre += str.trim();
	}
	
	public boolean mismoUltimoNivel(Directorio other){
		String ultimoN1 = this.niveles() == 1 ? this.nombre : this.nombre.substring(this.nombre.lastIndexOf("\\") + 1);
		String ultimoN2 = other.niveles() == 1 ? other.getNombre() : other.getNombre().substring(other.getNombre().lastIndexOf("\\") + 1);
		if(ultimoN1.equals(ultimoN2)) {
			return true;
		}
		return false;
	}
}
