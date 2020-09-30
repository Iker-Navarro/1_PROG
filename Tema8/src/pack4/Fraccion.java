package pack4;

public class Fraccion {
	private int numerador, denominador;

	public Fraccion(int numerador, int denominador) throws FraccionException {
		if(denominador == 0)
			throw new FraccionException("No se puede instanciar fracción con denom 0");
		this.numerador = numerador;
		this.denominador = denominador;
		this.simplificar();
	}

	@Override
	public String toString() {
		return numerador + "/" + denominador;
	}
	
	public double respuesta() {
		return (double) numerador/denominador;
	}
	
	private void simplificar() {
		if(numerador == 0) {
			return;
		}
			
		int mcd = mcd(numerador, denominador);
		numerador /= mcd;
		denominador /= mcd;
		
	}
	
	private int mcd(int a, int b) {
		int aux;
		
		aux = a % b;
		while(aux != 0) {
			a = b;
			b = aux;
			aux = a % b;
		}
		return b;
	}
	
	public Fraccion mas(Fraccion other) throws FraccionException{		
		int respNumer, respDenom;
		
		if(this.denominador != other.denominador) {
			respDenom = this.denominador * other.denominador;
			respNumer = this.numerador * other.denominador + other.numerador * this.denominador;
		}
		else {
			respDenom = this.denominador;
			respNumer = this.numerador + other.numerador;
		}
		
		return new Fraccion(respNumer, respDenom);
	}
	
	public Fraccion menos(Fraccion other) throws FraccionException{
		int respNumer, respDenom;
		
		if(this.denominador != other.denominador) {
			respDenom = this.denominador * other.denominador;
			respNumer = this.numerador * other.denominador - other.numerador * this.denominador;
		}
		else {
			respDenom = this.denominador;
			respNumer = this.numerador - other.numerador;
		}
	
		return new Fraccion(respNumer, respDenom);
	}
	
	public Fraccion por(Fraccion other) throws FraccionException {
		
		int respNumer, respDenom;

		respNumer = this.numerador*other.numerador;
		respDenom = this.denominador*other.denominador;
		
		return new Fraccion(respNumer, respDenom);
	}
	
	public Fraccion entre(Fraccion other) throws FraccionException{
		if(other.numerador == 0)
			throw new FraccionException("El resultado de esta operación es una fracción inválida");
		
		int respNumer, respDenom;

		respNumer = this.numerador*other.denominador;
		respDenom = this.denominador*other.numerador;
		
		return new Fraccion(respNumer, respDenom);
	}
}
