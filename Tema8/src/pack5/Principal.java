package pack5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;

public class Principal {
	
	public static Comercial pedirDatosComercial() {
		System.out.print("Nombre del comercial: ");
		String nom = Consola.leeString();
		
		System.out.print("Salario: ");
		double salario = Consola.leeDouble();
		
		System.out.print("tiene telefono? [si/no]");
		String opcion;
		do {
			opcion = Consola.leeString();
		}while(!opcion.equalsIgnoreCase("SI") && !opcion.equalsIgnoreCase("NO"));
		
		if(opcion.equalsIgnoreCase("SI")) {
			System.out.print("Numero de telefono: ");
			String num = Consola.leeString();
			
			System.out.println("Saldo: ");
			double saldo = Consola.leeDouble();
			
			return new Comercial(nom, salario, new TelefonoMovil(num, saldo));
		}
		else
			return new Comercial(nom, salario);
		
	}
	
	public static boolean evaluaComercial(Comercial c) throws ErrorComercialException {
		if(c.getSalario() < 0)
			throw new ErrorComercialException("Salario negativo");
		if(c.getTlf()==null)
			throw new ErrorComercialException("Sin telefono asignado");
		try {
			Long.parseLong(c.getTlf().getNumero());
		}catch (NumberFormatException e) {
			throw new ErrorComercialException("Carácter invalido en número de teléfono");
		}
		
		return true;
	}
	
	public static void grabarComerciales(String nomFich){
		String opcion;
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFich));
			
			do {
				Comercial actual = pedirDatosComercial();
				try {
					if(evaluaComercial(actual))
						oos.writeObject(actual);
				} catch (ErrorComercialException e) {
					System.out.println("Comercial " + actual + " no añadido a archivo \n" + e);
				}
				
				System.out.println("Continuar introduciendo comerciales? [SI/NO]");
				opcion = Consola.leeString();
			}while(opcion.equalsIgnoreCase("SI"));		
			oos.writeObject(null);
			oos.close();
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		} catch (IOException e) {
			System.out.println("Error de Entrada/Salida de datos");
		}
	}
	
	public static HashMap<String, TelefonoMovil> generarMapaMoviles(ArrayList<Comercial> comerciales){
		HashMap<String, TelefonoMovil> hm = new HashMap<String, TelefonoMovil>();
		
		for (Comercial comercial : comerciales) {
			hm.put(comercial.getNombre(), comercial.getTlf());
		}
		
		return hm;
	}
	
	public static void buscaMovil (HashMap<String, TelefonoMovil> hm) throws Exception{
		System.out.print("Nombre del comercial: ");
		String nombre = Consola.leeString();
		if(!hm.containsKey(nombre))
			throw new Exception("Comercial no existente");
		
		System.out.println(hm.get(nombre));
	}
	
	public static void main(String[] args) {
		System.out.print("Nombre del fichero de comerciales: ");
		String nomFich = Consola.leeString();
		grabarComerciales(nomFich);
		
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFich));
			
			ArrayList<Comercial> comercialesUnicos = new ArrayList<Comercial>();
			
			Comercial actual = (Comercial) ois.readObject();
			while(actual != null) {
				if(!comercialesUnicos.contains(actual))
					comercialesUnicos.add(actual);
				actual = (Comercial) ois.readObject();
			}
			
			ois.close();
			
			for (Comercial comercial : comercialesUnicos) {
				System.out.println(comercial);
			}
			
			HashMap<String, TelefonoMovil> mapaMoviles = generarMapaMoviles(comercialesUnicos);
			
			
			buscaMovil(mapaMoviles);	
			
		} catch (FileNotFoundException e) {
			System.out.println("Archivo no encontrado");
		} catch (IOException e) {
			System.out.println("Error de Entrada/Salida");
		} catch (ClassNotFoundException e) {
			System.out.println("Clase no encontrada");
		} catch (Exception e){
			System.out.println(e);
		}
		
		
	}
}
