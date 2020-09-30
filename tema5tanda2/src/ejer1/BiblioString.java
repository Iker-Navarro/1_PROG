package ejer1;

public class BiblioString {
	
	static String concatenarInverso() {
		String newStr = "";
		int cont = 1;
		
		System.out.println("introduce strings [\"fin\" para salir]:");
		System.out.print("String numero " + cont + ": ");
		String str = Consola.leeString();
		
		
		while(!str.equals("fin") && cont < 10) {
			newStr = str + newStr;
			
			cont++;
			System.out.print("String numero " + cont + ": ");
			str = Consola.leeString();
		}
		
		return newStr;
	}
	
	static int sustituir(String str, char antiguo, char nuevo) {
		String newStr = "";
		int ocurrencias = 0;
		
		for(int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == antiguo) {
				newStr += nuevo;
				ocurrencias++;
			}
			else 
				newStr+=str.charAt(i);
		}
		System.out.println(newStr);
		return ocurrencias;
	}
	
	static String eliminarCar(String str, char elimina) {
		String newStr = "";
		
		for(int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != elimina) 
				newStr += str.charAt(i);
		}
		
		return newStr;
	}
	
	static String completar$(String str, int longitud) {
		String newStr = str;
		
		if(longitud > str.length()) {
			for(int i = 0; i < longitud-str.length(); i++) {
				newStr += '$';
			}
		}
		
		return newStr;
	}
	
	static void verPalabras(String str) {
		str = str.toUpperCase();
		for(int i = 0; i < str.length(); i++) {
			System.out.print(str.charAt(i) == ' ' ? "\n" : str.charAt(i));
		}	
	}
	
	static void verPalabrasAlReves(String str) {
		String str2 = str.toLowerCase();
		str2 = str2.trim();
		while(str2.lastIndexOf(' ') != -1) {
			System.out.println(str2.substring(str2.lastIndexOf(' ')).trim());
			str2 = str2.substring(0, str2.lastIndexOf(' ')).trim();
		}
		System.out.println(str2);
	}
	
}
