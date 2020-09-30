package pack3;

public class Test {
	public static void main(String[] args) {
		MatrizCuadrada mc = new MatrizCuadrada(3);
		mc.cargar();

		mc.ver();
		System.out.println("Determinante " + mc.determinante3x3());
		
		System.out.println("Es cuadrado mágico? " + mc.esCuadradoMagico());
		
		mc.trasponer();
		mc.ver();
		
		System.out.println("simetrica? " + mc.esSimetrica());
		
		mc.cargarIdentidad();
		mc.ver();
		System.out.println("simetrica? " + mc.esSimetrica());
		
		
		MatrizCuadrada mc2 = new MatrizCuadrada(3);
		mc2.cargarIdentidad();
		
		System.out.println("iguales? " + mc.iguales(mc2));
	
	}
}
