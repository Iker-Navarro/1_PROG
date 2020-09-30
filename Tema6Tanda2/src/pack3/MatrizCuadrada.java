package pack3;

public class MatrizCuadrada {
	private int[][] matriz;
	
	public MatrizCuadrada(int tamanio) {
		matriz = new int[tamanio][tamanio];
	}
	
	public void cargar() {
		int valor,utilizados = 0;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				do {
					System.out.print("Valor para la posicion [" + (i + 1) + "][" + (j + 1) + "]: ");
					valor = Consola.leeInt();
				}while(estaRepetido(valor, utilizados));
				matriz[i][j] = valor;
				utilizados++;
			}
		}
	}
	
	private boolean estaRepetido(int num, int utilizados) {
		int topeI = utilizados/matriz.length;
		int topeJ = utilizados%matriz[0].length;
		
		int i = 0;
		for (; i < topeI; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if(matriz[i][j] == num)
					return true;
			}
		}
		for (int j = 0; j < topeJ; j++) {
			if(matriz[i][j] == num)
				return true;
		}
		
		return false;
	}
	
	public void ver() {
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public void cargarIdentidad() {	
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				matriz[i][j] = 0; 
			}
		}
		
		for (int i = 0; i < matriz.length; i++) {
			matriz[i][i] = 1;
		}
	}
	
	public int determinante3x3() {
		if(matriz.length != 3)
			return -1;
		
		int suma = 0, dimension = matriz.length;
		
		//Diagonal abajo derecha (sumados).
		for(int contador = 0; contador < dimension; contador++) {
			int multi = 1;
			for (int i = 0, j = contador, contador2 = 0; contador2 < dimension; i++, j++, contador2++) {
				if(j == dimension)
					j = 0;
				multi *= matriz[i][j];
			}
			suma += multi;
		}
		
		//Diagonales abajo izquierda (restados a la suma total)
		for(int contador = 0; contador < dimension; contador++) {
			int multi = 1;
			for (int i = 0, j = contador, contador2 = 0; contador2 < dimension; i++, j--, contador2++) {
				if(j < 0)
					j = dimension - 1;
				multi *= matriz[i][j];
			}
			suma -= multi;
		}
		return suma;
	}
	
	public boolean esCuadradoMagico() {
		int sumaCorrecta = 0, suma;
		
		//suma de la fila 0
		for (int j = 0; j < matriz[0].length; j++) {
			sumaCorrecta+= matriz[0][j];
		}
		
		//resto de lineas
		for (int i = 1; i < matriz.length; i++) {
			suma = 0;
			
			for (int j = 0; j < matriz[i].length; j++) 
				suma += matriz[i][j]; 
			
			if(suma != sumaCorrecta)
				return false;
		}
		
		//columnas
		for (int j = 0; j < matriz[0].length; j++) {
			suma = 0;
			
			for (int i = 0; i < matriz.length; i++)
				suma += matriz[i][j];
			
			if(suma != sumaCorrecta)
				return false;
		}
		
		//diagonales
		suma = 0;
		for (int i = 0, j = i; i < matriz.length; i++)
			suma+=matriz[i][j];
		
		if(suma != sumaCorrecta)
			return false;
		
		suma = 0;
		for (int i = 0, j = matriz.length-1-i; i < matriz.length; i++)
			suma+=matriz[i][j];
		
		if(suma != sumaCorrecta)
			return false;
		
		return true;
	}
	
	public void trasponer() {
		int aux;
		for (int i = 0; i < matriz.length; i++) {
			for (int j = i + 1; j < matriz.length; j++) {
				aux = matriz[i][j];
				matriz[i][j] = matriz[j][i];
				matriz[j][i] = aux;
			}
		}
	}
	
	private MatrizCuadrada devolverTraspuesta(){
		MatrizCuadrada traspuesta = new MatrizCuadrada(matriz.length);
		for (int i = 0; i < matriz.length; i++) {
			for (int j = i; j < matriz.length; j++) {
				traspuesta.matriz[j][i] = matriz[i][j];
				traspuesta.matriz[i][j] = matriz[j][i];
			}
		}
		return traspuesta;
	}
	
	public boolean esSimetrica() {
		return iguales(devolverTraspuesta()) ? true : false;
	}
	
	public boolean iguales(MatrizCuadrada other) {
		if(this.matriz.length != other.matriz.length)
			return false;
		
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[i].length; j++) {
				if(this.matriz[i][j] != other.matriz[i][j])
					return false;
			}
		}
		return true;
	}
}
