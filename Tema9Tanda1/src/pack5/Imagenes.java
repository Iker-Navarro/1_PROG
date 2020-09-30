package pack5;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class Imagenes extends JFrame{
	
	private String inputRuta;
	private JComboBox<String> cmbImagenes;
	private JTextField txtOpinion;
	private JButton btnGuardar;
	
	public Imagenes() {
		inputRuta = JOptionPane.showInputDialog("Ruta de las imágenes?");
		
		if(inputRuta == null) 
			return;
		
		File f = new File(inputRuta);
		if(!f.exists() || !f.isDirectory()) 
			return;
		
		String[] contenido = f.list();
		ArrayList<String> contenidoValido = new ArrayList<String>();
		
		for (String archivo : contenido) {
			if(isImage(archivo, inputRuta))
				contenidoValido.add(inputRuta + "/" + archivo);
		}
		
		if (contenidoValido.size() == 0) 
			return;
		
		String[] contenidoModeloCombo = new String[contenidoValido.size()];
		for(int i = 0; i < contenidoValido.size(); i++)
			contenidoModeloCombo[i] = contenidoValido.get(i);
		// La ruta dada es de una carpeta valida que contiene al menos una imágen
		
		setLayout(new BorderLayout());
		
		//Zona Oeste
		JPanel pnlOeste = new JPanel();
		
		JPanel subPnlOeste = new JPanel(new BorderLayout());
		
		JPanel pnlOesteTop = new JPanel(new GridLayout(2, 2, 5, 5));
		
		JLabel lblCmbImagenes = new JLabel("Imagen seleccionada");
		
		cmbImagenes = new JComboBox<String>(contenidoModeloCombo);
		
		pnlOesteTop.add(lblCmbImagenes);
		pnlOesteTop.add(cmbImagenes);		
		
		
		JLabel lblTxtOpinion = new JLabel("Escribe tu opinión");
		txtOpinion = new JTextField();
		
		pnlOesteTop.add(lblTxtOpinion);
		pnlOesteTop.add(txtOpinion);
		
		
		JPanel pnlOesteBot = new JPanel();
		pnlOesteBot.setLayout(new FlowLayout(FlowLayout.CENTER));
		btnGuardar = new JButton("GUARDAR");
		pnlOesteBot.add(btnGuardar);
		
		subPnlOeste.add(pnlOesteTop, BorderLayout.CENTER);
		subPnlOeste.add(pnlOesteBot, BorderLayout.SOUTH);	
		
		pnlOeste.add(subPnlOeste);
		// Zona central
		
		JPanel pnlCentro = new JPanel();
		pnlCentro.setLayout(new BorderLayout());
		
		JPanel pnlCentroGrid = new JPanel();
		pnlCentroGrid.setLayout(new GridLayout(0,3));
		
		for (String ruta : contenidoValido) {
			pnlCentroGrid.add(new JLabel(redim(ruta, 200, 100)));
		}
		
		pnlCentro.add(new JLabel("CONOCES ESTOS LUGARES?"), BorderLayout.NORTH);
		pnlCentro.add(pnlCentroGrid, BorderLayout.CENTER);
		//-------------------------------------------------------
		
		add(pnlOeste, BorderLayout.WEST);
		add(pnlCentro, BorderLayout.CENTER);
		
		//-------------------------------------------------------
		
		
		pack();
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}
	
	private boolean isImage(String archivo, String ruta) {
		final String[] EXTENSIONES_PERMITIDAS = {".jpg", ".png"};
		
		File f = new File(ruta + "/" + archivo);
		
		if(!f.isFile())
			return false;
		
		for (String extension : EXTENSIONES_PERMITIDAS) {
			if(archivo.endsWith(extension))
				return true;
		}
		
		return false;
	}
	
	private static ImageIcon redim(String fichImag, int ancho, int alto){
		ImageIcon imIcon=new ImageIcon(fichImag);
		Image im=imIcon.getImage();
		Image im2= im.getScaledInstance(ancho, alto, 0);
		return new ImageIcon(im2);
	}
	
	public static void main(String[] args) {
		Imagenes i = new Imagenes();
	}
}
