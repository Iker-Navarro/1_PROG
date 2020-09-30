package pr;

public class ClasePrueba {
	
	private Double x,y;

	public ClasePrueba(Double x, Double y) {
		this.x = x;
		this.y = y;
	}
	
	public double division(int tipo) {
		if(x == null || y == null)
			throw new java.lang.IllegalArgumentException("Ni x ni Y pueden ser \"null\"");
		switch (tipo) {
		case 1:
			if(y == 0)
				throw new java.lang.ArithmeticException("Division por 0 (y)");
			return x/y;
		case 2:
			if(x == 0)
				throw new java.lang.ArithmeticException("Division por 0 (x)");
			return y/x;
		default:
			throw new java.lang.IllegalArgumentException("valor fuera de rango");
		}
	}
}
