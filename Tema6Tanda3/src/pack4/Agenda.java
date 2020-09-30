package pack4;

import java.util.HashMap;
import java.util.Iterator;

public class Agenda {
	HashMap<String, String[]> entradas = new HashMap<String, String[]>();
	int maxEntradas;
	
	public Agenda(int maxEntradas) {
		this.maxEntradas = maxEntradas;
	}
	
	public void aniadeEntrada(String nombre, String[] telefonos) {
		if(entradas.size() == maxEntradas) {
			System.out.println("Agenda Completa");
			return;
		}
		
		if(entradas.containsKey(nombre)) {
			System.out.println("Su agenda ya dispone de los siguientes teléfonos para " + nombre + ": ");
			for (String tlf : entradas.get(nombre)) {
				System.out.println(tlf);
			}
			System.out.println("desea sustituirlos por los nuevos? [s/n]");	
			char opcion;
			do {
				opcion = Consola.leeChar();
			} while (opcion != 's' && opcion != 'n');
			
			if(opcion == 's') 
				entradas.put(nombre, telefonos);
			return;
		}
		
		entradas.put(nombre, telefonos);
	}
	
	public void ver() {
		System.out.println("<Agenda>");
		Iterator<String> it = entradas.keySet().iterator();
		while(it.hasNext()) {
			String clave = it.next();
			System.out.print(clave + ": ");
			for (String tlf : entradas.get(clave)) {
				System.out.print(tlf + " - ");
			}
			System.out.println();
		}
		System.out.println("-----------");
	}
	
	public String buscaNombre(String nombre) {
		if(!entradas.containsKey(nombre))
			return nombre + " no se encuentra en la agenda";
		String vuelta = "";
		for (String tlf : entradas.get(nombre)) {
			vuelta += tlf + " ";
		}
		return vuelta;
	}
	
	public int cuantosTelefonos(String tlfBuscado) {
		int cantidad = 0;
		
		Iterator<String> it = entradas.keySet().iterator();
		while(it.hasNext()) {
			String key = it.next();
			
			for (String tlfActual : entradas.get(key)) {
				if(tlfActual.equals(tlfBuscado)) {
					cantidad++;
					break;
				}
			}
		}
		
		return cantidad;
	}
}
