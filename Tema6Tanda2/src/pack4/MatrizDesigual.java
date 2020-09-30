package pack4;

public class MatrizDesigual {
	private Float[][] nums;
	
	public MatrizDesigual() {
		nums = new Float[4][];
	}
	
	public void cargarFila(Float[] array, int fila) {
		if (nums.length <= fila || fila < 0)
			return;
		nums[fila] = array;
	}
	
	public void cargarFila(int n, int fila) {
		if (nums.length <= fila || fila < 0)
			return;
		nums[fila] = new Float[n];
		float valor;
		int utilizados = 0;
		
		for (int i = 0; i < n; i++) {
			do {
				valor = aleatorio(-50, 50);
			}while(estaRepetido(valor, fila, utilizados));
			nums[fila][i] = valor;
			utilizados++;
		}
	}
	
	private boolean estaRepetido(float valor, int fila, int utilizados) {
		for (int i = 0; i < utilizados; i++) {
			if(nums[fila][i] == valor)
				return true;
		}
		return false;
	}
	
	private Float aleatorio(int min, int max) {
		return (float) ((Math.random()*(max - min +1))+min);
	}
	
	public void verFila(int posicion) {
		if(posicion < 0 || posicion > nums.length || nums[posicion] == null ) {
			System.out.println("Error: linea inaccesible");
			return;
		}
		
		for (int i = 0; i < nums[posicion].length; i++) {
			System.out.print(nums[posicion][i] + " ");
		}
	}
	
	public void verMatriz() {
		for (int i = 0; i < nums.length; i++) {
			if(nums[i] == null)
				System.out.print("Linea no instanciada");
			else
				for (int j = 0; j < nums[i].length; j++) {
					System.out.print(nums[i][j] + " ");
				}
			System.out.println();
		}
	}
	
	private static Float siguientePrimo(Float num) {
		boolean esPrimo;
		
		do {
			esPrimo = true;
			num+=2;
			int div;
			for (div = 3; div <= Math.sqrt(num) && esPrimo; div+=2) {
				if(num % div == 0) {
					esPrimo = false;
				}
			}
			
		}while(!esPrimo);
		
		return num;
	}
	
	public static void main(String[] args) {
		MatrizDesigual md1 = new MatrizDesigual();
		
		Float[] potenciasDe3 = new Float[7];
		Float mult = 1f;
		for (int i = 0; i < potenciasDe3.length; i++) {
			potenciasDe3[i] = mult;
			mult*=3;
		}
		
		md1.cargarFila(potenciasDe3, 0);
		
		Float[] cosenos = new Float[10];
		for (int i = 0, angulo = 0; i < cosenos.length; i++, angulo+=5) {
			cosenos[i] = (float) Math.cos(Math.toRadians(angulo));
		}
		
		md1.cargarFila(cosenos, 1);
		
		md1.cargarFila(10, 2);
		
		Float[] primos = new Float[20];
		primos[0] = 2f;
		Float actual = 3f;
		
		for (int i = 1; i < primos.length; i++) {
			primos[i] = actual;
			actual = siguientePrimo(actual);
		}
		
		md1.cargarFila(primos, 3);
		
		md1.cargarFila(4, 5);
		
		md1.verMatriz();
	}
}
