package pack4;

public class Hora {
	int hora, minutos;
	String am_pm;
	
	Hora(int hora, int minutos, String am_pm){
		this.hora = hora;
		this.minutos = minutos;
		this.am_pm = am_pm;
	}
	
	void actualizar() {
		int mitades = 0;
		if(minutos >= 60) {
			hora += (minutos/60);
			minutos -= (minutos/60) * 60;
		}
		if(hora > 12) {
			mitades = hora/12;
			hora -= (hora/12) * 12;
		}
		if(mitades > 0) {
			String proximo = am_pm == "AM" ? "PM" : "AM";
			am_pm = mitades % 2 == 0 ? am_pm : proximo;
		}
	}
	//TODO actualizar negativos / atrasar
	void actualizar2() {
		int mitades = 0;
		if(minutos < 0) {
			hora -= 1 +(-minutos/60);
			minutos += (minutos/60) * 60;
		}
		if(hora > 12) {
			mitades = hora/12;
			hora -= (hora/12) * 12;
		}
		if(mitades > 0) {
			String proximo = am_pm == "AM" ? "PM" : "AM";
			am_pm = mitades % 2 == 0 ? am_pm : proximo;
		}
	}
	
	
	void adelantar(int cantidad, char tipo) {
		switch(tipo) {
		case 'm':
			minutos += cantidad;
			break;
		case 'h':
			hora += cantidad;
			break;
		}
		actualizar();
	}
	
	void atrasar(int cantidad, char tipo) {
		adelantar(-cantidad, tipo);
	}
	
	void ver() {
		String nHora = hora < 10 ? "0"+ hora : "" + hora ;
		String nMinutos = minutos < 10 ? "0" + minutos : "" + minutos ;
		System.out.println(nHora + ":" + nMinutos + " " + am_pm);
	}
}
