package pack4;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestionMenu {
	private String nomFich;
	private Document doc;
	
	public GestionMenu(String nomFich) {
		this.nomFich = nomFich;
		
		parsear();
	}
	
	private void parsear() {
		File f = new File(nomFich);
		
		SAXBuilder builder = new SAXBuilder();
		
		try {
			doc = builder.build(f);
		} catch (JDOMException e) {
			System.out.println("Error con el documento xml");
		} catch (IOException e) {
			System.out.println("Error de entrada de datos");
		}
	}
	
	public void ver() {
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		try {
			out.output(doc, System.out);
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	public Plato consultaPlato(String nombre) {
		Plato consultado = null;
		
		List<Element> platos = doc.getRootElement().getChildren();
		Iterator<Element> it = platos.iterator();
		
		while (it.hasNext() && consultado == null) {
			Element element = it.next();
			if(element.getChildText("nombre").equalsIgnoreCase(nombre)) 
				consultado = creaPlatoDeXML(element);
		}
		
		return consultado;
	}
	
	public ArrayList<Plato> platosPorCalorias(int limInf,int limSup){
		ArrayList<Plato> vuelta = new ArrayList<Plato>();
		
		if(limInf > limSup) {
			int aux = limInf;
			limInf = limSup;
			limSup = aux;
		}
		
		List<Element> platos = doc.getRootElement().getChildren();
		Iterator<Element> it = platos.iterator();
		
		while (it.hasNext()) {
			Element element = it.next();
			
			Double cal;
			try {
				cal = Double.parseDouble(element.getChildText("calorias"));
				
				if(cal >= limInf && cal <= limSup)
					vuelta.add(creaPlatoDeXML(element));
				
			} catch (NumberFormatException e) {
				// Error de contenido de XML, ignorar plato
			}
		}
		
		return vuelta;
	}
	
	public void nuevaComida(Plato plato) {
		doc.getRootElement().addContent(crearElementoXML(plato));
		guardarXML(doc, nomFich);
	}
	
	public void eliminaPlatoMasCaro() {
		List<Element> platos = doc.getRootElement().getChildren();
		ArrayList<Element> elementosSeleccionados = new ArrayList<Element>();
		double maxPrecio = -1.0;
		
		// guardamos el indice de el/los elemento/s que tengan el precio más caro.
		for (int i = 0; i < platos.size(); i++) {
			try {
				double precio = Double.parseDouble(platos.get(i).getChildText("precio"));
				if(precio > maxPrecio) {
					elementosSeleccionados.clear();
					elementosSeleccionados.add(platos.get(i));
					
					maxPrecio = precio;
				}
				else if(precio == maxPrecio) {
					elementosSeleccionados.add(platos.get(i));
				}
				
			} catch (NumberFormatException e) {
				// Error de contenido de XML
			}
		}
		
		// Eliminamos los elementos en las posiciones guardadas
		
		for (int i = 0; i < elementosSeleccionados.size(); i++) {
			doc.getRootElement().removeContent(elementosSeleccionados.get(i));
		}
		
		guardarXML(doc, nomFich);
	}
	
	public HashMap<Double, HashSet<Plato>> mapaPlatosPrecio(){
		HashMap<Double, HashSet<Plato>> vuelta = new HashMap<Double, HashSet<Plato>>();
		
		List<Element> platos = doc.getRootElement().getChildren();
		Iterator<Element> it = platos.iterator();
		
		while (it.hasNext()) {
			Element element = it.next();
			Double precio = null;
			try {
				precio = Double.parseDouble(element.getChildText("precio"));
			} catch (NumberFormatException e) {
				// si el precio es incorrecto (no numerico) lo añadimos como categoria null
			}
			
			if(vuelta.containsKey(precio)) {
				vuelta.get(precio).add(creaPlatoDeXML(element));
			}else {
				vuelta.put(precio, new HashSet<Plato>());
				vuelta.get(precio).add(creaPlatoDeXML(element));
			}
		}
		
		return vuelta;
	}
	
	private static void guardarXML(Document doc, String nomFichero) {
		try {
			XMLOutputter XMLOut = new XMLOutputter(Format.getPrettyFormat());
			XMLOut.output(doc, System.out);

			int seleccion = JOptionPane.showConfirmDialog(null, "¿Quieres crear o sobreescribir el archivo " + nomFichero + " con el nuevo contenido?", "Aviso", JOptionPane.YES_NO_OPTION);
			if(seleccion == JOptionPane.YES_OPTION) {
				XMLOut.output(doc, new BufferedWriter(new FileWriter(nomFichero))); 
			}
			 			
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	public static Element crearElementoXML(Plato plato) {
		Element vuelta = new Element("plato");
		
		vuelta.addContent(new Element("nombre").setText(plato.getNombre()));
		vuelta.addContent(new Element("precio").setText(""+plato.getPrecio()));
		vuelta.addContent(new Element("descripcion").setText(plato.getDescripcion()));
		vuelta.addContent(new Element("calorias").setText(""+plato.getCalorias()));
		
		return vuelta;
	}
	
	public static Plato creaPlatoDeXML(Element element) {
		Plato vuelta = null;
		
		Double pre, cal;
		
		try {
			pre = Double.parseDouble(element.getChildText("precio"));
			cal = Double.parseDouble(element.getChildText("calorias"));
			vuelta = new Plato(
					element.getChildText("nombre"),
					element.getChildText("descripcion"),
					pre,
					cal);
		}catch (NumberFormatException e) {
			// Formato incorrecto para precio o calorias		
		}
		 
		
		return vuelta;
	}
	
	public static void main(String[] args) {
		GestionMenu gm = new GestionMenu("menu.xml");
		gm.ver();
		Plato consultado = gm.consultaPlato("new2");
		if(consultado != null) {
			System.out.println(consultado);
			System.out.println("--.--.--");
		}
	
		for (Plato plato : gm.platosPorCalorias(700, 520)) {
			System.out.println(plato);
			System.out.println("----");
		}
		
		//gm.nuevaComida(new Plato("Aniadido", "Esto se aniade con el metodo nuevaComida", 10.0, 1200.0));
		//gm.nuevaComida(new Plato("Aniadido 2", "Esto tambien se aniade con el metodo nuevaComida", 1000000.0, 1200.0));
		
		gm.eliminaPlatoMasCaro();
		
		HashMap<Double, HashSet<Plato>> precioPlatos = gm.mapaPlatosPrecio();
		for (Double precio : precioPlatos.keySet()) {
			System.out.println("||||||||||||||||" + precio + "||||||||||||||||");
			for (Plato plato : precioPlatos.get(precio)) {
				System.out.println(plato);
			}
			System.out.println("--");
		}
		
	}
}
