package ejer3;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Fecha {
	SimpleDateFormat formato;
	Date hoy;
	
	public Fecha() {
		hoy = new Date();
	}
	
	public String devuelveFecha(int tipo) {
		String cad = "";
		switch (tipo) {
		case 1:
			formato = new SimpleDateFormat("yyyy/MM");
			cad = formato.format(hoy);
			break;
		case 2:
			formato = new SimpleDateFormat("MM/yyyy");
			cad = formato.format(hoy);
			break;
		case 3:
			formato = new SimpleDateFormat("MM/yy");
			cad = formato.format(hoy);
			break;
		default:
			cad = "ERROR";
			break;
		}
		return cad;
	}
}
