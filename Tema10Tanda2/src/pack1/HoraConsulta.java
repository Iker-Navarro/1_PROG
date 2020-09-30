package pack1;

public class HoraConsulta {
	private int h, m;

	public HoraConsulta(int h, int m) {
		this.h = h;
		this.m = m;
	}
	
	public HoraConsulta(String hora) {
		String[] separado = hora.split(":");

		h = Integer.parseInt(separado[0]);
		m = Integer.parseInt(separado[1]);
	}
	
	@Override
	public String toString() {
		String m2 = m < 10 ? "0" + m : ""+m;
		return  h + ":" + m2;
	}
	
	public static HoraConsulta[] consultasEntre(HoraConsulta inicio, HoraConsulta fin, int salto) {
		
		int diferencia = inicio.distanciaA(fin);
		int cantidad = diferencia/salto + 1;
		
		HoraConsulta[] vuelta = new HoraConsulta[cantidad];
		HoraConsulta actual = inicio;
		for (int i = 0; i < vuelta.length; i++) {
			vuelta[i] = actual;
			actual = actual.horaAdelantada(salto);
		}
		
		return vuelta;
	}
	
	private int distanciaA(HoraConsulta other) {
		return Math.abs((this.h * 60 + this.m) - (other.h * 60 + other.m));
	}
	
	//
	// Si no instancio una hora por cada posible cita tendria que guardar un String con la hora e ir transformándolo
	// a HoraConsulta cada vez que se requiera. O no se me ocurre otra manera mejor.
	//
	private HoraConsulta horaAdelantada(int minutos) {
		int nuevaM, nuevaH;
		nuevaM = m + minutos;
		nuevaH = h + nuevaM/60;
		nuevaM %= 60;
		return new HoraConsulta(nuevaH, nuevaM);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoraConsulta other = (HoraConsulta) obj;
		if (h != other.h)
			return false;
		if (m != other.m)
			return false;
		return true;
		
	}
}
