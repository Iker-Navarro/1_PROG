package empresa;

public class TelefonoMovil {
	private String numero;
	private int bateria;
	
	public TelefonoMovil (String numero, int bateria) {
		this.numero = numero;
		this.bateria = bateria;
	}
	
	public int getBateria() {
		return bateria;
	}
	
	public String getNumero() {
		return numero;
	}
	
	void usar() {
		bateria -= 1;
	}
}
