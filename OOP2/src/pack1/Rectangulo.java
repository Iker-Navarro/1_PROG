package pack1;

public class Rectangulo {
	Punto posicion;
	double alto, ancho;
	
	Rectangulo(Punto posicion, double alto, double ancho) {
		this.posicion = posicion;
		this.alto = alto;
		this.ancho = ancho;
	}
	
	Rectangulo(double alto, double ancho){
		this.alto = alto;
		this.ancho = ancho;
		posicion = new Punto(0,0);
	}
	
	Rectangulo(double posX, double posY, double alto, double ancho){
		posicion = new Punto(posX, posY);
		this.ancho = ancho;
		this.alto = alto;
	}
	
	double area(){
		return alto * ancho;
	}
	
	boolean esCuadrado() {
		boolean respuesta = false;
		if(alto == ancho)
			respuesta = true;
		return respuesta;
	}	
	
	void visualizar() {
		System.out.println("Base: " + ancho);
		System.out.println("Altura: " + alto);
		System.out.println("Area: " + area());
		System.out.print("Posicion esquina superior izquierda: ");
		posicion.ver();
		
	}
}
