package estaticos;

public class BiblioStatic {
	
	static void verMultiplos5(int inicio, int fin) {
		int x = inicio;
		while(x % 5 != 0) {
			x++;
		}
		
		for(;x <= fin;x+=5) {
			System.out.println(x + " ");
		}
	}
	
	static int tipoCaracter(char car) {
		if(car >= 'A' && car <= 'Z') {
			return 1;
		}
		else if (car >= 'a' && car <= 'z') {
			return 2;	
		}
		
		return -1;
	}
	
	static long calcularPotencia(int base, int potencia) {
		long resp = 1;
		
		for(int i = 0; i < potencia; i++) {
			resp*=base;
		}
		
		return resp;
	}
	
	static long sumatorio(int num) {
		long resp = 0;
		for(int i = 1; i <= num; i++) {
			resp+=i;
		}
		
		return resp;
	}
	
	static void verTabla(int num) {
		for(int i = 1; i<=10; i++) {
			System.out.println(num + " X " + i + " = " + num*i);
		}
	}
	
	static boolean verTablaRestringida(int num) {
		if(num >= 1 && num <= 20) {
			verTabla(num);
			return true;
		}
		return false;	
	}
}
