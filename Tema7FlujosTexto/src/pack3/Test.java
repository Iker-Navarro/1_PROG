package pack3;

import java.io.IOException;

public class Test {
	public static void main(String[] args) throws IOException {
		GestionAgenda ga = new GestionAgenda("Agenda.txt");
		
		ga.ver();
		ga.aniadePersona(new Persona("nuevaPersona", "nuevoTel", "nuevoLugar", 20));
		
		System.out.println("----------");
		
		ga.ver();
		
		System.out.println("---------");
		
		Persona buscada = ga.buscaPersona("nuevaPersona");
		if(buscada == null) 
			System.out.println("Persona no encontrada");
		else
			System.out.println(buscada);
		
		System.out.println("---------");
		
		if(ga.eliminarPersona(buscada))
			System.out.println("Persona eliminada");
		
		ga.ver();
	}
}
