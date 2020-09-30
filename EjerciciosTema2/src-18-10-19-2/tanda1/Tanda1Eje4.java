package tanda1;

public class Tanda1Eje4 {

	public static void main(String[] args) {
		final int DCTO = 20;
		float compra;
		
		System.out.print("Valor de la compra: ");
		compra = Consola.leeInt();
		
		if (compra > 100) {
			compra -= compra*(DCTO/100.0);
		}
		System.out.print(compra);
	}

}
