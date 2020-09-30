package tanda1;

public class Tanda1Eje10 {
	public static void main(String[] args) {
		int a, b, aux;
		
		System.out.print("Valor de A: ");
		a = Consola.leeInt();
		
		System.out.print("Valor de B: ");
		b = Consola.leeInt();

		aux = a % b;
		
		System.out.print("El MCD entre " + a + " y " + b + " es ");
		while(aux != 0) {
			a = b;
			b = aux;
			aux = a % b;
		}
		System.out.print(b);
	}
}
