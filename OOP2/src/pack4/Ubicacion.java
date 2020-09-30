package pack4;

public class Ubicacion {
	double x, y;
	
	Ubicacion(double x, double y){
		this.x = x;
		this.y = y;
	}
	
	void ver(){
		System.out.println("(" + x + ", " + y + ")");
	}
}
