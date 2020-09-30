package tanda2;

public class Tanda2Eje1 {
	public static void main(String[] args) {
		final int LIMINF = 20, LIMSUP = 40;
		int suma = 0;
		long producto = 1;	
		
		// for(int i = LIMINF % 2 == 0 ? LIMINF + 1 : LIMINF ; i <= LIMSUP; i+=2) { // Para tener en cuenta cambios en las constantes
		for(int i = LIMINF + 1; i <= LIMSUP; i+=2) {
			suma+=i;
			producto*=i;
		}
		
		System.out.println("La suma de numeros impares entre " + LIMINF + " y " + LIMSUP + " es " + suma);
		System.out.println("El producto de numeros impares entre " + LIMINF + " y " + LIMSUP + " es " + producto);
	}
}
