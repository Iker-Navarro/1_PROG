package vii.museo;

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

/**
 * <h2>Clase: VisitasDia</h2>
 * Clase que almacena y gestiona todas las visitas de un dia concreto
 * @author Iker
 * @version v1
 */

public class VisitasDia {
	/**
	 * Constante con el aforo maximo del museo para un día.
	 */
	final static int AFORO_MAX = 50;
	/**
	 * Constante con el nombre del fichero que almacena las horas de visita permitidas.
	 */
	final static String FICHERO_HORAS_PERMITIDAS = "tiempos_visita.txt";
	
	/**
	 * día que representa cada instancia [día, mes].
	 */
	private int[] fecha;
	/**
	 * lista de visitas que están reservadas para este día.
	 * @see vii.museo.Visita
	 */
	private ArrayList<Visita> visitas;
	
	/**
	 * Constructor
	 * Crea una instancia de VisitasDia con una fecha dada
	 * Ademas inicia el arraylist de reservas vacio
	 * @param fecha fecha de visitas a guardar en objeto creado
	 */
	
	public VisitasDia(int[] fecha) {
		visitas = new ArrayList<Visita>();
		this.fecha = fecha;
	}
	
	/**
	 * Funcion que aniade visitas al arraylist en el caso que:
	 * <ol>
	 * 		<li>Sea una hora de las permitidas por el archivo de horas permitidas</li>
	 * 		<li>El aforo no supere el maximo permitido en esa hora concreta</li>
	 * </ol>
	 * @see vii.museo.VisitasDia#FICHERO_HORAS_PERMITIDAS
	 * @see vii.museo.VisitasDia#AFORO_MAX
	 * @param visita visita a añadir
	 * @return si se ha conseguido añadir la visita o no
	 */
	
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
	
	/**
	 * Metodo privado que comprueba que la hora dada esta entre las permitidas
	 * @param h hora a comprobar
	 * @return si la hora es permitida o no.
	 */
	
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

	/**
	 * Metodo privado que comprueba si existe la posibilidad de aniadir una nueva cantidad de personas en una hora concreta
	 * @param h hora de visita a comprobar
	 * @param aforo cantidad de personas a aniadir (de ser posible)
	 * @return si hay capacidad para el nuevo aforo en esta hora concreta o no.
	 */
	
	private boolean comprobarAforo(Hora h, int aforo) {
		
		int reservas = aforoOcupado(h);
		
		if(reservas + aforo > AFORO_MAX)
			return false;
		
		return true;
	}
	
	/**
	 * Variante para comprobar aforo para modificar reservas ya existentes, no nuevas.
	 * @param h hora de visita a comprobar
	 * @param aforoActual aforo actual de la visita a modificar.
	 * @param nuevoAforo cantidad de personas a aniadir (de ser posible).
	 * @return si hay capacidad para el nuevo aforo en esta hora concreta o no.
	 */
	
	private boolean comprobarAforo(Hora h, int aforoActual, int nuevoAforo) {
		int reservas = aforoOcupado(h) - aforoActual;
		
		if(reservas + nuevoAforo > AFORO_MAX)
			return false;
		
		return true;
	}
	/**
	 * Transforma strings del archivo de horas permitidas a instancias de Hora
	 * @param str cadena a transformar
	 * @return <ul>
	 * <li>instancia de clase Hora generado a partir del String</li>
	 * <li>null en el caso de no poder transformarlo</li>
	 * </ul>				
	 */

	private static Hora stringAHora(String str) {
		String[] splitted = str.split("\t");
		if(splitted.length != 2)
			return null;
		
		return new Hora(Integer.parseInt(splitted[0]), Integer.parseInt(splitted[1]));
	}
	
	/**
	 * Metodo para obtener la cantidad de personas que acudiran a una visita
	 * @param h hora de la visita
	 * @return cantidad de aforo ocupado
	 */

	private int aforoOcupado(Hora h) {
		int reservas = 0;
		
		for (Visita visitaGuardada : visitas) {
			if(visitaGuardada.getHora().equals(h)) {
				reservas += visitaGuardada.getCantidad();
			}
		}
		
		return reservas;
	}
	
	/**
	 * Metodo para visualizar ArrayList de visitas por consola.
	 */
	
	public void verVisitas(){
		for (Visita visita : visitas) {
			System.out.println(visita);
		}
		System.out.println();
	}
	
	/**
	 * Metodo que guarda el ArrayList completo de visitas en un archivo
	 * @param nomFich nombre del fichero donde se guardaran
	 */
	
	public void guardarAFichero(String nomFich) throws FileNotFoundException, IOException {
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFich));
		oos.writeObject(visitas);
		oos.close();
	}
	
	/**
	 * Metodo que carga desde un fichero las visitas guardadas al ArrayList de visitas
	 * @param nomFich fichero origen
	 * @return cantidad de visitas cargadas desde el fichero
	 */
	
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
	
	/**
	 * Metodo para ver el contenido de un fichero que almacene un ArrayList de Visitas
	 * @param nomFich fichero a visualizar
	 * @throws FileNotFoundException
	 */
	
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
	
	/**
	 * Metodo para actualizar una visita ya almacenada.
	 * Proceso:
	 * <ol>
	 * 	<li>Comprobar que existe alguna reserva a nombre de la persona dada</li>
	 * 	<li>Solicitar a usuario que clase de cambio se desea hacer</li>
	 * 	<li>Realizar cambio solicitado realizando comprobaciones pertinentes con metodos privados de clase</li>
	 * </ol>
	 * @param nombre solicitante de la reserva
	 * @return si se ha realizado algun cambio o no.
	 */
	
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
			
			if(comprobarHora(nuevaHora)) {
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
	
	/**
	 * Metodo para generar un informe con todas las visitas de un dia en concreto.
	 */
	
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
	
	/**
	 * Metodo para generar un HashMap con las horas del fichero de horas permitidas donde aun pueden hacerse reservas
	 * @see vii.museo.VisitasDia#FICHERO_HORAS_PERMITIDAS
	 * @return HashMap con dicha informacion [Hora (Key), cantidad libre (Value)]
	 */
	
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
	
	/**
	 * Metodo que devuelve la hora permitida más cercana a la hora dada.
	 * @see vii.museo.VisitasDia#FICHERO_HORAS_PERMITIDAS
	 * nota: solo devuelve una Hora aunque la distancia a dos visitas sea la misma
	 * @param h hora
	 * @param m minuto
	 * @return Hora mas cercana
	 */

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
	
	/**
	 * Metodo que elimina del ArrayList de visitas las visitas que ya han ocurrido (teniendo en cuenta la hora actual)
	 * Además almacena la cantidad de integrantes de la visita en un archivo.
	 * @return cantidad de visitas borradas
	 */
	
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
	
	/**
	 * Visualiza un archivo de shorts por consola.
	 * @param nomFich nombre del fichero a visualizar.
	 */
	
	public static void verVisitasPasadas(String nomFich) throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream(nomFich));
		while(dis.available() > 0) {
			System.out.println(dis.readShort());
		}
		dis.close();
	}
}
