package ejer6;

public class Cuadrado extends Figura{
	private Punto v1, v2, v3, v4;
	
	public Cuadrado(String nombre, Punto diagV1, Punto diagV3) {
		super(nombre);
		
		if(diagV1.getX() < diagV3.getX()) {
			v1 = diagV1;
			v3 = diagV3;
		}
		else {
			v1 = diagV3;
			v3 = diagV1;
		}
		
		calculaOtraDiagonal();
	}
	
	private void calculaOtraDiagonal() {
		double diagonal = v1.calcularDistanciaA(v3);
		double lado = diagonal/Math.sqrt(2); // lado del cuadrado.
		
		double alfa = Math.abs(Math.asin((v3.getY()-v1.getY())/diagonal)); // angulo de la diagonal
		double alfafinal = alfa + Math.PI/4; // + 45 grados
		
		double distanciaY = Math.sin(alfafinal) * lado;
		double distanciaX = Math.cos(alfafinal) * lado;
		
		if(v1.getY() < v3.getY()) {
			v2 = new Punto(v1.getX() + distanciaX, v1.getY() + distanciaY);
			v4 = new Punto(v3.getX() - distanciaX, v3.getY() - distanciaY);
		}
		else {
			v2 = new Punto(v1.getX() + distanciaX, v1.getY() - distanciaY);
			v4 = new Punto(v3.getX() - distanciaX, v3.getY() + distanciaY);
		}
		
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "CUADRADO " + getNombre() + "\nVERTICES:\n" + "v1 " + v1 + "\nv2 " + v2 + "\nv3 " + v3 + "\nv4 " + v4;
	}
	
	@Override
	public double calcularArea() {
		return Math.pow(v1.calcularDistanciaA(v2), 2);
	}

	@Override
	public boolean esRegular() {
		return true;
	}
}
