package pack2;

import java.util.ArrayList;

public class Matriz1 {
	private static final int NF = 10, NC = 15;
	
	private int[][] enteros;
	
	public Matriz1() {
		enteros = new int[NF][NC];
	}
	
	public Matriz1(int nFilas, int nColumnas) {
		enteros = new int[nFilas][nColumnas];
	}
	
	public int[][] getEnteros() {
		return enteros;
	}
	
	public void cargarPorFilas() {
		for (int i = 0; i < enteros.length; i++) {
			for (int j = 0; j < enteros[i].length; j++) {
				System.out.print("fila " + (i+1) + " Columna " + (j+1) + ": ");
				enteros[i][j] = Consola.leeInt(); 
			}
		}
	}
	
	public void cargarPorColumnas() {
		for (int j = 0; j < enteros[0].length; j++) {
			for (int i = 0; i < enteros.length; i++) {
				System.out.print("fila " + (i+1) + " Columna " + (j+1) + ": ");
				enteros[i][j] = Consola.leeInt(); 
			}
		}
	}
	
	public void ver() {
		for (int i = 0; i < enteros.length; i++) {
			for (int j = 0; j < enteros[i].length; j++) {
				System.out.print(enteros[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void invertirFilas() {
		int aux;
		for (int i = 0; i < enteros.length; i++) {
			for (int j = 0, j2 = enteros[i].length-1; j < j2; j++) {
				aux = enteros[i][j];
				enteros[i][j] = enteros[i][j2];
				enteros[i][j2] = aux;
			}
		}
	}
	
	public boolean intercambiarFilas(int origen, int destino) {
		origen--;
		destino--;
		
		if (origen < 0 || origen > enteros.length || destino < 0 || destino > enteros.length ) 
			return false;
		
		int[] aux = enteros[origen];
		enteros[origen] = enteros[destino];
		enteros[destino] = aux;
		return true;
	}
	
	public boolean intercambiarColumnas(int origen, int destino) {
		origen--;
		destino--;
		
		if (origen < 0 || origen > enteros[0].length || destino < 0 || destino > enteros[0].length ) 
			return false;
		
		int aux;
		for (int i = 0; i < enteros.length; i++) {
			aux = enteros[i][origen];
			enteros[i][origen] = enteros[i][destino];
			enteros[i][destino] = aux;
		}
		return true;
	}
	
	public int[] mayorFila() {
		
		int suma, maximaAnterior = Integer.MIN_VALUE, maximaIndice = -1;
		for (int i = 0; i < enteros.length; i++) {
			suma = 0;
			for (int j = 0; j < enteros[i].length; j++) {
				suma+=enteros[i][j];
			}
			if(suma > maximaAnterior) {
				maximaAnterior = suma;
				maximaIndice = i;
			}
		}
		
		return enteros[maximaIndice];
	}
	
	public int[] negativos() {
		int[] negativos = new int[enteros.length*enteros[0].length]; 
		int utiles = 0;
		
		for (int i = 0; i < enteros.length; i++) {
			for (int j = 0; j < enteros[i].length; j++) {
				if(enteros[i][j] < 0) {
					negativos[utiles] = enteros[i][j];
					utiles++;
				}
			}
		}
		
		int[] vuelta = new int[utiles];
		for (int i = 0; i < vuelta.length; i++) {
			vuelta[i] = negativos[i];
		}
		
		return vuelta;
	}
	
	public int filacentral() {
		return enteros[0].length/2;
	}
}
