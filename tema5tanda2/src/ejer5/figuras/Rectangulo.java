package ejer5.figuras;

public class Rectangulo extends Figura{
	private static double areaTotal = 0;
	
	public Rectangulo(double l1, double l2) {
		setPerimetro(l1 * 2 + l2 * 2);
		setArea(l1 * l2);
		areaTotal = getAreaTotal() + getArea();
		aumentarAT(getArea());
	}

	public static double getAreaTotal() {
		return areaTotal;
	}
}
