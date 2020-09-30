package pack1;

public class Punto {
	private double x,y;

	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double distanciaAOrigen() {
		return Math.sqrt(x*x+y*y);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
}
