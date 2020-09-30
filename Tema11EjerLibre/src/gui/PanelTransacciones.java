package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import beans.Compra;
import beans.Transaccion;

public class PanelTransacciones extends JPanel{
	
	private VentanaPrincipal vp;
	
	private LinkedHashMap<Transaccion, ArrayList<Compra>> transacciones;

	private DefaultListModel<Transaccion> mdlTransacciones;
	private JList<Transaccion> lstTransacciones;
	
	private JButton btnVolver;
	
	public PanelTransacciones(VentanaPrincipal vp) {
		this.vp = vp;
		
		mdlTransacciones = new DefaultListModel<Transaccion>();
		
		transacciones = vp.getBbdd().transaccionesUsuario(vp.getUsuarioActual());
		Iterator<Transaccion> it = transacciones.keySet().iterator();
		while (it.hasNext()) {
			Transaccion t = it.next();
			mdlTransacciones.addElement(t);
		}
		dibujar();
		eventos();
	}

	private void dibujar() {
		setLayout(new BorderLayout());
		JPanel pnlSuperior = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JLabel lblTitulo = new JLabel("TRANSACCIONES");
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 15));
		pnlSuperior.add(lblTitulo);
		
		
		lstTransacciones = new JList<Transaccion>();
		DefaultListCellRenderer renderer =  (DefaultListCellRenderer)lstTransacciones.getCellRenderer();  
		renderer.setHorizontalAlignment(JLabel.CENTER);  
		
		lstTransacciones.setModel(mdlTransacciones);
		
		JScrollPane scrll= new JScrollPane(lstTransacciones, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrll.setPreferredSize(new Dimension(700, 400));
		scrll.setBorder(BorderFactory.createLineBorder(Color.BLACK));	
		
		
		JPanel pnlInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		
		btnVolver = new JButton("VOLVER");
		pnlInferior.add(btnVolver);
		
		
		add(pnlSuperior, BorderLayout.NORTH);
		add(scrll, BorderLayout.CENTER);
		add(pnlInferior, BorderLayout.SOUTH);
	}

	private void eventos() {
		lstTransacciones.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2) {
					Transaccion transaccionSeleccionada = lstTransacciones.getSelectedValue();
					String mensaje = "TRANSACCION nº" + transaccionSeleccionada.getId_transaccion() + " \nFECHA " + transaccionSeleccionada.getFecha() + "\n";
					for (Compra compra : transacciones.get(transaccionSeleccionada)) {
						mensaje += ">>>" + compra.getNombre_producto() + " " + compra.getCantidad() + " unidades " + compra.getPrecio() + "€\n"; 
					};
					
					JOptionPane.showMessageDialog(null, mensaje, "TRANSACCIÓN", JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		
		btnVolver.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				vp.movMenuPrincipal();
			}
		});
	}
	
	public void aniadirTransaccion(Transaccion t, ArrayList<Compra> compras) {
		transacciones.put(t, compras);
		mdlTransacciones.addElement(t);
	}
}
