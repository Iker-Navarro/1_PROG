package ejer2;

public class PruebaDirectorio {
	public static void main(String[] args) {

		Directorio d1 = new Directorio("C:\\Users   \\dw1b_alum16 \\documents", 1073741825L);
		Directorio d2 = new Directorio("\\ Users \\\\ dw1b_alum16 \\documents", 2147483648L);
		
		d1.ver();
		d2.ver();
		
		d2.setTamanio(d2.getTamanio()+d1.getTamanio()/2);
		
		d1.descomTamanio();
		
		if(d1.niveles() > d2.niveles())
			d1.ver();
		else if(d2.niveles() > d1.niveles())
			d2.ver();
		else
			System.out.println("Ambas carpetas son del mismo nivel");
		
		System.out.println(d1.dirValido());
		System.out.println(d2.dirValido());

		if(d1.mismoUltimoNivel(d2)) {
			String previo = d2.getNombre().substring(0, d2.getNombre().lastIndexOf("\\") + 1);
			String posterior = d2.getNombre().substring(d2.getNombre().lastIndexOf("\\") + 1);
			d2.setNombre(previo + "D2" + posterior);
		}
		
		d1.ver();
		d2.ver();
		System.out.println();
		
		d1.compactarDir();
		d2.compactarDir();
		
		d1.ver();
		d2.ver();
	}
}
