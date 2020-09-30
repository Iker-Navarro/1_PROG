package pack2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class GestorEmpresa {
	private String nomFich;
	final static String[] DEPARTAMENTOS = {"Dep 1", "Dep 2", " Dep 3", " Dep 4", "Dep 5", "Dep 6"};
	final static double[] FACTURACIONES = {223, 229.5, 1234.5, 342.54, 123.43, 1000.00};
	
	public GestorEmpresa(String nomFich) {
		this.nomFich = nomFich;
	}
	
	public void generarXml() {
		if(DEPARTAMENTOS.length != FACTURACIONES.length) {
			System.out.println("Error arrays no son del mismo tamaño");
			return;
		}
		
		Document doc = new Document(new Element("departamentos"));
		
		for (int i = 0; i < DEPARTAMENTOS.length; i++) {
			Element eDepartamento = new Element("departamento").setAttribute("nombre", DEPARTAMENTOS[i]);
			
			eDepartamento.addContent(new Element("facturacion").addContent(""+FACTURACIONES[i]));
			
			doc.getRootElement().addContent(eDepartamento);
		}
		
		checkXML(doc, nomFich);
	}
	
	private static void checkXML(Document doc, String nomFichero) {
		try {
			XMLOutputter XMLOut = new XMLOutputter(Format.getPrettyFormat());
			XMLOut.output(doc, System.out);
			
			System.out.println("¿Quieres crear o sobreescribir el archivo " + nomFichero + " con este contenido? [S/N]");
			Scanner scan = new Scanner(System.in);
			String seleccion = "";
			do {
				seleccion = scan.nextLine();
			}while(!seleccion.equalsIgnoreCase("S") && !seleccion.equalsIgnoreCase("N"));
			
			scan.close();
			
			if(seleccion.equalsIgnoreCase("S")) {
				XMLOut.output(doc, new BufferedWriter(new FileWriter(nomFichero))); 
			}
			 			
		} catch (IOException e) {
			//e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GestorEmpresa ge = new GestorEmpresa("empresa.xml");
		ge.generarXml();
	}
}
