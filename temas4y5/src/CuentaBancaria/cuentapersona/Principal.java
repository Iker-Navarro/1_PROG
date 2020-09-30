package cuentapersona;

public class Principal {

	public static void main(String[] args) {
		
		CuentaBancaria c1 = new CuentaBancaria(new Persona ("Juan Lopez", 30, 'h'));
		CuentaBancaria c2 = new CuentaBancaria(new Persona ("Ana Aguirre", 31, 'm'));
		CuentaBancaria c3 = new CuentaBancaria(new Persona ("Jorge Gil", 32, 'h'), 800);
		
		c1.ver();
		c2.ver();
		c3.ver();
		
		System.out.println();
		
		c1.ingresar(500);
		c2.ingresar(2000);
		c3.ingresar(100);
		
		System.out.println();
		
		c1.ver();
		c2.ver();
		c3.ver();
		
		System.out.println();

		c1.gastar(100);
		c2.gastar(100);
		c3.gastar(100);
		
		System.out.println();
		
		c1.ver();
		c2.ver();
		c3.ver();
		
		System.out.println();

		c1.gastar(500);
		c2.gastar(500);
		c3.gastar(500);
		
		System.out.println();
		
		c1.ver();
		c2.ver();
		c3.ver();
		
		System.out.println();
		
		c3.ingresar(2000000);
		
		System.out.println();

		c1.ver();
		c2.ver();
		c3.ver();
		
		System.out.println();
		
		c3.gastar(100);
		c1.ingresar(100);
		
		System.out.println();
		
		c1.ver();
		c2.ver();
		c3.ver();
		
	}

}
