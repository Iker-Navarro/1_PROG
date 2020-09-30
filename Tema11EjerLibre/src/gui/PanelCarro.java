package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

import beans.Transaccion;

public class PanelCarro extends JPanel{
	
	private VentanaPrincipal vp;
	
	private PanelListadoCarro pnlCentral;
	
	private JButton btnComprar;
	private JButton btnContComp;
	private JButton btnVolver;
	
	public PanelCarro(VentanaPrincipal vp) {
		this.vp = vp;
		
		dibujar();
		eventos();
	}

	private void dibujar() {
		setLayout(new BorderLayout());
		
		JPanel pnlSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JLabel lblTitulo = new JLabel("TU CARRO");
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 15));
		pnlSuperior.add(lblTitulo);
		
		btnComprar = new JButton("COMPRAR");
		pnlCentral = new PanelListadoCarro(vp, btnComprar);
		
		JPanel pnlInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
		btnVolver = new JButton("VOLVER");
		pnlInferior.add(btnVolver);
		
		btnContComp = new JButton("CONTINUAR COMPRANDO");
		pnlInferior.add(btnContComp);
		
		
		pnlInferior.add(btnComprar);
		
		//-----------------
		add(pnlSuperior, BorderLayout.NORTH);
		add(pnlCentral, BorderLayout.CENTER);
		add(pnlInferior, BorderLayout.SOUTH);		
		//-----------------
	}
	
	private void eventos() {
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vp.movMenuPrincipal();
			}
		});
		
		btnContComp.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vp.movTienda();
			}
		});
		
		btnComprar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Transaccion transaccion = vp.getBbdd().realizarCompra(vp.getUsuarioActual());
				vp.aniadirNuevaTransaccion(transaccion, vp.getBbdd().comprasEnTransaccion(transaccion.getId_transaccion()));
				JOptionPane.showMessageDialog(null, "COMPRA REALIZADA CORRECTAMENTE\nFACTURA GENERADA");
				regenerarContenidoCarro();
				btnComprar.setEnabled(false);
			}
		});
	}

	public void regenerarContenidoCarro() {
		remove(pnlCentral);
		pnlCentral = new PanelListadoCarro(vp, btnComprar);
		add(pnlCentral, BorderLayout.CENTER);
		revalidate();
		repaint();
		vp.pack();
	}
	
	public JButton getBtnComprar() {
		return btnComprar;
	}
}
