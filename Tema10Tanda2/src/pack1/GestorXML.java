package pack1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

public class GestorXML {
	private static final String[] MEDICOS = {"Dr Saez", "Dra Artea", "Dr Cabeza", "Dra Kholn"};
	
	private String nomFich;
	private Document doc;
	
	public GestorXML(boolean resetear, File f) {
		nomFich = f.getName();
		if(resetear)
			generarNuevoXML(f);
		else
			recuperarXML(f);
	}
	
	private void recuperarXML(File f) {
		SAXBuilder builder = new SAXBuilder();
		
		try {
			doc = builder.build(f);
		} catch (JDOMException e) {
			System.out.println("Error con el documento xml");
		} catch (IOException e) {
			System.out.println("Error de entrada de datos");
		}
	}

	public void generarNuevoXML(File f) {
		doc = new Document(new Element("consultas"));
		
		for (String nombre : MEDICOS) {
			Element eMedico = new Element("medico").setAttribute("nombre", nombre);
			doc.getRootElement().addContent(eMedico);
		}
		
		XMLOutputter out = new XMLOutputter(Format.getPrettyFormat());
		try {
			out.output(doc, new BufferedWriter(new FileWriter(nomFich)));
		} catch (IOException e) {
			// Error de salida de datos
		}
	}
	
	public String[] todosMedicos() {
		List<Element> medicos = doc.getRootElement().getChildren();
		
		String[] vuelta = new String[medicos.size()];
		
		int i = 0;
		Iterator<Element> it = medicos.iterator();
		while (it.hasNext()) {
			Element element = it.next();
			vuelta[i] = element.getAttributeValue("nombre");
			i++;
		}
		
		return vuelta;
	}

	public boolean comprobarDisponibilidad(String medico, HoraConsulta hora) {
		Element med = buscarMedico(medico);
		
		List<Element> consultas = med.getChildren();
		Iterator<Element> it = consultas.iterator();
		
		while (it.hasNext()) {
			Element cons = it.next();
			if(new HoraConsulta(cons.getChildText("hora")).equals(hora))
				return false;
		}
		
		return true;
	}
	
	private Element buscarMedico(String nombre) {
		List<Element> medicos = doc.getRootElement().getChildren();
		
		Iterator<Element> it = medicos.iterator();
		while (it.hasNext()){
			Element med = it.next();
			if(med.getAttributeValue("nombre").equals(nombre)) {
				return med;
			}
		}
		return null;
	}
	
	public void aniadirConsulta(String medico, HoraConsulta hora, String paciente) {
		Element nuevoElemento = obtenerElementoConsulta(hora, paciente);
		buscarMedico(medico).addContent(nuevoElemento);
		guardarXML(doc, nomFich);
	}
	
	private Element obtenerElementoConsulta(HoraConsulta hora, String paciente) {
		Element vuelta = new Element("consulta");
		
		vuelta.addContent(new Element("hora").setText(hora.toString()));
		vuelta.addContent(new Element("paciente").setText(paciente));
		
		return vuelta;
	}
	
	private void guardarXML(Document doc, String nomFichero) {
		try {
			XMLOutputter XMLOut = new XMLOutputter(Format.getPrettyFormat());
			XMLOut.output(doc, new BufferedWriter(new FileWriter(nomFichero)));
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
}
