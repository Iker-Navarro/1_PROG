package pack3;

public class SumaNumeros {
	public static void main(String[] args) {
		if(args.length == 0) {
			System.out.println("\"args\" está vacio.");
			return;
		}
		int resultado = 0, incorrectos = 0;
		for (int i = 0; i < args.length; i++) {
			try {
				int actual = Integer.parseInt(args[i]);
				resultado+=actual;
			} catch (NumberFormatException e) {
				incorrectos++;
			}
		}
		
		System.out.println("Suma de todos los valores adecuados = " + resultado);
		System.out.println(incorrectos + " valores omitidos.");
	}
}
