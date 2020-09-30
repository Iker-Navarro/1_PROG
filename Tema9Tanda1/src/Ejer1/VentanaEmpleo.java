package Ejer1;

import java.awt.Image;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class VentanaEmpleo extends JFrame{
	
	private static final Ciclo[] CICLOS_COMBO = {new Ciclo("DW", "gs"), new Ciclo("DM", "gs"), new Ciclo("ASIR", "gs"), new Ciclo("SMR", "gm")};
	private static final String[] CHECKS = {"Permiso B1", "Ingles", "Euskera" ,"Experiencia"};
	
	private JTextField txtNombre;
	private JRadioButton radJCompleta;
	private JRadioButton radJMedia;
	private JComboBox<Ciclo> cmbCiclo;
	private JCheckBox[] chkExtras;
	private JButton btnGuardar;
	private JButton btnBorrar;
	
	public VentanaEmpleo() {
		final int bordeDerecho = 300, stdH = 50, granW = 600, saltoLinea = 50;
		int alturaLinea = 20;
		
		setTitle("Instancia Empleo");
		setLayout(null);
		
		//nombre
		txtNombre = new JTextField("Escribe aquí tu nombre");
		txtNombre.setBounds(bordeDerecho, alturaLinea, granW, stdH);
		
		
		alturaLinea += saltoLinea;
		
		//tipo de jornada
		radJMedia = new JRadioButton("Media Jornada");
		radJMedia.setBounds(bordeDerecho, 70, 150, stdH);
		
		radJCompleta = new JRadioButton("Jornada Completa");
		radJCompleta.setBounds(430, alturaLinea, 150, stdH);
		
		ButtonGroup grupoJornada = new ButtonGroup();
		grupoJornada.add(radJCompleta);
		grupoJornada.add(radJMedia);
		
		alturaLinea += saltoLinea;
		
		//Combo Ciclos
		cmbCiclo = new JComboBox<Ciclo>(CICLOS_COMBO);
		cmbCiclo.setBounds(bordeDerecho, alturaLinea, granW, stdH);
		
		alturaLinea += saltoLinea;
		
		// checks
		chkExtras = new JCheckBox[CHECKS.length];
		
		int posX = bordeDerecho;
		for (int i = 0; i < CHECKS.length; i++) {
			chkExtras[i] = new JCheckBox(CHECKS[i]);
			chkExtras[i].setBounds(posX, alturaLinea, 100, stdH);
			chkExtras[i].setSelected(true);
			posX += 100;
		}
		
		alturaLinea += saltoLinea;
		
		// Botones
		btnGuardar = new JButton("GUARDAR");
		btnGuardar.setBounds(bordeDerecho, alturaLinea, 150, stdH);
		
		btnBorrar = new JButton("BORRAR");
		btnBorrar.setEnabled(false);
		btnBorrar.setBounds(500, alturaLinea, 150, stdH);
		
		alturaLinea += saltoLinea;
		
		// Icono Lateral
		JLabel iconoLateral = new JLabel(redim("img/img.png", 200, alturaLinea-20));
		iconoLateral.setBounds(25, 25, 200, alturaLinea-20);
		
		//-------------------------------------------------------
		
		add(txtNombre);
		add(radJMedia);
		add(radJCompleta);
		add(cmbCiclo);
		
		for (int i = 0; i < chkExtras.length; i++) {
			add(chkExtras[i]);
		}
		
		add(btnGuardar);
		add(btnBorrar);
		add(iconoLateral);
		
		//-------------------------------------------------------
		
		setVisible(true);
		setSize(950, 360);
	}
	
	private static ImageIcon redim(String fichImag, int ancho, int alto){
		ImageIcon imIcon=new ImageIcon(fichImag);
		Image im=imIcon.getImage();
		Image im2= im.getScaledInstance(ancho, alto, 0);
		return new ImageIcon(im2);
	}
	
	public static void main(String[] args) {
		VentanaEmpleo ve = new VentanaEmpleo();
	}
}
