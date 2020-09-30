package ejer4;

public class Principal {
	public static void main(String[] args) {
		
		
		
		System.out.print("Numero 1: ");
		NumCapic nc1 = new NumCapic(Consola.leeLong());		
		System.out.print("Numero 2: ");
		NumCapic nc2 = new NumCapic(Consola.leeLong());
		System.out.print("Numero 3: ");
		NumCapic nc3 = new NumCapic(Consola.leeLong());
		System.out.print("Numero 4: ");
		NumCapic nc4 = new NumCapic(Consola.leeLong());
		System.out.print("Numero 5: ");
		NumCapic nc5 = new NumCapic(Consola.leeLong());
		
		System.out.println(nc1.capicua() ? "El numero " + nc1.num + " es CAPICUA." : "El numero " + nc1.num + " NO es CAPICUA." );
		System.out.println(nc2.capicua() ? "El numero " + nc2.num + " es CAPICUA." : "El numero " + nc2.num + " NO es CAPICUA." );
		System.out.println(nc3.capicua() ? "El numero " + nc3.num + " es CAPICUA." : "El numero " + nc3.num + " NO es CAPICUA." );
		System.out.println(nc4.capicua() ? "El numero " + nc4.num + " es CAPICUA." : "El numero " + nc4.num + " NO es CAPICUA." );
		System.out.println(nc5.capicua() ? "El numero " + nc5.num + " es CAPICUA." : "El numero " + nc5.num + " NO es CAPICUA." );
		
	}
}