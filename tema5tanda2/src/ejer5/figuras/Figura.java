package ejer5.figuras;

public abstract class Figura {
	private static double areaTotal = 0;
	
	private double area, perimetro;
	
	public double getArea() {
		return area;
	}

	public void setArea(double area) {
		this.area = area;
	}
	
	public double getPerimetro() {
		return perimetro;
	}

	public void setPerimetro(double perimetro) {
		this.perimetro = perimetro;
	}
	
	public static void aumentarAT(double cantidad) {
		areaTotal = getAreaTotal() + cantidad;
	}

	public static double getAreaTotal() {
		return areaTotal;
	}
}
