package pack2;

public class PruebaMatriz1 {
	public static void main(String[] args) {
		
		Matriz1 m1 = new Matriz1();
		
		m1.cargarPorFilas();
		m1.ver();
		
		System.out.println();
		
		m1.cargarPorColumnas();
		m1.ver();
		
		int origen, destino;
		do {
			System.out.print("Fila origen: ");
			origen = Consola.leeInt();
			System.out.print("Fila destino: ");
			destino = Consola.leeInt();
		} while (m1.intercambiarFilas(origen, destino));
		m1.ver();
		System.out.println();
		
		m1.intercambiarColumnas(1, m1.filacentral());
		m1.ver();
		
		int[] negativos = m1.negativos();
		for (int i = 0; i < negativos.length; i++) {
			System.out.print(negativos[i] + " ");
		}
		System.out.println();
		
		Matriz1[] matrices = {new Matriz1(3,3), new Matriz1(3,3), new Matriz1(3,3), new Matriz1(3,3)};
		
		for (int i = 0; i < matrices.length; i++) {
			System.out.println("Matriz " + (i+1));
			matrices[i].cargarPorFilas();
			matrices[i].ver();
		
		}
		
		for (int i = 0; i < matrices.length; i++) {
			if(matrices[i].getEnteros()[0].length >= 5) {
				matrices[i].invertirFilas();
				matrices[i].ver();				
			}
		}
	}
}
