package pack1;

public class Ahorcado {
	private String palabra, mostrado;
	private int intentos = 5;
	
	public Ahorcado(String palabra, int  intentos) {
		this.palabra = palabra;
		mostrado = "";
		
		for(int i = 0; i < palabra.length();i++) {
			mostrado+="_ ";
		}
	}
	
	public String getMostrado() {
		return mostrado;
	}
	
	public void jugar() {
		char intento;
		
		System.out.println("<AHORCADO>");
		System.out.println("-------------");
		
		while(intentos > 0 && mostrado.indexOf("_") != -1) {
			System.out.println("PALABRA: " + mostrado);
			System.out.println("Intentos restantes: " + intentos);
			System.out.print(">>> ");
			intento = Character.toUpperCase(Consola.leeChar());
			
			while((intento < 'a' || intento > 'z') && (intento < 'A' || intento > 'Z')) {
				System.out.println("Introduce una letra: ");
				intento = Character.toUpperCase(Consola.leeChar());
			}
			
			if(palabra.indexOf(intento) == -1) {
				intentos--;
			}
			else {
				actualizarMostrado(intento);
			}
		}
		System.out.println("PALABRA: " + mostrado);
		System.out.println("FINAL");
		System.out.println(mostrado.indexOf("_") != -1 ? "Fracaso\nIntentos maximos superados" : "VICTORIA");
	}
	
	public void actualizarMostrado(char letra) {
		String mostrado2 = "";
		for(int i = 0; i < palabra.length(); i++) {
			mostrado2 += palabra.charAt(i) == letra ? letra + " " : mostrado.charAt(i*2) + " " ;
		}
		mostrado = mostrado2;
	}
	
	
	
}
