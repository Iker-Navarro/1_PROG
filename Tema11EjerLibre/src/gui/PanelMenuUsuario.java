package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class PanelMenuUsuario extends JPanel{
	private VentanaPrincipal vp;
	
	private JPanel panelPrincipal;
	
	private JButton btnTienda;
	private JButton btnCarro;
	private JButton btnRegistro;
	
	private JButton btnCerrarSesion;
	
	public PanelMenuUsuario(VentanaPrincipal vp) {
		this.vp = vp;
		
		dibujar();
		eventos();
	}

	private void dibujar() {
		setLayout(new BorderLayout());
		
		panelPrincipal = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 100, 5, 100);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 5;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		btnTienda = new JButton("TIENDA");
		panelPrincipal.add(btnTienda, gbc);
		
		gbc.gridy++;
		
		btnCarro = new JButton("TU CARRO");
		panelPrincipal.add(btnCarro, gbc);
		
		gbc.gridy++;
		
		btnRegistro = new JButton("REGISTRO COMPRAS");
		panelPrincipal.add(btnRegistro, gbc);
		
		gbc.gridy++;
		gbc.insets = new Insets(25, 100, 5, 100);
		
		btnCerrarSesion = new JButton("CERRAR SESIÓN");
		panelPrincipal.add(btnCerrarSesion, gbc);
		
		
		
		add(panelPrincipal, BorderLayout.NORTH);
	}
	
	private void eventos() {
		btnTienda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vp.movTienda();
			}
		});
		
		
		btnCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vp.setUsuarioActual(null);
				vp.vaciarEtiquetaUsuario();
				vp.movLogin();
			}
		});
		
		btnCarro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vp.movCarro();
			}
		});
		
		btnRegistro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vp.movTransacciones();				
			}
		});
	}
}
