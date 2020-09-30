package ejer5.figuras;

public class Cuadrado extends Figura{
	private static double areaTotal = 0;
	
	public Cuadrado(double l) {
		setPerimetro(4 * l);
		setArea(l * l);
		areaTotal = getAreaTotal() + getArea();
		aumentarAT(getArea());
	}

	public static double getAreaTotal() {
		return areaTotal;
	}
}
