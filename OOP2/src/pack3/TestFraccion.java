package pack3;

public class TestFraccion {
	public static void main(String[] args) {
		Fraccion f1 = new Fraccion(14, 16);
		Fraccion f2 = new Fraccion(2, 10);
		Fraccion f3 = new Fraccion(6, 20);
		Fraccion f4 = new Fraccion(2, 1);
		
		f1.ver();
		f1.simplificar();
		f1.ver();
		
		f1.sumale(f2);
		f1.ver();
		
		f1.restale(f3);
		f1.ver();
		
		f1.multiplica(f4);
		f1.ver();
	
		f1.divide(f4);
		f1.ver();
	}
}
