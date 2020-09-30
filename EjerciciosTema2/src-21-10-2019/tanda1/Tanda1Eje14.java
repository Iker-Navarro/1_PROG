package tanda1;

public class Tanda1Eje14 {
	public static void main(String[] args) {
		int dividendo, divisor, resultado = 0;
		
		System.out.print("Introduce el dividendo: ");
		dividendo = Consola.leeInt();
		
		System.out.print("Introduce el divisor: ");
		divisor = Consola.leeInt();
		
		int i = divisor;
		while(i <= dividendo) {
			resultado++;
			i+=divisor;
		}
		
		System.out.print(dividendo+"/"+divisor+"="+resultado);
		
	}
}
