package pack1_2;

import pack1.Vaca;

public class Principal {
	public static void main(String[] args) {
		Vaca miVaca1 = new Vaca();
		Vaca miVaca2 = new Vaca("Vaca 2", 15, "Negro");
		Vaca miVaca3 = new Vaca("Vaca 3", 17);
		
		miVaca1.muu();
		miVaca2.muu();
		miVaca3.muu();
		
		miVaca1.compararEdad(miVaca2);
		
		System.out.println(miVaca1.getNombre());
		
		miVaca1.sumaEdad(1);

	}
}
