package pack2;

public class Ave {
	static int numAves = 0;
	
	char sexo;
	int edad;
	
	Ave(char sexo, int edad){
		this.sexo = sexo;
		this.edad = edad;
		numAves++;
	}
	
	static int cantAves() {
		return numAves;
	}
	
	void quienSoy() {
		System.out.println("Edad: " + edad + "\nSexo: " + sexo);
	}
}
