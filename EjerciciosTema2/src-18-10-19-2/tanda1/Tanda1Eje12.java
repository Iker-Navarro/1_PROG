package tanda1;

public class Tanda1Eje12 {
	public static void main(String[] args) {
		int n, digitos = 0;
		System.out.print("Introduce un numero: ");
		n = Consola.leeInt();
		
		for(int i = n; i >= 1; i/=10) {
			digitos++;
		}
		System.out.print(n + " tiene " + digitos + " digitos:");
	}
}
