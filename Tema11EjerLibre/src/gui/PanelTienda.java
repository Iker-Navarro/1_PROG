package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import beans.Producto;

public class PanelTienda extends JPanel{
	
	private VentanaPrincipal vp;
	
	private PanelListadoTienda panelCentral;
	private JScrollPane scrll;
	
	private JButton btnVolver;
	private JButton btnCarro;
	
	public PanelTienda(VentanaPrincipal vp) {
		this.vp = vp;
		
		dibujar();
		eventos();
	}

	private void dibujar() {
		setLayout(new BorderLayout());
		JPanel pnlSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JLabel lblTitulo = new JLabel("PRODUCTOS DISPONIBLES");
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 15));
		pnlSuperior.add(lblTitulo);
		
		panelCentral = new PanelListadoTienda(vp);
		scrll= new JScrollPane(panelCentral, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrll.setPreferredSize(new Dimension(700, 400));
		scrll.setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		
		
		JPanel pnlInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		
		btnVolver = new JButton("VOLVER");
		pnlInferior.add(btnVolver);
		
		btnCarro = new JButton("MI CARRO");
		pnlInferior.add(btnCarro);
		
		add(pnlSuperior, BorderLayout.NORTH);
		add(scrll, BorderLayout.CENTER);
		add(pnlInferior, BorderLayout.SOUTH);
	}
	
	private void eventos() {
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vp.movMenuPrincipal();
			}
		});
		
		btnCarro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vp.movCarro();
			}
		});
		
	}
	
	public void regenerarContenidoTienda(Producto p, int cantidad) {
		if(p.getStock() == 0) {
			remove(scrll);
			
			panelCentral = new PanelListadoTienda(vp);
			scrll= new JScrollPane(panelCentral, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			scrll.setPreferredSize(new Dimension(700, 400));
			scrll.setBorder(BorderFactory.createLineBorder(Color.BLACK));	
			
			add(scrll, BorderLayout.CENTER);
			
			revalidate();
			repaint();
		}
		else {
			panelCentral.incrementarStock(p, cantidad);
		}
	}
}

