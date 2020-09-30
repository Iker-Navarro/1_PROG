package empresa;

public class Programa2 {
	public static void main(String[] args) {
		Empresa e1 = new Empresa("Empresa 1", 550000, new Trabajador("Duenio", "11111111A", 180, 70, 12000, new TelefonoMovil("111 111 111", 100)));
		
		System.out.println(e1);
		
		e1.contratarGerente(new Trabajador("Gerente", "22222222B", 160, 55, 1300, new TelefonoMovil("222 222 222", 75)));
		
		System.out.println(e1);
		
		e1.trabajar();
		e1.trabajar();
		
		System.out.println(e1);
	}
}
