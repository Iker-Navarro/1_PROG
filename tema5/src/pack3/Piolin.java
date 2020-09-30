package pack3;

public class Piolin extends Canario{
	private int numPeliculas;
	
	public Piolin(char sexo, int edad, DatosPersonales nombres, double tamanio, int numPeliculas) {
		super(sexo, edad, nombres, tamanio);
		this.numPeliculas = numPeliculas;
	}
	
	void cantar() {
		super.cantar();
		System.out.println( ", pio-pio soy un piolin." );
	}
	
	/*public static void main(String[] args) {
		Piolin p1 = new Piolin('h', 5, 25, 5);
		Loro l1 = new Loro('m', 6, 'n', "verde");
		
		p1.quienSoy();
		l1.quienSoy();
		
		p1.altura();
		
		l1.deDondeEres();
		
		p1.tamanio = 100;
		p1.altura();
		
		l1.region = 's';
		
		System.out.println("Aves creadas: " + Ave.getAves());
	}*/
}
