package pack2;

public class Loro extends Ave{
	char region;
	String color;
	
	Loro(char sexo, int edad, char region, String color){
		super(sexo, edad);
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
}
