package pack3;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class DialogoPedir extends JDialog{
	
	private GestorXML gestor;
	
	private Plato primero;
	private Plato principal;
	private Plato[] extras;
	
	private JButton btnAceptar;
	private JButton btnDescartar;
	
	public DialogoPedir(GestorXML gestor, Plato primero, Plato principal, Plato[] extras) {
		
		this.gestor = gestor;
		
		this.primero = primero;
		this.principal = principal;
		this.extras = extras;		
		
		setModal(true);
		
		dibujar();
		
		eventos();
		
		setVisible(true);
	}

	private void dibujar() {
		DecimalFormat df = new DecimalFormat("#.##");
		
		setTitle("TU PEDIDO");
		setResizable(false);
		
		BoxLayout bl = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
		setLayout(bl);
		
		double precioTotal = 0;
		
		// PLATOS
		JPanel pnlPlatos = new JPanel(new GridLayout(2, 2, 20, 5));
		
		aniadirPlato(pnlPlatos, primero);
		precioTotal += primero.getPrecio();
		
		aniadirPlato(pnlPlatos, principal);
		precioTotal += principal.getPrecio();
		
		
		//EXTRAS
		JPanel pnlExtras = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		JLabel lblExtras = null;
		if(extras.length != 0) {
			String contenido = "";
			double precioExtras = 0;
			for (Plato plato : extras) {
				precioExtras += plato.getPrecio();
				contenido += plato.getNombre() + " (" + plato.getPrecio() + "€), ";
			}
			
			contenido = contenido.substring(0, contenido.length()-2) + ": " + df.format(precioExtras) + "€";
			lblExtras = new JLabel(contenido);
			precioTotal += precioExtras;
		}
		else 
			lblExtras = new JLabel("Sin extras");
		
		pnlExtras.add(lblExtras);
		
		
		// TOTAL
		JPanel pnlTotal = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblTotal = new JLabel("EL PRECIO TOTAL: " + df.format(precioTotal) + "€");
		lblTotal.setFont(new Font("SansSerif", Font.PLAIN, 25));
		lblTotal.setBorder(BorderFactory.createMatteBorder(5, 0, 5, 0, Color.BLACK));
		pnlTotal.add(lblTotal);
		// BOTONES
		
		JPanel pnlBotones = new JPanel(new FlowLayout(FlowLayout.LEFT));
		btnAceptar = new JButton("ACEPTAR");
		btnDescartar = new JButton("DESCARTAR");
		pnlBotones.add(btnAceptar);
		pnlBotones.add(btnDescartar);
		
		//-------------------
		add(pnlPlatos);
		add(pnlExtras);
		add(pnlTotal);
		add(pnlBotones);
		//-------------------
		
		pack();
	}
	
	private void aniadirPlato(JPanel panel, Plato plato) {
		// Funcion para añadir el nombre de un plato y su imagen (o comodin).
		panel.add(new JLabel(plato.getNombre() + " " + plato.getPrecio() + "€", SwingConstants.RIGHT));
		if(plato.getImagen() == null) 
			panel.add(new JLabel(redim("img/comodin.jpg", 150, 100)));
		else
			panel.add(new JLabel(redim(plato.getImagen(), 150, 100)));	
	}
	
	private static ImageIcon redim (String fichImag, int ancho, int alto){
        ImageIcon imIcon=new ImageIcon(fichImag);
        Image im=imIcon.getImage();
        Image im2= im.getScaledInstance(ancho, alto, 0);
        return new ImageIcon(im2);
	}
	
	private void eventos() {
		btnDescartar.addActionListener(new DescartarListener());
		btnAceptar.addActionListener(new AceptarListener());
		
	}
	private class DescartarListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
		
	}
	
	private class AceptarListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			gestor.guardarPedido(primero, principal, extras);
			dispose();
		}
	}
}
