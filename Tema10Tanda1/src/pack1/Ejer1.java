package pack1;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

public class Ejer1 {
	
	public static void main(String[] args){
		if(args.length == 0)
			System.out.println("Se requiere un parametro numerico para la main");
		else {
			try {
				int numero = Integer.parseInt(args[0]);
				
				Document doc = new Document(new Element("tabla").setAttribute("num", "" + numero));
				
				for (int i = 1; i <= 9; i++) {
					//doc.getRootElement().addContent(new Element("factor").setAttribute("f", "" + i).addContent(new Element("resultado").addContent(""+(numero*i))));
					Element eFactor = new Element("factor");
					eFactor.setAttribute("f", "" + i);
					
					eFactor.addContent(new Element("resultado").addContent(""+(numero*i)));
					
					doc.getRootElement().addContent(eFactor);
					
				}
				
				checkXML(doc, "multiplicacion.xml");
				System.out.println("fin");
				
			} catch (NumberFormatException e) {
				System.out.println("Se requiere un parametro numerico para la main");
			}
			
		}
		
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
	
}
