package pack5;

public class CuadradoMagico {
	private static int[][] crearCuadradoMagico(int lado) {
		if(lado < 1 || lado % 2 == 0)
			return null;
		int[][] cm = new int[lado][lado];
		
		int i=0, j=lado/2, actual = 1;;
		while(actual <= lado*lado) {
			
			cm[i][j] = actual;
			actual++;
			int[] posAnt = {i, j};
			
			i--;
			j++;
			
			if(i < 0) 
				i = lado - 1;
			if(j >= lado)
				j = 0;
			if(cm[i][j] != 0) {
				i = posAnt[0] + 1;
				j = posAnt[1];
			}
		
		}
		
		
		return cm;
	}
	
	private static void ver(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	public static void main(String[] args) {
		int[][] cm = crearCuadradoMagico(13);
		if (cm == null) 
			System.out.println("Dimension erronea");
		else
			ver(cm);
	}
}
