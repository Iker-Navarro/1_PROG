package pack1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaPuntos extends JFrame implements MouseListener{
	
	private final static String FICHTXT = "carpetapuntos";
	
	private JComboBox<String> cmbArchivos;
	private JList<Punto> lstPuntos;
	private DefaultListModel<Punto> mdlPuntos;
	private JLabel lblVariable;
	private JTextField txtDistancia;
	
	public VentanaPuntos() {
		
		dibujar();
		eventos();
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}	
	
	private void dibujar() {
		
		// la carpeta FICHTXT existe y tuene al menos un fichero .txt
		
		setLayout(null);
		
		cmbArchivos = new JComboBox<String>(obtenerContenidoCombo());
		cmbArchivos.setBounds(25, 25, 150, 40);
		
		mdlPuntos = new DefaultListModel<Punto>();
		
		lstPuntos = new JList<Punto>(mdlPuntos);
		lstPuntos.setBounds(25, 85, 150, 200);
		lstPuntos.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		JPanel pnlDistancia = new JPanel();
		pnlDistancia.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlDistancia.setBounds(200, 25, 300, 80);
		
		lblVariable = new JLabel("DISTANCIA");
		
		txtDistancia = new JTextField();
		txtDistancia.setEnabled(false);
		txtDistancia.setPreferredSize(new Dimension(200, 35));
		txtDistancia.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		
		pnlDistancia.add(lblVariable);
		pnlDistancia.add(txtDistancia);
		
		//--------------------
		
		add(cmbArchivos);
		add(lstPuntos);
		add(pnlDistancia);
		
		//--------------------
		
		//pack();
		setSize(1200, 900);
	}

	private String[] obtenerContenidoCombo() {
		File f = new File(FICHTXT);

		String[] contenidoCarpeta = f.list();
		ArrayList<String> contenidoValido = new ArrayList<String>(); 
		
		for (String archivo : contenidoCarpeta) {
			if(esTxt(archivo, FICHTXT))
				contenidoValido.add(archivo);
		}
		
		String[] validos = new String[contenidoValido.size()];
		for(int i = 0; i < contenidoValido.size(); i++) {
			validos[i] = contenidoValido.get(i);
		}
		return validos;
	}
	
	private void eventos() {
		// sacar puntos de archivo en lista
		cmbArchivos.addActionListener(new ActionListener() {	
			@Override
			public void actionPerformed(ActionEvent e) {
				txtDistancia.setEnabled(false);
				txtDistancia.setText("");
				lblVariable.setText("DISTANCIA");
				
				String archivo = FICHTXT + "/" + (String) cmbArchivos.getSelectedItem();
				
				try {
					BufferedReader br = new BufferedReader(new FileReader(archivo));
					String linea = br.readLine();
					
					mdlPuntos.removeAllElements();
					
					while(linea!=null) {
						
						String[] separado = linea.split(",");
						
						if(separado.length == 2) {
							try {
								double x = Double.parseDouble(separado[0]), y = Double.parseDouble(separado[1]);
								mdlPuntos.addElement(new Punto(x, y));
							} catch(NumberFormatException exception) {}
						}
						linea = br.readLine();
					}
					br.close();
				} catch (Exception exception) {
					System.out.println("Ha ocurrido un error");
				}
				
			}
		});
		
		// cambiar label y activar textField
		lstPuntos.addMouseListener(this);
		
		// Evitar introducción de caracteres no validos
		txtDistancia.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if((e.getKeyChar() < '0' || e.getKeyChar() > '9') && e.getKeyChar() != '.' )
					e.consume();
			}
		});
		
		// procesar distancia dada y eliminar punto si es correcto
		txtDistancia.addActionListener(new DistanceListener());
		
	}
	
	public class DistanceListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			String mensaje = "";
			
			if(txtDistancia.getText().equals("") || Math.abs(lstPuntos.getSelectedValue().distanciaAOrigen() - Double.parseDouble(txtDistancia.getText())) > 0.2)
				mensaje = "Distancia incorrecta, intentalo de nuevo";
			else {
				mensaje = "distancia correcta, se eliminará el punto de la lista";
				mdlPuntos.remove(lstPuntos.getSelectedIndex());
				txtDistancia.setText("");
				lblVariable.setText("DISTANCIA");
				txtDistancia.setEnabled(false);
			}
			JOptionPane.showMessageDialog(null, mensaje, "mensaje", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	private boolean esTxt(String archivo, String ruta) {
		
		File f = new File(ruta + "/" + archivo);
		
		if(!f.isFile())
			return false;
		
		if(archivo.endsWith(".txt"))
			return true;
		
		return false;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getClickCount() == 2) {
			lblVariable.setText("Dame la distancia del punto " + lstPuntos.getSelectedValue().toString() + " a (0, 0)");
			txtDistancia.setEnabled(true);
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {}

	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {}

	@Override
	public void mouseReleased(MouseEvent e) {}
}
