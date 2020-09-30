package tanda1;

public class Tanda1Eje13 {
	public static void main(String[] args) {
		int n, p, pInv, digitos = 0;
		System.out.print("Introduce un número: ");
		n = Consola.leeInt();
		for(int i = n; i >= 1; i/=10) {
			digitos++;
		}
		do {
			System.out.print("Introduce una posición: ");
			p = Consola.leeInt();
		}while(p > digitos || p < 1);
		
		pInv = digitos - p + 1;
		for(int i = n, j = 1; j<=pInv; i/=10, j++) {
			if(j == pInv) {
				System.out.print("El digito nº" + p + " del numero " + n + " es " + (i % 10));
			}
		}
	}
}
