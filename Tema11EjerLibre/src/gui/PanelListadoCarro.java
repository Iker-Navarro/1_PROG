package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.TreeMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import beans.Compra;
import beans.Producto;

public class PanelListadoCarro extends JPanel{
	private VentanaPrincipal vp;
	private JButton btnComprar;
	
	private double precioTotal;
	
	private LinkedHashMap<Compra, JButton> compras_botones;
	
	private JLabel lblPrecioTotal;
	private JButton btnVaciar; 
	
	public PanelListadoCarro(VentanaPrincipal vp, JButton btnComprar) {
		this.vp = vp;
		this.btnComprar = btnComprar;
		
		compras_botones = new LinkedHashMap<Compra, JButton>();
				
		dibujar();
		eventos();
	}
	
	private void dibujar() {
		setLayout(new GridBagLayout());
		ArrayList<Compra> listaCompras = vp.getBbdd().obtenerDatosCarro(vp.getUsuarioActual());
		
		btnVaciar = new JButton("VACIAR CARRO");
		if(listaCompras == null) 
			dibujarVacio();
		else
			dibujarLleno(listaCompras);
	}
	
	private void dibujarVacio() {
		btnComprar.setEnabled(false);
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel lblVacio = new JLabel("EL CARRO ESTÁ VACIO");
		add(lblVacio, gbc);
	}

	private void dibujarLleno(ArrayList<Compra> listaCompras) {
		precioTotal = 0;
		btnComprar.setEnabled(true);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 5, 10);
		
		dibujarTitulosColumas(gbc);
		
		for (Compra compra : listaCompras) {
			
			generarLineaCompra(compra, gbc);
			
			JButton nuevoBoton = new JButton("CANCELAR");
			add(nuevoBoton, gbc);
			compras_botones.put(compra, nuevoBoton);
			
			gbc.gridx = 0;
			gbc.gridy ++;		
		}
		
		JLabel lblTotal = new JLabel("PRECIO TOTAL: ");
	
		add(lblTotal, gbc);
		gbc.gridx++;
		
		lblPrecioTotal = new JLabel(""+precioTotal+"€");
		
		add(lblPrecioTotal, gbc);
		gbc.gridx++;
		
		btnVaciar = new JButton("VACIAR CARRO");
		
		add(btnVaciar, gbc);
	}
	
	
	
	private void dibujarTitulosColumas(GridBagConstraints gbc) {
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel lblProducto = new JLabel("PRODUCTO");
		add(lblProducto, gbc);
		
		gbc.gridx++;
		
		JLabel lblPrecio = new JLabel("PRECIO Unid");
		add(lblPrecio, gbc);
		
		gbc.gridx++;
		
		JLabel lblCantidad = new JLabel("CANTIDAD");
		add(lblCantidad, gbc);
		
		gbc.gridx++;
		
		JLabel lblPrecioTot = new JLabel("PRECIO TOTAL");
		add(lblPrecioTot, gbc);		
		
		gbc.gridx = 0;
		gbc.gridy ++;
	}

	private void reDibujarLleno() {
		precioTotal = 0;
		btnComprar.setEnabled(true);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 5, 10);
		
		dibujarTitulosColumas(gbc);
		
		Iterator<Compra> it = compras_botones.keySet().iterator();
		while (it.hasNext()) {
			Compra compra = it.next();
			
			generarLineaCompra(compra, gbc);
			
			add(compras_botones.get(compra), gbc);
			
			gbc.gridx = 0;
			gbc.gridy ++;	
		}

		
		JLabel lblTotal = new JLabel("PRECIO TOTAL: ");
		add(lblTotal, gbc);
		gbc.gridx++;
		
		add(lblPrecioTotal, gbc);
		lblPrecioTotal.setText(""+precioTotal + "€");
		gbc.gridx++;
		
		add(btnVaciar, gbc);
	}
	
	private void generarLineaCompra(Compra compra, GridBagConstraints gbc) {
		JLabel lblProductoC = new JLabel(compra.getNombre_producto());
		add(lblProductoC, gbc);
		
		gbc.gridx++;
		
		JLabel lblPrecioC = new JLabel(""+compra.getPrecio() + "€");
		add(lblPrecioC, gbc);
		
		gbc.gridx++;
		
		JLabel lblCantidadC = new JLabel(""+compra.getCantidad());
		add(lblCantidadC, gbc);
		
		gbc.gridx++;
		double precioCompra = compra.getCantidad() * compra.getPrecio();
		JLabel lblPrecioTotC = new JLabel(""+precioCompra + "€");
		add(lblPrecioTotC, gbc);
		
		gbc.gridx++;
		
		precioTotal += precioCompra;
	}
	
	private void eventos() {
		Iterator<Compra> it = compras_botones.keySet().iterator();
		while (it.hasNext()) {
			Compra compra = it.next();
			compras_botones.get(compra).addActionListener(new EscuchadorBorrarCompra(compra));
		}
		
		btnVaciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Iterator<Compra> it = compras_botones.keySet().iterator();
				while (it.hasNext()) {
					Compra compra = it.next();
					
					Producto prod = vp.getBbdd().obtenerProducto(compra.getId_producto());
					
					vp.getBbdd().borrarCompra(compra);
					
					int nuevoStock = prod.getStock();
					nuevoStock += compra.getCantidad();
					
					vp.getBbdd().actualizarStock(compra.getId_producto(), nuevoStock);
					
					vp.regenerarContenidoTienda(prod, compra.getCantidad());
						
				}
				removeAll();
				dibujarVacio();
				revalidate();
				repaint();
			}
		});
	}
	
	private class EscuchadorBorrarCompra implements ActionListener{
		private Compra compra;
		
		public EscuchadorBorrarCompra(Compra compra) {
			this.compra = compra;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int seleccion = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres eliminar la compra seleccionada?");
			if(seleccion == JOptionPane.YES_OPTION) {
				Producto prod = vp.getBbdd().obtenerProducto(compra.getId_producto());
				
				vp.getBbdd().borrarCompra(compra);
				
				int nuevoStock = prod.getStock();
				nuevoStock += compra.getCantidad();
				
				vp.getBbdd().actualizarStock(compra.getId_producto(), nuevoStock);
				
				compras_botones.remove(compra);
				
				removeAll();
				
				if(compras_botones.size() == 0)
					dibujarVacio();
				else
					reDibujarLleno();
				
				vp.regenerarContenidoTienda(prod, compra.getCantidad());
					
				revalidate();
				repaint();
			}
		}
	}
	
	
}
