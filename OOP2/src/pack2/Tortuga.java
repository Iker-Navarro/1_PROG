package pack2;

public class Tortuga {
	static final double PI = 3.14159;
	
	static int tortugasAtleticas = 0;
	
	double x, y, angulo, distanciaTotal;
	boolean esAtletica;

	Tortuga(double x, double y, double angulo){
		this.x = x;
		this.y = y;
		this.angulo = angulo;
		distanciaTotal = 0;
		esAtletica = false;
		
		modAngulo();
	}
	
	Tortuga(double x, double y){
		this.x = x;
		this.y = y;
		angulo = 0;
		distanciaTotal = 0;
		esAtletica = false;
	}
	
	Tortuga(double angulo){
		this.angulo = angulo;
		x = 0;
		y = 0;
		distanciaTotal = 0;
		esAtletica = false;
		
		modAngulo();
	}
	
	void modAngulo() {
		if(angulo >= 360) {
			this.angulo -= ((int) this.angulo/360)*360;
		}
		else if(angulo < 0) {
			this.angulo += ((int) -this.angulo / 360)*360;
			this.angulo +=360;
		}
	}
	
	void mirarDerecha() {
		angulo = 0;
	}
	
	void mirarArriba() {
		angulo = 90;
	}
	
	void mirarIzquierda() {
		angulo = 180;
	}
	
	void mirarAbajo() {
		angulo = 270;
	}
	
	void girarIzquierda(double grados) {
		angulo += grados;
		modAngulo();
	}
	
	void girarDerecha(double grados) {
		angulo -= grados;
		modAngulo();
	}
	
	void avanzar(double distancia) {
		double rad = angulo * (PI/180);
		
		y += Math.sin(rad) * distancia;
		x += Math.cos(rad) * distancia;
		
		distanciaTotal += Math.abs(distancia);
		if (distanciaTotal > 300 && !esAtletica) {
			tortugasAtleticas++;
			esAtletica = true;
		}
	}
	
	void retroceder(double distancia) {
		avanzar(-distancia);
	}
	
	void verPos() {
		System.out.println("(" + x + ", " + y + ")");
	}
}
