package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;

import beans.Usuario;

public class PanelRegistro extends JPanel{
	
	private final static Border BORDE_ROJO =  BorderFactory.createLineBorder(Color.RED), BORDE_POR_DEFECTO = (UIManager.getLookAndFeel().getDefaults().getBorder("TextField.border"));
	
	private VentanaPrincipal vp;
	
	private JPanel panelPrincipal;
	
	private JTextField txtUsuario;
	
	private JTextField txtNombre;
	private JTextField txtApellidos;
	
	private JPasswordField txtPass;
	private JPasswordField txtPass2;
	
	private JButton btnVolver;
	private JButton btnRegistrarse;
	
	public PanelRegistro(VentanaPrincipal vp) {
		this.vp = vp;
		
		dibujar();
		eventos();
	}

	private void dibujar() {
		setLayout(new BorderLayout());
		
		panelPrincipal = new JPanel(new GridBagLayout());
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5,3,5,3);
		
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 3;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel lblTit = new JLabel("INTRODUCE TUS DATOS", SwingConstants.CENTER);
		panelPrincipal.add(lblTit, gbc);
		
		gbc.gridy++;
		gbc.gridwidth = 1;
		
		JLabel lblUsuario = new JLabel("USUARIO");
		txtUsuario = new JTextField(10);
		generarLinea(lblUsuario, txtUsuario, gbc);
	
		JLabel lblNombre = new JLabel("NOMBRE");
		txtNombre = new JTextField(10);
		generarLinea(lblNombre, txtNombre, gbc);
		
		JLabel lblApellidos = new JLabel("APELLIDOS");
		txtApellidos = new JTextField(10);
		generarLinea(lblApellidos, txtApellidos, gbc);	
		
		JLabel lblPass = new JLabel("CONTRASEÑA");
		txtPass = new JPasswordField(10);
		generarLinea(lblPass, txtPass, gbc);	
		
		JLabel lblPass2 = new JLabel("REPETIR CONTRASEÑA");
		txtPass2 = new JPasswordField(10);
		generarLinea(lblPass2, txtPass2, gbc);
		
		btnVolver = new JButton("VOLVER");
		panelPrincipal.add(btnVolver, gbc);
		gbc.gridx++;
		
		btnRegistrarse = new JButton("REGISTRARSE");
		panelPrincipal.add(btnRegistrarse, gbc);
		
		add(panelPrincipal, BorderLayout.NORTH);
	}
	
	private void generarLinea(JLabel lbl, JTextField txt, GridBagConstraints gbc){

		panelPrincipal.add(lbl, gbc);
		
		gbc.gridx++;
	
		panelPrincipal.add(txt, gbc);
		
		gbc.gridx = 0;
		gbc.gridy++;
	}	
	
	private void eventos() {
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarCampos();
				
				vp.movLogin();
			}
		});
		
		btnRegistrarse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Check que haya contenido en todos los campos
				if(todoRellenado() ) {
					// Check si el usuario esta disponible
					if(vp.getBbdd().usuarioDisponible(txtUsuario.getText())) {
						// Check contraseñas iguales
						// TODO guardar contraseña de modo seguro y no en texto plano
						if(Arrays.equals(txtPass.getPassword(),txtPass2.getPassword())) {
							Usuario u = new Usuario(
									txtUsuario.getText(),
									txtNombre.getText(),
									txtApellidos.getText(),
									new Date(), 
									txtPass.getText(),
									"USER");
							
							vp.getBbdd().darDeAlta(u);
							
							JOptionPane.showMessageDialog(null, "Usuario añadido correctamente");
							limpiarCampos();
							
							vp.setUsuarioActual(u);
							vp.iniciarSesion();
							
						}
						else {
							JOptionPane.showMessageDialog(null, "ERROR, Las contraseñas dadas no coinciden", "Error", JOptionPane.ERROR_MESSAGE);
						}	
					}
					else {
						JOptionPane.showMessageDialog(null, "ERROR, El usuario dado no está disponible", "Error", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
			
			private boolean todoRellenado() {
				boolean vuelta = true;
				
				if(!campoLleno(txtUsuario))
					vuelta = false;
				if(!campoLleno(txtNombre))
					vuelta = false;
				if(!campoLleno(txtApellidos))
					vuelta = false;
				if(!campoLleno(txtPass))
					vuelta = false;
				if(!campoLleno(txtPass2))
					vuelta = false;
				
				return vuelta;
			}
			
			private boolean campoLleno(JTextField txt) {
				if(txt.getText().isEmpty()) {
					txt.setBorder(BORDE_ROJO);
					return false;
				}else {
					txt.setBorder(BORDE_POR_DEFECTO);
					return true;
				}
			}
			
		});
	}
	
	private void limpiarCampos() {
		txtUsuario.setText("");
		txtUsuario.setBorder(BORDE_POR_DEFECTO);
		
		txtNombre.setText("");
		txtNombre.setBorder(BORDE_POR_DEFECTO);
		
		txtApellidos.setText("");
		txtApellidos.setBorder(BORDE_POR_DEFECTO);
		
		txtPass.setText("");
		txtPass.setBorder(BORDE_POR_DEFECTO);
		
		txtPass2.setText("");
		txtPass2.setBorder(BORDE_POR_DEFECTO);
	}
}
