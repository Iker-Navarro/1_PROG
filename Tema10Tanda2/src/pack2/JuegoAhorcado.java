package pack2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class JuegoAhorcado extends JFrame{
	
	private Ahorcado juego;
	
	private Icon iconoError;
	
	private JButton btnJugar;
	private JLabel[] lblErrores;
	private JLabel lblCentral;
	private JButton[] btnAlfabeto;
	
	private JPanel pnlOeste;
	
	public JuegoAhorcado() {
		
		iconoError = UIManager.getIcon("OptionPane.errorIcon");
		
		dibujar();
		
		eventos();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	private void dibujar() {
		
		setTitle("AHORCADO");
		setLayout(new BorderLayout());
		
		// N
		
		JPanel pnlNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnJugar = new JButton("JUGAR");
		pnlNorte.add(btnJugar);
		
		// W
		
		pnlOeste = new JPanel(new FlowLayout());

		// C
		
		JPanel pnlCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlCentral.setPreferredSize(new Dimension(0, 200));
		
		lblCentral = new JLabel("");
		lblCentral.setFont(new Font("serif", Font.PLAIN, 40));
		pnlCentral.add(lblCentral);
		
		// S
		
		JPanel pnlSur = new  JPanel(new GridLayout(0, 13, 8, 2));
		btnAlfabeto = new JButton['Z' - 'A' + 1];
		for (int i = 0; i < btnAlfabeto.length; i++) {
			btnAlfabeto[i] = new JButton("" + (char) ('A' + i));
			btnAlfabeto[i].setEnabled(false);
			pnlSur.add(btnAlfabeto[i]);
		}
		
		//----------------
		
		add(pnlNorte, BorderLayout.NORTH);
		add(pnlOeste, BorderLayout.WEST);
		add(pnlCentral, BorderLayout.CENTER);
		add(pnlSur, BorderLayout.SOUTH);
		
		//----------------
		
		pack();
		
	}
	
	private void eventos() {
		
		btnJugar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				instanciarAhorcado();
				
				// Instanciar labels de errores en invisible
				lblErrores = new JLabel[juego.getVidasTotales()];
				for (int i = 0; i < lblErrores.length; i++) {
					lblErrores[i] = new JLabel(iconoError);
					lblErrores[i].setVisible(false);
					pnlOeste.add(lblErrores[i]);
				}
				
				lblCentral.setText(juego.respuestaToBigString());
				
				// deshabilitar y habilitar botones
				btnJugar.setEnabled(false);
				for (JButton boton : btnAlfabeto) {
					boton.setEnabled(true);
				}
				
				pnlOeste.revalidate();
				pnlOeste.repaint();			
			}

			private void instanciarAhorcado() {
				do {
					String respuesta = JOptionPane.showInputDialog("Elije el numero de letras");
					
					if(respuesta == null || respuesta.equals("")) {
						JOptionPane.showMessageDialog(null, "Iniciando juego con palabra por defecto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
						juego = new Ahorcado();
					}
					else {
						try {
							juego = new Ahorcado(Integer.parseInt(respuesta));
						} catch(NumberFormatException ex) {
							JOptionPane.showMessageDialog(null, "Introduce un valor numerico", "Error", JOptionPane.ERROR_MESSAGE);
						} catch (IllegalArgumentException exc) {
							JOptionPane.showMessageDialog(null, "No se ha encontrado ninguna palabra con la cantidad de letras dada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
							juego = new Ahorcado();
						}
						
					}
				}while(juego == null);
			}
			
		});
		
		ActionListener escuchadorLetras = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Obtener letra del texto del JButton
				char letra = ((JButton) e.getSource()).getText().charAt(0);
				
				// Tirada
				if(juego.tirar(letra)) 
					lblCentral.setText(juego.respuestaToBigString());
				else 
					lblErrores[juego.getVidasTotales() - juego.getVidasRestantes() - 1].setVisible(true);
				
				// Checkear victoria/derrota 
				if(juego.completo()) {
					JOptionPane.showMessageDialog(null, "Enhorabuena. Palabra " + juego.getPalabra() + " acertada" , "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					juego.actualizarAciertosEnXML();
					resetear();
				}
				else if(juego.getVidasRestantes() == 0) {
					JOptionPane.showMessageDialog(null, "Se acabarón tus vidas. Palabra: " + juego.getPalabra(), "Mensaje", JOptionPane.INFORMATION_MESSAGE);
					resetear();
				}
			}
			
			private void resetear() {
				lblCentral.setText("");;
				pnlOeste.removeAll();
				
				for (JButton boton : btnAlfabeto) {
					boton.setEnabled(false);
				}
				btnJugar.setEnabled(true);
				
				pnlOeste.revalidate();
				pnlOeste.repaint();
			}
		};
		
		// Añadir listener a botones de letras
		for (JButton boton : btnAlfabeto) {
			boton.addActionListener(escuchadorLetras);
		}	
		
	}
	
}
