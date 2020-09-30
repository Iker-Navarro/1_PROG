package pruebas_JUNIT;

public class Div {
	int x,y;
	
	public Div(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int divide0() {
		if (y == 0)
			throw new java.lang.ArithmeticException("Zero Divide");
		else {
			return x/y;
		}
	}
}
