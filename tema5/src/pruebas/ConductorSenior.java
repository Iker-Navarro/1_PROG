package pruebas;

public class ConductorSenior extends Conductor{
	
	private String fechaRenovacion;
	
	public ConductorSenior(String nombre, int edad, String fechaRenovacion){
		super(nombre,edad);
		this.fechaRenovacion = fechaRenovacion;
	}
	
	/*public void ver() {
		super.ver();
		System.out.println("fecha de renovación: " + fechaRenovacion);
	}*/
	public String toString() {
		return super.toString() + " fecha de renovación: " + fechaRenovacion;
	}
}
