package empresa;

public class Empresa {
	
	private String nombre;
	private double beneficios;
	
	private Trabajador duenio, gerente;
	
	public Empresa(String nombre, double beneficios, Trabajador duenio) {
		this.nombre = nombre;
		this.beneficios = beneficios;
		this.duenio = duenio;
	}
	
	void contratarGerente(Trabajador gerente) {
		this.gerente = gerente;
	}
	
	void trabajar() {
		duenio.trabajar();
		if (gerente != null) {
			gerente.trabajar();
		}
		beneficios+=100;
	}
	
	@Override
	public String toString() {
		
		return gerente == null ?
				nombre + ", " + beneficios + "� , due�o: " + duenio.toString() + ", sin gerente" :
				nombre + ", " + beneficios + "� , due�o: " + duenio.toString() + ", Gerente: " + gerente.toString();
				
	}
}
