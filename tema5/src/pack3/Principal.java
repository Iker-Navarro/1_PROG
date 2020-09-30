package pack3;

public class Principal {
	public static void main(String[] args) {
		
		Piolin p1 = new Piolin('h', 5, new DatosPersonales("piolin 1", "dueño 1"), 5, 3);
		Piolin p2 = new Piolin('m', 8, new DatosPersonales("piolin 2", "dueño 2"), 6, 2);
		Piolin p3 = new Piolin('m', 12, new DatosPersonales("piolin 3", "dueño 3"), 8, 2);
		Loro l1 = new Loro('h', 7, new DatosPersonales("loro1", "dueñoLoro"), 'n', "verde");
		
		p1.setNombres(new DatosPersonales("NuevoNombre", "NuevoDueño"));
		
		p1.cantar();
		p2.cantar();
		p3.cantar();
		l1.cantar();
		System.out.println("Nombre ave: " + p1.getNombres().getNombreAve() + "\nNombreDueño: " + p1.getNombres().getNombrePersona());
	}
}
