package pack2;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Ahorcado {
	private static final String PALABRA_INICIAL = "CRISTALERA";
	
	private Document doc;
	
	private String palabra;
	private String respuesta;
	private int vidasTotales;
	private int vidasRestantes;

	public Ahorcado() {
		instanciarDoc();
		palabra = PALABRA_INICIAL;
		inizializarRestoVariables();
	}
	
	public Ahorcado(int cantLetras) throws IllegalArgumentException{
		instanciarDoc();
		
		List<Element> palabras = listaGrupoPalabras(cantLetras);	
		if(palabras == null || palabras.size() == 0)
			throw new IllegalArgumentException();
		else
			palabra = palabras.get(aleatorio(0, palabras.size())).getValue().toUpperCase();	
		
		inizializarRestoVariables();
	}

	private void instanciarDoc() {
		SAXBuilder builder = new SAXBuilder();
		try {
			doc = builder.build(new File("palabras.xml"));
		} catch (JDOMException | IOException e) {
			//e.printStackTrace();
		}
	}
	
	private void inizializarRestoVariables() {
		respuesta = "";
		for(int i = 0; i < palabra.length(); i++) {
			respuesta += "-";
		}
		
		vidasTotales = palabra.length()/2;
		vidasRestantes = vidasTotales;
	}
	
	public boolean tirar(char car) {
		
		String nuevaRespuesta = "";
		
		for (int i = 0; i < palabra.length(); i++) {
			if(palabra.charAt(i) == car) {
				nuevaRespuesta += "" + car;
			}
			else {
				nuevaRespuesta += respuesta.charAt(i);
			}
		}
		
		if(nuevaRespuesta.equalsIgnoreCase(respuesta)){
			vidasRestantes = vidasRestantes - 1;
			return false;
		}
		else {
			respuesta = nuevaRespuesta;
			return true;
		}
		
	}
	
	public void actualizarAciertosEnXML() {
		List<Element> palabras = listaGrupoPalabras(palabra.length());
		
		if(palabras == null || palabras.size() == 0) {
			// No hay palabras de este tamaño en el XML, puede ocurrir si la palabra acertada es el valor por defecto PALABRA_INICIAL (y esta no está en el XML)
			// Se podria añadir al fichero xml o como en este caso no hacer nada
		}
		else {
			for (Element element : palabras) {
				if(element.getText().equalsIgnoreCase(palabra)) {
					try {
						int aciertosPrevios = Integer.parseInt(element.getAttributeValue("aciertos"));
						element.setAttribute("aciertos", "" + (aciertosPrevios + 1));
					}catch(NumberFormatException e){
						element.setAttribute("aciertos", "1");
					}
					guardarXML(doc, "palabras.xml");
					return;
				}
			}
			// Si se llega a este punto la palabra por defecto PALABRA_INICIAL es de un grupo de palabras que existe, pero dicha palabra no esta en el XML
		}
		
	}
	
	public String respuestaToBigString() {
		String vuelta = "";
		for (int i = 0; i < respuesta.length(); i++) {
			vuelta += respuesta.charAt(i) + " ";
		}
		return vuelta.toUpperCase();
	}
	
	public boolean completo() {
		return respuesta.equalsIgnoreCase(palabra) ? true : false;
	}
	
	private List<Element> listaGrupoPalabras(int cantLetras){	
		// lista con grupos de palabras de cantLetras cantidad de letras
		List<Element> grupos = doc.getRootElement().getChildren();
		Iterator<Element> it = grupos.iterator();
		while (it.hasNext()) {
			Element grupo = it.next();
			// buscar el grupo que coincida con el dado
			if(Integer.parseInt(grupo.getAttributeValue("numletras")) == cantLetras) {
				return grupo.getChildren();
			}	
		}
		return null;
	}
	
	private static void guardarXML(Document doc, String nomFichero) {
		try {
			XMLOutputter XMLOut = new XMLOutputter(Format.getPrettyFormat());
			XMLOut.output(doc, new BufferedWriter(new FileWriter(nomFichero)));
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	private static int aleatorio(int min, int max) {
		return (int) ((Math.random()*(max - min))+min);
	}
	
	
	public String getPalabra() {
		return palabra;
	}
	
	public int getVidasRestantes() {
		return vidasRestantes;
	}
	
	public int getVidasTotales() {
		return vidasTotales;
	}
}
