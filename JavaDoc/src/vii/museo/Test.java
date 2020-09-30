package vii.museo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

public class Test {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		VisitasDia vd = new VisitasDia(new int[] {12, 5});
		
		vd.aniadeVisita(new Visita("Persona 1", 10, new Hora(14, 0)));
		vd.aniadeVisita(new Visita("Persona 2", 8, new Hora(13, 0)));
		vd.aniadeVisita(new Visita("Persona 3", 43, new Hora(13, 0)));
		vd.aniadeVisita(new Visita("Persona 4", 13, new Hora(11, 0)));
		vd.aniadeVisita(new Visita("Persona 5", 50, new Hora(11, 20)));
		vd.aniadeVisita(new Visita("Persona 6", 15, new Hora(23, 59)));
		vd.aniadeVisita(new Visita("Persona 7", 15, new Hora(0, 1)));
		
		vd.guardarAFichero("visitasGuardadas.obj");
		System.out.println("-------------");
		VisitasDia.verFichero("visitasGuardadas.obj");
		System.out.println("---");
		vd.cargarVisitas("visitasGuardadas.obj");
		System.out.println("---");
		vd.cargarVisitas("visitasGuardadas.obj");
		System.out.println("---");
		vd.cargarVisitas("visitasGuardadas.obj");
		System.out.println("---");
		vd.cargarVisitas("visitasGuardadas.obj");
		
		if(vd.actualizaVisita("Persona 2"))
			vd.verVisitas();
		else
			System.out.println("no se han realizado cambios");
		
		vd.crearInforme();
		
		HashMap<Hora, Integer> libres = vd.mapaLibres();
		Iterator<Hora> it = libres.keySet().iterator();
		while (it.hasNext()) {
			Hora hora = it.next();
			System.out.println(hora + " -> " + libres.get(hora) + " libres");	
		}
		
		System.out.println(vd.tiempoDeVisitaMasCercana(11, 25));
		
		System.out.println(vd.borrarVisitasPasadas());
		VisitasDia.verVisitasPasadas("visitasPasadas_00_16.bin");
		
		vd.verVisitas();
	}
}
