package pack3;

public class Loro extends Ave{
	private char region;
	private String color;
	
	Loro(char sexo, int edad, DatosPersonales nombres, char region, String color){
		super(sexo, edad, nombres);
		this.region = region;
		this.color = color;
	}
	
	void deDondeEres(){
		switch(region) {
		case 'N':
		case 'n':
			System.out.println("NORTE");
			break;
		case 'S':
		case 's':
			System.out.println("SUR");
			break;
		case 'E':
		case 'e':
			System.out.println("ESTE");
			break;
		case 'O':
		case 'o':
			System.out.println("OESTE");
			break;
		}
	}
	
	void cantar() {
		super.cantar();
		System.out.println(", pio-pio loro bonito." );
	}
}
