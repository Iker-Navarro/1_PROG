package empresa;

public class Programa {
	public static void main(String[] args) {
		
		TelefonoMovil tel1 = new TelefonoMovil("111 111 111", 95);
		Trabajador t1 = new Trabajador("Trabajador 1", "11111111A", 175, 60, 500, tel1);
		
		t1.verDatos();
		
		t1.trabajar();
		t1.trabajar();
		
		System.out.println("----");
		t1.verDatos();
	}
}
