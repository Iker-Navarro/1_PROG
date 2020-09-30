package pack3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestorVuelos {
	
	private Document doc;
	
	public GestorVuelos(String nomFich) {
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
	
	public ArrayList<String> destinosDirectosDesde(String origen){
		ArrayList<String> vuelta = new ArrayList<String>();
		
		List<Element> listaVuelos =  doc.getRootElement().getChildren();
		Iterator<Element> it = listaVuelos.iterator();
		
		while (it.hasNext()) {
			Element element = it.next();
			if(element.getChildText("origen").equalsIgnoreCase(origen)) {
				if(element.getChild("escala") == null)
					vuelta.add(element.getChildText("destino"));
			}
		}
		
		return vuelta;
	}
	
	public void vuelosPosterioresA(String hora) {
		int[] hConsultada = transformarHora(hora);
		if(hConsultada == null) {
			System.out.println("formato de hora incorrecto, debe ser \"hh:mm\"");
			return;
		}
			
		List<Element> listaVuelos =  doc.getRootElement().getChildren();
		Iterator<Element> it = listaVuelos.iterator();
		
		while (it.hasNext()) {
			Element eVuelo = it.next();
			int[] hEncontrada = transformarHora(eVuelo.getChildText("hora"));
			if(hConsultada[0] < hEncontrada[0] || (hConsultada[0] == hEncontrada[0] && hConsultada[1] < hEncontrada[1]))
				verVuelo(eVuelo);
		}
	}
	
	// Transforma String de aspecto "hh:mm" a un array de int de dos posiciones
	// Para poder comparar horas además de comprobar el formato correcto del string
	private int[] transformarHora(String hora) {
		String[] separado = hora.split(":");
		if(separado.length != 2) {
			return null;
		}
		
		try {
			int h = Integer.parseInt(separado[0]);
			int m = Integer.parseInt(separado[1]);
			
			if(h < 0 || h > 23 || m < 0 || m > 59) {
				return null;
			}
			
			return new int[] {h, m};
			
		} catch (NumberFormatException e) {
			System.out.println("Error de formato");
			return null;
		}
	}
	
	private void verVuelo(Element eVuelo) {
		List<Element> elementos = eVuelo.getChildren();
		for (Element element : elementos) {
			System.out.println(element.getName() + " " + element.getText());
		}
		System.out.println();
	}
	
	public void nuevoVuelo(String id, String origen, String destino, int[] hora, boolean escala){
		Element eVuelo = new Element("vuelo").setAttribute("id", id);
		eVuelo.addContent(new Element("origen").setText(origen));
		eVuelo.addContent(new Element("destino").setText(destino));
		eVuelo.addContent(new Element("hora").setText(hora[0] + ":" + hora[1]));
		if(escala)
			eVuelo.addContent(new Element("escala"));
		
		doc.getRootElement().addContent(eVuelo);
		
		guardarXML(doc, "vuelos.xml");
		
	}
	
	public boolean borrarVuelo(String id) {
		
		boolean borrado = false; 
		
		List<Element> vuelos = doc.getRootElement().getChildren();
		Iterator<Element> it = vuelos.iterator();
		while (it.hasNext() && !borrado) {
			Element element = it.next();
			if(element.getAttributeValue("id").equalsIgnoreCase(id)) {
				it.remove();
				borrado = true;
			}
		} 
		
		if(borrado)
			guardarXML(doc, "vuelos.xml");
		
		return borrado;
	}
	
	public boolean vuelosConDestino(String destino) {
		
		Document nuevoDoc = new Document(new Element("vuelos").setAttribute("destino", destino));
		boolean encontrado = false;
		
		List<Element> vuelos = doc.getRootElement().getChildren();
		Iterator<Element> it = vuelos.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			if(element.getChildText("destino").equalsIgnoreCase(destino)) {
				encontrado = true;
				Element clonVuelo = element.clone();
				clonVuelo.removeChild("destino");
				nuevoDoc.getRootElement().addContent(clonVuelo);
			}
		}
		
		if(!encontrado) 
			return false;
		
		guardarXML(nuevoDoc, "vuelos_" + destino + ".xml");
		
		return true;
		
	}
	
	public void crearFicheroCompanias(){
		HashMap<String, Element> companias = new HashMap<String, Element>();
		
		Document nuevoDoc = new Document(new Element("companias"));
		
		List<Element> vuelos = doc.getRootElement().getChildren();
		Iterator<Element> it = vuelos.iterator();
		while (it.hasNext()) {
			Element newElement = it.next().clone();
			
			//Cambio de formato a elemento Vuelo
			newElement.setAttribute("hora", newElement.getChildText("hora"));
			newElement.removeChild("hora");
			newElement.removeChild("escala");
			newElement.removeChild("origen");
			
			String id = newElement.getAttributeValue("id").substring(0, 2);
			
			if(companias.containsKey(id)) {
				companias.get(id).addContent(newElement);
			}
			else {
				companias.put(id, new Element("compania").setAttribute("id", id));
				
				nuevoDoc.getRootElement().addContent(companias.get(id));
				companias.get(id).addContent(newElement);
			}
		}
		guardarXML(nuevoDoc, "companias.xml");
	}
	
	public void cambioEstructura() {
		List<Element> vuelos = doc.getRootElement().getChildren();
		Iterator<Element> it = vuelos.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			element.setAttribute("hora", element.getChildText("hora"));
			element.removeChild("hora");
			element.removeChild("escala");
		}
		
		guardarXML(doc, "vuelos.xml");
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
	
	public static void main(String[] args) throws IOException {
		GestorVuelos gv = new GestorVuelos("vuelos.xml");
		
		for (String string : gv.destinosDirectosDesde("madrid")) {
			System.out.println(string);
		}
		
		System.out.println("--");
		
		gv.vuelosPosterioresA("22:00");
		
		// gv.nuevoVuelo("ib00001", "Nuevo Origen", "Nuevo Destino", new int[] {9, 30}, true);
		
		// gv.borrarVuelo("ib8040");
		
		// gv.vuelosConDestino("Sevilla");
		
		gv.crearFicheroCompanias();
		
		gv.cambioEstructura();
	}
}
