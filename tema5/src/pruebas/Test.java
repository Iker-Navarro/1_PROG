package pruebas;

public class Test {
	public static void main(String[] args) {
		ConductorSenior cs1 = new ConductorSenior("nombre1", 24, "14/10/2019");
		Vehiculo v1 = new Vehiculo("Marca1", 25, cs1, 15);
		
		System.out.println(cs1);
		System.out.println(v1.esPeligroso() ? "Es peligroso" : "No es peligroso");
		
		
		if(v1.getKms() > 300000 && v1.getMarca().equals("Ford")) {
			v1.setKms(100000);
		}
		
		System.out.println(v1);
		
		if(v1.getConductor().getEdad() < 25) 
			v1.setKms(v1.getKms()*2);
		
		System.out.println(v1);
		
	}
}
