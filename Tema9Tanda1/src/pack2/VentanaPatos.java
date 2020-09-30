package pack2;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;


public class VentanaPatos extends JFrame{

	private static final Pato[] PATOS = {new Pato("Donald", 80), new Pato("Lucas", 50), new Pato("Roger", 180), new Pato("Daisy", 70)};
	private static final String FICHERO_ALIMENTOS = "comidas.txt";
	
	private JList<Pato> lstPatos;
	private JButton btnGuardar;
	private JCheckBox[] alimentos;
	
	public VentanaPatos() {
		
		setTitle("ALIMENTA TUS PATOS");
		setLayout(null);
		
		// Lista de Patos
		lstPatos = new JList<Pato>(PATOS);
		lstPatos.setBackground(Color.PINK);
		lstPatos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		JScrollPane scrollPatos = new JScrollPane(lstPatos, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPatos.setBounds(50, 25, 175, 150);
		
		// Boton guardar
		btnGuardar = new JButton("Guardar");
		btnGuardar.setBounds(50, 200, 175, 25);
		
		// Checks alimentos
		JPanel panelDerecho = new JPanel();
		panelDerecho.setLayout(null);
		panelDerecho.setBounds(250, 25, 300, 300);

		String[] strAlimentos = alimentos(FICHERO_ALIMENTOS);
		
		if(strAlimentos != null) {
			alimentos = new JCheckBox[strAlimentos.length];
			
			for (int i = 0, altura = 0; i < strAlimentos.length; i++, altura+=25) {
				alimentos[i] = new JCheckBox(strAlimentos[i]);
				alimentos[i].setBounds(0, altura, 150, 25);
			}
			
			//añadir en caso de poder
			for (int i = 0; i < alimentos.length; i++) {
				panelDerecho.add(alimentos[i]);
			}
		} 
		else {
			JLabel lblError = new JLabel("  Ha ocurrido un error con el archivo de alimentos");
			lblError.setBounds(0, 150, 300, 50);
			lblError.setBackground(Color.RED);
			lblError.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
			lblError.setOpaque(true);
			panelDerecho.add(lblError);
		}
		
		//-------------------------------------------------------
		
		add(scrollPatos);
		add(btnGuardar);
		add(panelDerecho);
		
		//-------------------------------------------------------
		

		setSize(700, 400);
		setVisible(true);
	}
	
	private String[] alimentos(String nomFich)  {
		try {
			File f = new File(nomFich);
			if(!f.exists())
				throw new IOException();
			
			ArrayList<String> alimentos = new ArrayList<String>();
			BufferedReader br = new BufferedReader(new FileReader(f));
			
			String linea = br.readLine();
			while(linea!=null) {
				alimentos.add(linea);
				linea = br.readLine();
			}
			br.close();
			
			String[] vuelta = new String[alimentos.size()];
			for (int i = 0; i < vuelta.length; i++) {
				vuelta[i] = alimentos.get(i);
			}
			
			return vuelta;
		} catch (IOException e) {
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		VentanaPatos vp = new VentanaPatos();
	}
}
