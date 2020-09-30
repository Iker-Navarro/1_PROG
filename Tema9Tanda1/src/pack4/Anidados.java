package pack4;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Anidados extends JFrame{
	
	private JTextField[] textsCentro;
	private JButton btnCentralNorte;
	private JButton btnInferior;
	private JTextArea txtAreaCentroCentro;
	
	public Anidados() {
		
		setTitle("Paneles anidados en BoxLayout");
		BoxLayout bl = new BoxLayout(getContentPane(), BoxLayout.Y_AXIS);
		setLayout(bl);
		
		btnInferior = new JButton("OK");
		
		//-------------------------------------------------------
		
		add(crearPanelSuperior());
		add(Box.createRigidArea(new Dimension(0, 15)));
		add(crearPanelCentral());
		add(Box.createRigidArea(new Dimension(0, 15)));
		add(btnInferior);
		
		//-------------------------------------------------------		
		
		setSize(550, 400);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private JPanel crearPanelSuperior() {
		JPanel pnlSuperior = new JPanel();
		
		pnlSuperior.setLayout(new BorderLayout());
		pnlSuperior.setBorder(BorderFactory.createTitledBorder("BorderLayout"));
		
		// PanelSuperior / Zona Oeste

		JPanel pnlSuperiorOeste = new JPanel();
		JLabel[] labelsCentro = new JLabel[3];
		
		pnlSuperiorOeste.setLayout(new GridLayout(labelsCentro.length, 1));
		pnlSuperiorOeste.setBorder(BorderFactory.createTitledBorder("GridLayout"));
		pnlSuperiorOeste.setPreferredSize(new Dimension(150, -1));
		
		for (int i = 0; i < labelsCentro.length; i++) {
			labelsCentro[i] = new JLabel("Label " + (i+1));
			pnlSuperiorOeste.add(labelsCentro[i]);
		}
		
		// PanelSuperior / Zona Centro
		
		JPanel pnlSuperiorCentro = new JPanel();
		
		pnlSuperiorCentro.setLayout(new GridLayout(labelsCentro.length, 1));
		pnlSuperiorCentro.setBorder(BorderFactory.createTitledBorder("GridLayout"));
		
		textsCentro = new JTextField[3];
		for (int i = 0; i < textsCentro.length; i++) {
			textsCentro[i] = new JTextField();
			pnlSuperiorCentro.add(textsCentro[i]);
		}
		
		//-------------------------------------------------------
		
		pnlSuperior.add(pnlSuperiorOeste, BorderLayout.WEST);
		pnlSuperior.add(pnlSuperiorCentro, BorderLayout.CENTER);
		
		//-------------------------------------------------------
		
		return pnlSuperior;
	}
	
	private JPanel crearPanelCentral() {

		JPanel pnlCentral = new JPanel();
		pnlCentral.setLayout(new BorderLayout());
		pnlCentral.setBorder(BorderFactory.createTitledBorder("BorderLayout"));
		
		// PanelCentral / Zona Norte

		JPanel pnlCentralNorte = new JPanel();
		pnlCentralNorte.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlCentralNorte.setBorder(BorderFactory.createTitledBorder("FlowLayout"));
		
		btnCentralNorte = new JButton("Click Me");
		pnlCentralNorte.add(btnCentralNorte);
		
		// PanelCentral / Zona Centro
		txtAreaCentroCentro = new JTextArea("TEXTAREA..........");
		
		
		
		//-------------------------------------------------------
		
		pnlCentral.add(pnlCentralNorte, BorderLayout.NORTH);
		pnlCentral.add(txtAreaCentroCentro, BorderLayout.CENTER);
		
		//-------------------------------------------------------
		
		return pnlCentral;
	}
	
	public static void main(String[] args) {
		Anidados a = new Anidados();
	}
}
