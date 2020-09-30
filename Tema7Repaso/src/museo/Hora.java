package museo;

import java.io.Serializable;

public class Hora implements Serializable{
	private int horas, minutos;

	public Hora(int horas, int minutos) {
		this.horas = horas;
		this.minutos = minutos;
	}
	
	@Override
	public String toString() {
		String h = horas < 10 ? "0" + horas : "" + horas;
		String m = minutos < 10 ? "0" + minutos : "" + minutos;
		return h + ":" + m;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hora other = (Hora) obj;
		if (horas != other.horas)
			return false;
		if (minutos != other.minutos)
			return false;
		return true;
	}
	
	public int distanciaMinutos(Hora other) {
		int thisMinutos = this.horas * 60 + this.minutos;
		int otherMinutos = other.horas * 60 + other.minutos;
		//negativo si el que llama al metodo es menor que "other"
		return thisMinutos-otherMinutos;
	}
}
