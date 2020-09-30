package pack2;

public class Canario extends Ave{
	double tamanio;
	
	Canario(char sexo, int edad){
		super(sexo, edad);
	}
	
	Canario(char sexo, int edad, double tamanio){
		super(sexo, edad);
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
