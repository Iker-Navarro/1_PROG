package pack2;

public class TransformaStrings {
	public static String palabrasMayusculas(String str) {
		String vuelta = "";
		boolean siguienteMayus = true;
		for (int i = 0; i < str.length(); i++) {
			char caracter = str.charAt(i);
			if(siguienteMayus){
				if (caracter >= 'A' && caracter <= 'Z')
					siguienteMayus = false;
				else if(caracter >= 'a' && caracter <= 'z') {
					caracter = (char)(caracter - 32);
					siguienteMayus = false;
				}
			}
			else if(caracter == ' ') 
				 siguienteMayus = true;
			else {
				if (caracter >= 'A' && caracter <= 'Z')
					caracter = (char)(caracter + 32);
			}
			
			vuelta += caracter;
		}
		
		return vuelta;
	}
	
	public static String frasesMayusculas(String str) {
		String vuelta = "";
		boolean siguienteMayus = true;
		for (int i = 0; i < str.length(); i++) {
			char caracter = str.charAt(i);
			if(siguienteMayus){
				if (caracter >= 'A' && caracter <= 'Z')
					siguienteMayus = false;
				else if(caracter >= 'a' && caracter <= 'z') {
					caracter = (char)(caracter - 32);
					siguienteMayus = false;
				}
			}
			else if(caracter == '.') 
				 siguienteMayus = true;
			else {
				if (caracter >= 'A' && caracter <= 'Z')
					caracter = (char)(caracter + 32);
			}
			
			vuelta += caracter;
		}
		
		return vuelta;
	}
	
	public static String todoMayusculas(String str) {
		String vuelta = "";
		
		for (int i = 0; i < str.length(); i++) {
			char caracter = str.charAt(i);
			if(caracter >= 'a' && caracter <= 'z') {
				caracter = (char)(caracter - 32);
			}
			vuelta += caracter;
		}
		
		return vuelta;
	}
	
	public static String invertirString(String str) {
		String vuelta = "";
		
		for (int i = str.length()-1; i >= 0; i--) {
			vuelta += str.charAt(i);
		}
		
		return vuelta;
	}
}
