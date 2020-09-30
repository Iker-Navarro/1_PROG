package pack3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestorXML {
	private static final String FICHERO_MENU = "menu.xml", FICHERO_PEDIDOS = "pedidos.xml";
	
	private Document docMenu;
	private Document docPedidos;
	
	public GestorXML() {
		SAXBuilder builder = new SAXBuilder();
		
		// Generar el document del menú - si este no existe el programa no puede continuar
		try {
			docMenu = builder.build(new File(FICHERO_MENU)); 
		} catch (JDOMException | IOException e) {
			System.out.println("Error en archivo o formato del XML en archivo de MENU");
			return;
		}
		
		// Generar el document  de los pedidos, si no existe se crea la raiz y el grupo de pedidos para el día de hoy.
		try {
			docPedidos = builder.build(new File(FICHERO_PEDIDOS));
		} catch (IOException e) {
			// si hay problemas con el fichero lo creamos de nuevo
			docPedidos = new Document(new Element("raiz"));
		} catch (JDOMException e) {
			System.out.println("Error en formato de XML de archivo de PEDIDOS");
		}
	}
	
	public Plato[] damePlatos(String tipo) {
		List<Element> platos = docMenu.getRootElement().getChildren();
		ArrayList<Plato> seleccionados = new ArrayList<Plato>();
		for (Element plato : platos) {
			if(plato.getAttributeValue("tipo").equalsIgnoreCase(tipo)) 
				seleccionados.add(new Plato(plato.getAttributeValue("nombre"), Double.parseDouble(plato.getChildText("precio")), plato.getChildText("imagen")));
		}
		
		Plato[] vuelta = new Plato[seleccionados.size()];
		
		for(int i = 0; i < seleccionados.size(); i++) {
			vuelta[i] = seleccionados.get(i);
		}
		
		return vuelta;
	}
	
	public void guardarPedido(Plato primero, Plato principal, Plato[] extras){
		Element pedidosDia = obtenerPedidosDeHoy();
		
		if(pedidosDia == null) {
			pedidosDia = new Element("pedidos").setAttribute("fecha", obtenerFechaActual());
			docPedidos.getRootElement().addContent(pedidosDia);
		}
		
		Element nuevoPedido = new Element("pedido");
		pedidosDia.addContent(nuevoPedido);
		
		nuevoPedido.addContent(new Element("primero").setText(primero.getNombre()));
		
		nuevoPedido.addContent(new Element("principal").setText(principal.getNombre()));
		
		if(extras.length != 0) {
			Element etiquetaExtras = new Element("adicionales");
			nuevoPedido.addContent(etiquetaExtras);
			for (Plato plato : extras) {
				etiquetaExtras.addContent(new Element("adicional").setText(plato.getNombre()));
			}
			
		}
		
		guardarXML(docPedidos, FICHERO_PEDIDOS);		
	}
	
	private Element obtenerPedidosDeHoy() {
		List<Element> diasPedidos = docPedidos.getRootElement().getChildren();
		Iterator<Element> it = diasPedidos.iterator();
		while (it.hasNext()) {
			Element pedidosDia = it.next();
			if(pedidosDia.getAttributeValue("fecha").equals(obtenerFechaActual()))
				return pedidosDia;
		}
		
		return null;
	}
	
	private static String obtenerFechaActual() {
		GregorianCalendar calendar = new GregorianCalendar();
		
		int dia = calendar.get(GregorianCalendar.DAY_OF_MONTH);
		int mes= calendar.get(GregorianCalendar.MONTH);
		
		// +1 al mes ya que empiezan en 0
		return dia + "-" + (mes + 1);
	}
	
	private static void guardarXML(Document doc, String nomFichero) {
		try {
			XMLOutputter XMLOut = new XMLOutputter(Format.getPrettyFormat());
			XMLOut.output(doc, new BufferedWriter(new FileWriter(nomFichero)));
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
}
