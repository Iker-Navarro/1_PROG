package ejer3;

public class FuncionMatem {
	private double x;
	
	public FuncionMatem(int x) {
		this.x = x;
	}
	
	public void evaluar() {
		evaluarX(x);
	}
	
	public static void evaluarX(double x) {
		System.out.println(Math.sqrt(Math.sin(Math.pow(x, 3) + 2*x/9) + Math.cos(6*Math.PI + Math.tan(Math.pow(Math.E, Math.pow(x, 3))))));
	}
}
