package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import beans.Usuario;

public class PanelLogin extends JPanel{
	private VentanaPrincipal vp;
	
	private JTextField txtUser;
	private JPasswordField txtPass;
	
	private JButton btnLogin;
	private JButton btnRegistro;
	
	public PanelLogin(VentanaPrincipal vp) {
		this.vp = vp;
		
		dibujar();
		eventos();
	}

	private void dibujar() {
		setLayout(new BorderLayout());
		
		JPanel panelPrincipal = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,3,5,3);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		txtUser = new JTextField("USUARIO", 10);
		txtUser.setForeground(Color.GRAY);
		panelPrincipal.add(txtUser, gbc);
		
		gbc.gridy++;
		
		txtPass = new JPasswordField("PASSWORD", 10);
		txtPass.setForeground(Color.GRAY);
		panelPrincipal.add(txtPass, gbc);
		
		gbc.gridy++;
		gbc.gridwidth = 1;
		
		btnLogin = new JButton("INICIAR SESIÓN");
		panelPrincipal.add(btnLogin, gbc);
		
		gbc.gridx++;
		
		btnRegistro = new JButton("REGISTRARSE");
		panelPrincipal.add(btnRegistro, gbc);
		
		add(panelPrincipal, BorderLayout.NORTH);
	}
	
	private void eventos() {
		txtUser.addFocusListener(new placeHolderListener("USUARIO"));
		txtPass.addFocusListener(new placeHolderListener("PASSWORD"));
		
		btnRegistro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vp.movRegistro();
			}
		});
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Usuario u = vp.getBbdd().accesoValido(txtUser.getText(), txtPass.getText());
				if(u == null) {
					JOptionPane.showMessageDialog(null, "ERROR: Usuario o Constraseña incorrectos", "Error de login", JOptionPane.ERROR_MESSAGE);;
				}else {
					txtPass.setText("PASSWORD");
					txtUser.setText("USUARIO");
					vp.setUsuarioActual(u);
					vp.iniciarSesion();
				}
					
			}
		});
	}
	
	private class placeHolderListener implements FocusListener{
		String valorInicial;
		
		public placeHolderListener(String valorInicial) {
			this.valorInicial = valorInicial;
		}
		
		@Override
		public void focusGained(FocusEvent e) {
			JTextField source = (JTextField)e.getSource();
			if(source.getText().equalsIgnoreCase(valorInicial)) {
				source.setForeground(Color.BLACK);
				source.setText("");
			}
		}
		
		@Override
		public void focusLost(FocusEvent e) {
			JTextField source = (JTextField)e.getSource();
			if(source.getText().isEmpty()) {
				source.setForeground(Color.GRAY);
				source.setText(valorInicial);
			}
			
		}
		
	}
}
