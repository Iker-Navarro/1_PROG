package pack3;

public class Ave {
	private static int numAves = 0;
	
	private char sexo;
	private int edad;
	
	private DatosPersonales nombres;
	
	public Ave(char sexo, int edad, DatosPersonales nombres){
		this.sexo = sexo;
		this.edad = edad;
		this.nombres = nombres;
		numAves++;
	}
	
	public Ave(char sexo, int edad){
		this.sexo = sexo;
		this.edad = edad;
		numAves++;
	}
	
	public void setNombres(DatosPersonales nombres) {
		this.nombres = nombres;
	}
	
	public DatosPersonales getNombres() {
		return nombres;
	}
	/*
	public void setNombres(DatosPersonales nombres) {
		this.nombres.setNombreAve(nombres.getNombreAve());
		this.nombres.setNombrePersona(nombres.getNombrePersona());
	}
	*/
	static int getAves() {
		return numAves;
	}
	
	void quienSoy() {
		System.out.println("Edad: " + edad + "\nSexo: " + sexo);
	}
	
	void cantar() {
		System.out.print("Mi nombre es " + nombres.getNombreAve());
	}
}
