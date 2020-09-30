package pack3;

public class Fraccion {
	int numerador, denominador;
	
	Fraccion(int numerador, int denominador){
		this.numerador = numerador;
		this.denominador =  denominador;
	}
	
	double respuesta() {
		return (double) numerador/denominador;
	}
	
	void simplificar() {
		int mcd = mcd(numerador, denominador);
		numerador /= mcd;
		denominador /= mcd;
		
	}
	
	int mcd(int a, int b) {
		int aux;
		
		aux = a % b;
		while(aux != 0) {
			a = b;
			b = aux;
			aux = a % b;
		}
		return b;
	}
	
	void sumale(Fraccion other){
		int nume = other.numerador;
		if(this.denominador != other.denominador) {
			nume *= this.denominador;
			this.numerador *= other.denominador; 
			this.denominador *= other.denominador; 
		}
		this.numerador += nume;
		simplificar();
	}
	
	void restale(Fraccion other) {
		int nume = other.numerador;
		if(this.denominador != other.denominador) {
			nume *= this.denominador;
			this.numerador *= other.denominador; 
			this.denominador *= other.denominador; 
		}
		this.numerador -= nume;
		simplificar();
	}
	
	void multiplica(Fraccion other) {
		this.numerador*=other.numerador;
		this .denominador*=other.denominador;
		simplificar();
	}
	
	void divide(Fraccion other) {
		this.numerador*=other.denominador;
		this.denominador*=other.numerador;
		simplificar();
	}
	
	void ver() {
		System.out.println(numerador + "/" + denominador);
	}
	
}
