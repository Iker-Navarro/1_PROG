package pack3;

public class DatosPersonales {
	private String nombreAve, nombrePersona;
	
	public DatosPersonales(String nombreAve, String nombrePersona) {
		this.nombreAve = nombreAve;
		this.nombrePersona = nombrePersona;
	}
	
	public String getNombreAve(){
		return nombreAve;
	}
	
	public String getNombrePersona(){
		return nombrePersona;
	}
	
	public void setNombreAve(String nombreAve){
		this.nombreAve = nombreAve;
	}
	
	public void setNombrePersona(String nombrePersona){
		this.nombrePersona = nombrePersona;
	}
}
