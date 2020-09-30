package mytest;

public class Division {
	private Double x, y;
	
	public Division(Double x, Double y) {
		this.x = x;
		this.y = y;
	}

	public double division(){
		if(y == null || x == null)
			throw new java.lang.IllegalArgumentException("No puede ser NULL");
		/*if(y == 0)
			throw new java.lang.ArithmeticException("Error de division entre 0");*/
		return x/y;
	}
}
