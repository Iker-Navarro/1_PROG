package ejer6;

public class Punto {
	private double x, y;
	
	public Punto(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	@Override
	public String toString() {
		return "("+ x + ", " + y +")";
	}
	
	public double distanciaOrigen() {
		return Math.sqrt(x * x + y * y);
	}
	
	public double calcularDistanciaA(Punto other) {
		return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
	}
	
	public int calcularCuadrante() {
		if(x == 0 || y == 0)
			return 0;
		else if(x > 0) {
			if(y > 0)
				return 1;
			return 4;
		}
		else {
			if(y > 0)
				return 2;
			return 3;
		}
	}
	
	public Punto calcularMasCercano(Punto p1, Punto p2, Punto p3) {
		double dp1 = calcularDistanciaA(p1);
		double dp2 = calcularDistanciaA(p2);
		double dp3 = calcularDistanciaA(p3);
		
		if(dp1 < dp2 && dp1 < dp3)
			return p1;
		else if(dp2 < dp3)
			return p2;
		else if(dp3 < dp2)
			return p3;
		return null;
	}
	
}
