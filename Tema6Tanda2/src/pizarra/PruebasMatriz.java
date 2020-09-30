package pizarra;

public class PruebasMatriz {
	public static void main(String[] args) {
		final int N = 5, M = 5;
		int[][] m = new int[N][M];
		
		cargar(m);
		
		ver(m);
		
		ordenarFilas(m);
		
		System.out.println("---------------");
		
		ver(m);
	}
	
	private static void cargar(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				m[i][j] = random((i*10+1), ((i+1)*10));
			}
		}
	}
	
	private static void ver(int[][] m) {
		for(int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	private static void ordenarFilas(int[][] m) {
		int aux;
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length - 1; j++) {
				for(int k = 0; k < m[i].length - 1 - j; k++) {
					if(m[i][k + 1] < m[i][k]) {
						aux = m[i][k];
						m[i][k] = m[i][k + 1];
						m[i][k + 1] = aux;
					}
				}
			}
		}
	}
	
	private static int random(int min, int max) {
		return (int) ((Math.random()*(max - min + 1)) + min);
	}
}
