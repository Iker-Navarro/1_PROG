package pack3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class VentanaPrincipal extends JFrame{
	
	private GestorXML gestor;
	
	private PanelTipoPlato pnlPrincipales;
	private PanelTipoPlato pnlPrimeros;
	private PanelTipoExtra pnlAdicionales;
	
	private JButton btnPedir;
	
	private DialogoPedir dialogoPedir;
	
	public VentanaPrincipal() {
		gestor = new GestorXML();
		
		dibujar();
		
		eventos();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void dibujar() {
		setTitle("RESTAURANTE");
		setLayout(new BorderLayout(25, 5));
		
		
		// C
		JPanel pnlGeneral = new JPanel(new GridLayout(1,3));
		
		pnlPrimeros = new PanelTipoPlato("primero", gestor);
		pnlPrincipales = new PanelTipoPlato("principal", gestor);
		pnlAdicionales = new PanelTipoExtra(gestor);
		
		pnlGeneral.add(pnlPrimeros);
		pnlGeneral.add(pnlPrincipales);
		pnlGeneral.add(pnlAdicionales);
		
		// S
	
		JPanel pnlSur = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnPedir = new JButton("PEDIR");
		pnlSur.add(btnPedir);
		
		//-----------------------

		add(pnlGeneral, BorderLayout.CENTER);
		add(pnlSur, BorderLayout.SOUTH);
		
		//-----------------------
		
		pack();
	}
	
	private void eventos() {
		btnPedir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Plato primero = pnlPrimeros.obtenerSeleccionado();
				Plato principal = pnlPrincipales.obtenerSeleccionado();
				if(primero == null || principal == null) {
					JOptionPane.showMessageDialog(
							null,
							"Se debe seleccionar un PRIMERO y un plato PRINCIPAL\nLos EXTRAS son opcionales",
							"Aviso",
							JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				DialogoPedir dialogo = new DialogoPedir(gestor, primero, principal, pnlAdicionales.obtenerSeleccionados());
			}
		});
	}
	
	
}
