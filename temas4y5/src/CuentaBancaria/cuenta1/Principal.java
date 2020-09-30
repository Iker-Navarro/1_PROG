package cuenta1;

public class Principal {

	public static void main(String[] args) {
		
		CuentaBancaria c1 = new CuentaBancaria("Juan Lopez");
		CuentaBancaria c2 = new CuentaBancaria("Ana Aguirre");
		CuentaBancaria c3 = new CuentaBancaria("Jorge Gil", 800);
		
		c1.ver();
		c2.ver();
		c3.ver();
		/*
		c3.gastar(801);
		c3.ver();
		c3.gastar(800);
		c3.ver();
		c3.ingresar(800);
		*/
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
