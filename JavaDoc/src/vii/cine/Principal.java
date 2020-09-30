package vii.cine;

import java.util.ArrayList;

public class Principal {
	private static void verArrayList(ArrayList<Integer> arrList) {
		for (Integer salaVacia : arrList) {
			System.out.print(salaVacia + " ");
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		
		Cine c1 = new Cine("Florida", 8);
		
		verArrayList(c1.libres());
		
		c1.aniadePeli(new Pelicula("Peli1", 2003, 135));
		c1.aniadePeli(new Pelicula("Peli2", 1996, 90), 3);
		c1.aniadePeli(new Pelicula("Peli3", 1996, 95), 8);
		c1.aniadePeli(new Pelicula("Peli4", 2013, 135));
		c1.aniadePeli(new Pelicula("Peli5", 2013, 135));
		c1.aniadePeli(new Pelicula("Peli6", 2013, 135));
		c1.aniadePeli(new Pelicula("Peli7", 2013, 80));
		c1.aniadePeli(new Pelicula("Peli8", 2013, 135));
		c1.aniadePeli(new Pelicula("Peli9", 2013, 65));
		c1.aniadePeli(new Pelicula("Peli6", 1999, 140), 8);
		
		c1.ver();
		
		verArrayList(c1.libres());
		c1.quitaPeli("Peli6");
		
		verArrayList(c1.libres());
		
		c1.ver();
		
		c1.aniadePeli(new Pelicula("Peli6", 1999, 140));
		
		c1.ver();
		
		verArrayList(c1.libres());
		
		c1.quitaPeli("Peli6");
		c1.quitaPeli("Peli3");
		c1.ver();
		
		c1.cambiaDeSala(new Pelicula("Peli1", 2003, 135));
		
		c1.ver();
		
		c1.cambiaDeSala(new Pelicula("Peli1", 2003, 135));
		
		c1.ver();
		
		ArrayList<String> pelisCortas = c1.pelisMasCortas(1, 40);
		for (String nombre : pelisCortas) {
			System.out.print(nombre + " ");
		}
		System.out.println();
		
		c1.reseteaCine();
		
		c1.ver();
		
		
	}
}
