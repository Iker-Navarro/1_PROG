package ejer6;

public class Triangulo extends Figura{
	private Punto v1, v2, v3;
	
	public Triangulo(String nombre, Punto v1, Punto v2, Punto v3) {
		super(nombre);
		this.v1 = v1;
		this.v2 = v2;
		this.v3 = v3;
	}
	
	public void mostrarLongitudLados() {
		System.out.println(v1.calcularDistanciaA(v2));
		System.out.println(v2.calcularDistanciaA(v3));
		System.out.println(v3.calcularDistanciaA(v1));
	}

	@Override
	public double calcularArea() {
		return Math.abs((v1.getX()*(v2.getY()-v3.getY()) + v2.getX()*(v3.getY()-v1.getY()) + v3.getX()*(v1.getY()-v2.getY()))/2);
	}

	@Override
	public boolean esRegular() {
		double dist1 = v1.calcularDistanciaA(v2);
		if(dist1 == v2.calcularDistanciaA(v3) && dist1 == v3.calcularDistanciaA(v1))
			return true;
		return false;
	}

	@Override
	public String toString() {
		String regular = esRegular() ? "REGULAR" : "NO REGULAR";
		return "TRIANGULO " + getNombre() + " " + regular + " : VERTICES " + v1 + ", " + v2 + ", " + v3;
	}
}
