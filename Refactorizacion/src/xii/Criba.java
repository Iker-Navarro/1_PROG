package xii;

public class Criba
{
	private static boolean[] esPrimo;
	private static int dim;
	private static int[] primos;

	/**
	 *  Generar n�meros primos de 1 a max
	 *  @param max es el valor m�ximo
	 *  @return Vector de n�meros primos
	 */
	
	public static int[] generarPrimos (int max) {
		
		int i,j;
		
		if (max >=2) {

			inicializarCriba(max);
			
			cribar();
			
			rellenarPrimos();
			
			return primos;
			
		} else { // max < 2
			return new int[0];   // Vector vac�o
		}
	}

	private static void rellenarPrimos() {
		int i;
		int j;
		// �Cu�ntos primos hay?
		int cuenta = 0;
		
		for (i=0; i<dim; i++) {
			if (esPrimo[i])
				cuenta++;
		}
		
		primos = new int[cuenta];
		
		for (i=0, j=0; i<dim; i++) {
			if (esPrimo[i])
				primos[j++] = i;
		}
	}

	private static void cribar() {
		int i;
		int j;
		// Criba
		for (i=2; i<Math.sqrt(dim)+1; i++) {
			if (esPrimo[i]) {
				// Eliminar los m�ltiplos de i
				for (j=2*i; j<dim; j+=i)
					esPrimo[j] = false;
			}
		}
	}

	private static void inicializarCriba(int max) {
		int i;
		dim = max +1;
		esPrimo = new boolean[dim];
		
		// Inicializar el array
		for (i=0; i<dim; i++)
			esPrimo[i] = true;
		
		// Eliminar el 0 y el 1, que no son primos
		esPrimo[0] = esPrimo[1] = false;
	}
}

