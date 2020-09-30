package pruebas;

public class Vehiculo {
	private String marca;
	private int kms;
	private Conductor conductor;
	private int anios;
	
	public Vehiculo(String marca, int kms, Conductor conductor, int anios) {
		this.marca = marca;
		this.kms = kms;
		this.conductor = conductor;
		this.anios = anios;
	}
	
	public int getKms() {
		return kms;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setKms(int kms) {
		this.kms = kms;
	}
	
	public Conductor getConductor() {
		return conductor;
	}
	
	public String toString() {
		return marca + ", " + kms + "kms, de " + anios + " anios, cuyo conductor es " + conductor.toString();
	}
	
	public boolean esPeligroso() {
		if(this.anios > 20 || conductor.getEdad() > 80 || conductor instanceof ConductorSenior) {
			return true;
		}
		return false;
	}
}
