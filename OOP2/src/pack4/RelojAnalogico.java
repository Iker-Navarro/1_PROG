package pack4;

public class RelojAnalogico {
	Hora hora;
	double longitudM, longitudH;
	
	public RelojAnalogico(Hora hora, double longitudM, double longitudH) {
		this.hora = hora;
		this.longitudH = longitudH;
		this.longitudM = longitudM;
	}
	
	Ubicacion agujaHora() {
		double grados = (hora.hora * 30) - 90;
		grados *= 3.14159/180;
		
		double x = Math.cos(grados) * longitudH;
		double y = Math.sin(grados) * longitudH * -1;
		
		return new Ubicacion(x, y);
	}
	
	Ubicacion agujaMinutos() {
		double grados = (hora.minutos * 6) - 90;
		grados *= 3.14159/180;
		
		double x = Math.cos(grados) * longitudM;
		double y = Math.sin(grados) * longitudM * -1;
		
		return new Ubicacion(x, y);
	}
	
}
