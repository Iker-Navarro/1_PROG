package pack4;

public class Principal {
	public static void main(String[] args) {
		Fraccion fr1 = null;
		Fraccion fr2 = null;
		Fraccion frErr = null;
		try {
			fr1 = new Fraccion(1, 5);
			fr2 = new Fraccion(0, 4);
			frErr = new Fraccion(1, 0);
		} catch (FraccionException e) {
			System.out.println(e);
		}

		try {
			System.out.println(fr1);
			System.out.println(fr2);
			
			System.out.println(fr1.mas(fr2));
			System.out.println(fr1.menos(fr2));
			System.out.println(fr1.por(fr2));
		
			System.out.println(fr1.entre(fr2));
		} catch (FraccionException e) {
			System.out.println(e);
		}
	}
}
