package pack1;

public class Punto {
	double x, y;
	
	Punto(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	void ver() {
		System.out.println("(" + x + ", " + y + ")");
	}
}
