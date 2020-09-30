package museo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;

public class VisitasDia {
	// Aforo maximo permitido por visita
	private final static int AFORO_MAX = 50;
	// Ruta de fichero con horas de visita permitidas
	private final static String FICHERO_HORAS_PERMITIDAS = "tiempos_visita.txt";
	
	private int[] fecha;
	private ArrayList<Visita> visitas;
	
	public VisitasDia(int[] fecha) {
		visitas = new ArrayList<Visita>();
		this.fecha = fecha;
	}
	
	public boolean aniadeVisita(Visita visita) throws IOException {
		if(visita == null)
			return false;
		
		if(comprobarHora(visita.getHora())){
			if(comprobarAforo(visita.getHora(), visita.getCantidad())) {
				visitas.add(visita);
				verVisitas();
				return true;
			}
		}
		return false;
	}
	
	private static boolean comprobarHora(Hora h) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(FICHERO_HORAS_PERMITIDAS));
		String linea = br.readLine();
		
		while(linea != null) {
			
			if(h.equals(stringAHora(linea))) {
				// si la hora de visita esta entre las aceptadas
				br.close();
				return true;
			}
			linea = br.readLine();
		}
		br.close();
		return false;
	}
	
	//comprobar aforo para aniadir nuevas reservas
	private boolean comprobarAforo(Hora h, int aforo) {
		
		int reservas = aforoOcupado(h);
		
		if(reservas + aforo > AFORO_MAX)
			return false;
		
		return true;
	}
	
	// Comprobar aforo para modificar reservas ya existentes
	private boolean comprobarAforo(Hora h, int aforoActual, int nuevoAforo) {
		int reservas = aforoOcupado(h) - aforoActual;
		
		if(reservas + nuevoAforo > AFORO_MAX)
			return false;
		
		return true;
	}
	
	// Transforma strings del archivo de horas permitidas a instancias de Hora
	private static Hora stringAHora(String str) {
		String[] splitted = str.split("\t");
		if(splitted.length != 2)
			return null;
		
		return new Hora(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]));
	}
	
	// Devuelve totalidad de visitas reservadas para una hora
	private int aforoOcupado(Hora h) {
		int reservas = 0;
		
		for (Visita visitaGuardada : visitas) {
			if(visitaGuardada.getHora().equals(h)) {
				reservas += visitaGuardada.getCantidad();
			}
		}
		
		return reservas;
	}
	
	
	public void verVisitas(){
		for (Visita visita : visitas) {
			System.out.println(visita);
		}
		System.out.println();
	}
	
	public void guardarAFichero(String nomFich) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFich));
		oos.writeObject(visitas);
		oos.close();
	}
	
	public int cargarVisitas(String nomFich) throws ClassNotFoundException, IOException {
		File f = new File(nomFich);
		if(!f.exists())
			return -1;
		
		int cantidad = 0;
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		ArrayList<Visita> contenido = (ArrayList<Visita>) ois.readObject();
		for (Visita visita : contenido) {
			if(visita != null) {
				aniadeVisita(visita);
				cantidad++;
			}
		}
		ois.close();
		return cantidad;
	}
	
	static void verFichero(String nomFich) throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(nomFich);
		if(!f.exists())
			return;
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		ArrayList<Visita> contenido = (ArrayList<Visita>) ois.readObject();
		
		for (Visita visita : contenido) {
			if(visita != null) {
				System.out.println(visita);
			}
		}
		ois.close();
	}
	
	boolean actualizaVisita(String nombre) throws IOException {
		// Buscar si existe alguna reserva a nombre de la persona especificada
		boolean encontrado = false;
		Visita actual = null;
		for(int i = 0; i < visitas.size() && !encontrado; i++) {
			actual = visitas.get(i);
			if(actual.getNombre().equalsIgnoreCase(nombre)) {
				encontrado = true;
			}
		}
		
		if (!encontrado)
			return false;
		
		// Menu
		System.out.println("Desea realizar un cambio sobre la hora [1] o sobre el numero de personas [2] o no realizar cambios [0]?");
		int opcion;
		do {
			System.out.print(">>");
			opcion = Consola.leeInt();
		}while(opcion < 0 || opcion > 2);
		
		switch (opcion) {
		// cambio de hora
		case 1:
			System.out.println("Introduce primero la hora y luego los minutos");
			System.out.print("hora = ");
			int h = Consola.leeInt();
			System.out.print("minutos = ");
			int m = Consola.leeInt();
			
			Hora nuevaHora = new Hora(h, m);
			
			if(comprobarHora(nuevaHora) && comprobarAforo(nuevaHora, actual.getCantidad())) {
				actual.setHora(nuevaHora);
				return true;
			}
			return false;
			
		// cambio de cantidad de personas
		case 2:
			System.out.print("introduce la nueva cantidad de visitantes: ");
			int nuevaCantidad = Consola.leeInt();
			
			if(nuevaCantidad < 1 || nuevaCantidad > AFORO_MAX) 
				return false;
			
			if(nuevaCantidad == actual.getCantidad())
				return true;
			
			if(nuevaCantidad < actual.getCantidad() || comprobarAforo(actual.getHora(), actual.getCantidad(), nuevaCantidad)) {
				actual.setCantidad(nuevaCantidad);
				return true;
			}
			
			return false;
		}
		// opcion 0
		return false;
	}
	
	public void crearInforme() throws IOException {
		String dia = fecha[0] < 10 ? "0" + fecha[0] : "" + fecha[0];
		String mes = fecha[1] < 10 ? "0" + fecha[1] : "" + fecha[1];
		String nomFich = "report_" + mes + "_" + dia;
		
		BufferedWriter bw = new BufferedWriter(new FileWriter(nomFich)); 
		for (Visita visita : visitas) {
			bw.write(visita.getNombre() + "\t" + visita.getHora().toString() + "\t" + visita.getCantidad() + " personas\n");
		}
		bw.close();
	}
	
	public HashMap<Hora, Integer> mapaLibres() throws IOException{
		HashMap<Hora, Integer> vuelta = new HashMap<Hora, Integer>();
		
		BufferedReader br = new BufferedReader(new FileReader(FICHERO_HORAS_PERMITIDAS));
		String linea = br.readLine();
		while(linea != null) {
			Hora hora = stringAHora(linea);
			vuelta.put(hora, AFORO_MAX - aforoOcupado(hora));
			
			linea = br.readLine();
		}
		br.close();
		return vuelta;
	}
	
	//solo devuelve una aunque la distancia a dos visitas sea la misma
	public Hora tiempoDeVisitaMasCercana(int h, int m) throws IOException {
		Hora escogida = new Hora(h, m), horaMasCercana = null;
		int diferencia, diferenciaMinima = Integer.MAX_VALUE;
		BufferedReader br = new BufferedReader(new FileReader(FICHERO_HORAS_PERMITIDAS));
	
		String linea = br.readLine();
		while(linea != null) {
			Hora horaEnLineaActual = stringAHora(linea);
			diferencia = Math.abs(escogida.distanciaMinutos(horaEnLineaActual));
			
			if(diferencia < diferenciaMinima) {
				if(aforoOcupado(horaEnLineaActual) < AFORO_MAX) {
					diferenciaMinima = diferencia;
					horaMasCercana = horaEnLineaActual;
				}
			}
			
			linea = br.readLine();
		}
		
		return horaMasCercana;
	}
	
	public int borrarVisitasPasadas() throws IOException {
		int borrados = 0;

		// The method getMinutes() from the type Date is deprecated
		Calendar ahora = Calendar.getInstance();
		int hora = ahora.get(Calendar.HOUR_OF_DAY);
		int minuto = ahora.get(Calendar.MINUTE);
		
		Hora horaActual = new Hora(hora, minuto);
		
		String h = hora < 10 ? "0" + hora : "" + hora;
		String m = minuto < 10 ? "0" + minuto : "" + minuto;
		
		String nomFich = "visitasPasadas_" + h + "_" + m + ".bin";
		
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(nomFich));
		
		Iterator<Visita> it = visitas.iterator();
		while (it.hasNext()) {
			Visita visita = it.next();
			if(visita.getHora().distanciaMinutos(horaActual) < 0) {
				it.remove();
				dos.writeShort(visita.getCantidad());
				borrados++;
			}

		}
		dos.close();
		return borrados;
	}
	
	public static void verVisitasPasadas(String nomFich) throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream(nomFich));
		while(dis.available() > 0) {
			System.out.println(dis.readShort());
		}
		dis.close();
	}
}
