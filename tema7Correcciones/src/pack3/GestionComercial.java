package pack3;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GestionComercial {
	private String nomFich;

	public GestionComercial(String nomFich) {
		this.nomFich = nomFich;
	}
	
	public void guardaComerciales(ArrayList<Comercial> comerciales) throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(nomFich);
		ArrayList<Comercial> anteriores = new ArrayList<Comercial>();
		if(f.exists()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
			Comercial actual = (Comercial) ois.readObject();
			
			while (actual != null) {
				anteriores.add(actual);
				actual = (Comercial) ois.readObject();
			} 
			ois.close();
		}
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f));
		if(anteriores.size() != 0){
			for (Comercial comercial : anteriores) {
				oos.writeObject(comercial);
			}
		}
		
		for (Comercial comercial : comerciales) {
			oos.writeObject(comercial);
		}
		
		oos.writeObject(null);
		oos.close();
	}
	
	public void verComerciales() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFich));
		Comercial actual = (Comercial) ois.readObject();
		
		while (actual != null) {
			actual.ver();
			actual = (Comercial) ois.readObject();
		} 
		ois.close();
	}
	
	public void verMoviles(String nomFich) throws FileNotFoundException, IOException, ClassNotFoundException {
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFich));
		TelefonoMovil actual = (TelefonoMovil) ois.readObject();
		
		while (actual != null) {
			actual.ver();
			System.out.println();
			actual = (TelefonoMovil) ois.readObject();
		} 
		ois.close();
	}
	
	public Comercial buscaComercial(String nomComer) throws ClassNotFoundException, IOException {
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFich));
		Comercial actual = (Comercial) ois.readObject();
		while(actual != null) {
			if(actual.getNombre().equalsIgnoreCase(nomComer)) {
				ois.close();
				return actual;
			}
			actual = (Comercial) ois.readObject();
		}
		ois.close();
		return null;
	}
	
	public void generaFichMovil(String nomFich) throws FileNotFoundException, IOException, ClassNotFoundException {
		File f = new File(this.nomFich);
		if (!f.exists())
			return;
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomFich));
		
		Comercial actual = (Comercial) ois.readObject();
		while(actual != null) {
			actual.getTlf().cargar(10);
			oos.writeObject(actual.getTlf());
			actual = (Comercial) ois.readObject();
		}
		oos.writeObject(null);
		
		ois.close();
		oos.close();
	}
	
	public void trabajarTodos() throws FileNotFoundException, IOException, ClassNotFoundException {
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nomFich));
		ArrayList<Comercial> actualizados = new ArrayList<Comercial>();
		guardaComerciales(actualizados);
	}
	
	public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, IOException {
		GestionComercial gc1 = new GestionComercial("comerciales.obj");
		ArrayList<Comercial> comerciales = new ArrayList<Comercial>();
		comerciales.add(new Comercial("Com1", 1200, new TelefonoMovil("111 111 111", 20)));
		comerciales.add(new Comercial("Com2", 1000, new TelefonoMovil("222 222 222", 0)));
		comerciales.add(new Comercial("Com3", 2200, new TelefonoMovil("333 333 333", 50)));
		comerciales.add(new Comercial("Com4", 3200, new TelefonoMovil("444 444 444", 19)));
		comerciales.add(new Comercial("Com5", 700, new TelefonoMovil("555 555 555", 25)));
		
		gc1.guardaComerciales(comerciales);
		gc1.verComerciales();
		System.out.println("-------------");
		comerciales.clear();
		comerciales.add(new Comercial("Com6", 7000, new TelefonoMovil("666 666 666", 25)));
		
		gc1.guardaComerciales(comerciales);
		gc1.verComerciales();
		System.out.println("-------------");
		
		Comercial buscado = gc1.buscaComercial("Com1");
		
		if (buscado != null)
			buscado.ver();
		else
			System.out.println("No encontrado");
		
		System.out.println("-------------");
		
		Comercial buscado2 = gc1.buscaComercial("Com11");
		
		if (buscado2 != null)
			buscado2.ver();
		else
			System.out.println("No encontrado");
		
		System.out.println("-------------");
		
		gc1.generaFichMovil("moviles.obj");
		gc1.verMoviles("moviles.obj");
		
		
		System.out.println("-------------");
		
		gc1.trabajarTodos();
		gc1.verComerciales();
		
		File f = new File("comerciales.obj");
		f.delete();
	}
}
