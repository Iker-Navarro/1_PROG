package estaticos;

public class Principal {
	public static void main(String[] args) {
		// 1
		/*
		int n1, n2;
		System.out.println("\nIntroduce 0 en alguno de los numeros para finalizar");
		do {
			System.out.println("n2 debe ser mayor que n1");
			System.out.print("n1: ");
			n1 = Consola.leeInt();
			
			System.out.print("n2: ");
			n2 = Consola.leeInt();
		}while(n1 > n2);
		
		while(n1 != 0 && n2 != 0) {
			BiblioStatic.verMultiplos5(n1, n2);
			
			System.out.println("\nIntroduce 0 en alguno de los numeros para finalizar");
			do {
				System.out.println("n2 debe ser mayor que n1");
				System.out.print("n1: ");
				n1 = Consola.leeInt();
				
				System.out.print("n2: ");
				n2 = Consola.leeInt();
			}while(n1 > n2 && n1!=0 && n2!=0);
		}
		
		System.out.println();
		*/
		//2
		/*
		for(int i = 1; i <= 5; i++) {
			System.out.print("Introduce el caracter nº" + i + ": ");
			char car = Consola.leeChar();
			
			switch(BiblioStatic.tipoCaracter(car)) {
			case 1:
				System.out.println("Letra MAYUSCULA");
				break;
			case 2:
				System.out.println("Letra MINUSCULA");
				break;
			case -1:
				System.out.println("No es una LETRA");
			}
		}
		
		System.out.println();
		*/
		//3
		/*
		System.out.println("Calcular n primeras potencias de 7; 7^n");
		System.out.print("n = ");
		int n = Consola.leeInt();
		
		for(int i = 0; i <= n; i++) {
			System.out.println("7^"+i+" = " + BiblioStatic.calcularPotencia(7, i));
		}
		
		System.out.println();
		*/
		//4
		/*
		int x;
		double resultado = 0;
		System.out.println("valor de x para calcular x/sumatorio1 + x^2/sumatorio2 + ... hasta 8 terminos.");
		System.out.print("x = ");
		x = Consola.leeInt();
		for(int i = 1; i <= 8; i++) {
			resultado += (BiblioStatic.calcularPotencia(x, i)/(double)BiblioStatic.sumatorio(i));
		}
		
		System.out.println("El resultado es: " + resultado);
		*/
		//5
		/*
		int numero;
		System.out.println("Introduce un numero para obtener su tabla de multiplicar [Entre 1-10]");
		do {
			numero = Consola.leeInt();
		}while(numero > 10 || numero < 1);
		BiblioStatic.verTabla(numero);
		*/
		//6
		int numero;
		System.out.println("Introduce un numero para obtener su tabla de multiplicar [Entre 1-20]");
		numero = Consola.leeInt();
		while(numero != 0) {
			if(!BiblioStatic.verTablaRestringida(numero))
				System.out.println("ERROR el valor debe estar comprendido entre 1 y 20");
			
			System.out.println("Introduce un numero para obtener su tabla de multiplicar [Entre 1-20]");
			numero = Consola.leeInt();
		};
	}
}
