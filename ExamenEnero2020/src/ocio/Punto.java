package ocio;

public class Punto {
	private double x, y;
	
	public Punto(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public boolean esIgualA(Punto other) {
		if(this.x == other.x && this.y == other.y) {
			return true;
		}
		return false;
	}
	
	public double distanciaA(Punto other) {
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}
	
	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
}
