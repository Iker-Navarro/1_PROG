package pack4;

public class Test {
	public static void main(String[] args) {
		RelojAnalogico r1 = new RelojAnalogico(new Hora(12, 30 , "PM"), 5 , 4 );
		r1.hora.ver();
		System.out.println("hora:");
		r1.agujaHora().ver();
		System.out.println("minutos:");
		r1.agujaMinutos().ver();
	}
}	
