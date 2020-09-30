package pack1;

import java.io.IOException;

public class DividePorCero {
	public static void main(String[] args) {
		int x, y, res;
		System.out.println("x / y = ?");
		System.out.print("x: ");
		try {
			x = Consola.leeInt();
			System.out.print("y: ");
			y = Consola.leeInt();
			res = x/y;
			System.out.println(x + "/" + y + "=" + res);
		} catch (ArithmeticException e) {
			System.out.println("Error: division por 0");
		} catch (NumberFormatException e) {
			System.out.println("Error: formato numerico incorrecto");
		} catch (IOException e) {
			System.out.println("Error: entror de IO");
		}	
	}
}
