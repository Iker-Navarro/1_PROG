package pack3;

public class Canario extends Ave{
	private double tamanio;
	
	Canario(char sexo, DatosPersonales nombres, int edad){
		super(sexo, edad, nombres);
	}
	
	Canario(char sexo, int edad, DatosPersonales nombres, double tamanio){
		super(sexo, edad, nombres);
		this.tamanio = tamanio;
	}
	
	void altura() {
		 if (tamanio > 30)
			 System.out.println("Alto");
		 else if (tamanio >= 15 )
			 System.out.println("Mediano");
		 else
			 System.out.println("Bajo");
	}
	
}
