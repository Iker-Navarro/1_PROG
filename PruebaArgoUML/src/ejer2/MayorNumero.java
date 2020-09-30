package ejer2;

public class MayorNumero {
	public static int obt_mayorNumero(int[] lista) {
		int indice, max = Integer.MIN_VALUE;
		for (indice = 0; indice < lista.length; indice++) {
			if (lista[indice] > max) {
				max = lista[indice];
			}
		}
		return max;
	}
}
