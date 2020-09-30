package ejer4;

public class CalculosVarios {
	private static int randomEntero(int minimo, int maximo) {
		return (int) (Math.random()*(maximo - minimo + 1) + minimo);
	}
	
	private static int dado() {
		return randomEntero(1, 6);
	}
	
	public static void main(String[] args) {
		//1
		String str_int1 = "12";
		String str_int2 = "34";
		
		System.out.println(str_int1 + str_int2);
		System.out.println(Integer.parseInt(str_int1) + Integer.parseInt(str_int2));
		
		//2
		String str_f1 = "15.5";
		String str_f2 = "4.5f";
		
		System.out.println(str_f1 + str_f2);
		System.out.println(Float.parseFloat(str_f1) + Float.parseFloat(str_f2));
		
		//3
		Integer n1 = 20, n2 = Integer.parseInt("30");
		int resultado = n1 * n2;
		System.out.println(resultado);
		
		//4
		System.out.println(Math.ceil(2.3));
		
		//5
		System.out.println(Math.round(Math.PI*2));
		
		//6
		System.out.println(Math.abs(Math.sin(Math.toRadians(-2))));
		
		//7
		System.out.println(Math.min(Math.sin(Math.toRadians(180)), Math.sin(Math.toRadians(270))));
		
		//8
		for(int i = 0; i < 10; i++) {
			System.out.println(Math.pow(3, i));
		}
		
		//9
		System.out.println(randomEntero(0, 10));
		
		//10
		System.out.println(randomEntero(10, 20));
		
		//11
		for(int i = 0; i < 5; i++) 
			System.out.println(randomEntero(10, 20));
		
		//12
		int n = 10;
		for(int i = 0; i < n; i++)
			System.out.println(dado());
		
		//13
		System.out.println(randomEntero(-20, 10));
		
		//14
		System.out.println((char) ('A' + randomEntero(0, 25)));
		
	}
}
