package ejer5.figuras;

public class Circulo extends Figura{
	private static double areaTotal = 0;
	
	public Circulo(double r) {
		setPerimetro(2 * Math.PI * r);
		setArea(Math.PI * r * r);
		areaTotal = getAreaTotal() + getArea();
		aumentarAT(getArea());
	}

	public static double getAreaTotal() {
		return areaTotal;
	}
}
