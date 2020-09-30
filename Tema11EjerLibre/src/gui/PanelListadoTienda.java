package gui;

import java.awt.Component;
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

public class PanelListadoTienda extends JPanel{

	private VentanaPrincipal vp;
	
	private LinkedHashMap<Producto, Component[]> productos;
	
	public PanelListadoTienda(VentanaPrincipal vp) {
		this.vp = vp;
				
		productos = new LinkedHashMap<Producto, Component[]>();
		
		dibujar();
		eventos();
	}

	private void dibujar() {
		setLayout(new GridBagLayout());
		
		ArrayList<Producto> productosMostrados = vp.getBbdd().todosProductos();

		if(productosMostrados == null || productosMostrados.size() == 0) 
			dibujarVacio();
		else
			dibujarLleno(productosMostrados);
	}

	private void dibujarVacio() {
		GridBagConstraints gbc = new GridBagConstraints();
		JLabel lblVacio = new JLabel("No hay productos disponibles en la tienda");
		add(lblVacio, gbc);
	}

	private void dibujarLleno(ArrayList<Producto> productosMostrados) {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 5, 10);
		
		generarTitulos(gbc);
		
		for (Producto prod : productosMostrados) {
			generarLineaProducto(prod, gbc);
		}
		
	}
	
	private void reDibujarLleno() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 10, 5, 10);
		
		generarTitulos(gbc);
		Iterator<Producto> it = productos.keySet().iterator();
		while (it.hasNext()) {
			Producto prod = it.next();
			generarLineaProducto(prod, gbc);
		}
	}
	
	private void generarTitulos(GridBagConstraints gbc) {
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel lblNombre = new JLabel("NOMBRE");
		add(lblNombre, gbc);
		
		gbc.gridx++;
		
		JLabel lblDesc = new JLabel("DESCRIPCION");
		add(lblDesc, gbc);
		
		gbc.gridx++;
		
		JLabel lblStock = new JLabel("STOCK");
		add(lblStock, gbc);
		
		gbc.gridx++;
		
		JLabel lblPrecio = new JLabel("PRECIO");
		add(lblPrecio, gbc);
		
		gbc.gridx = 0;
		gbc.gridy ++;
	}
	
	private void generarLineaProducto(Producto prod, GridBagConstraints gbc) {
		
		JLabel lblNombreP = new JLabel(prod.getNombre());
		add(lblNombreP, gbc);
		
		gbc.gridx++;
		
		JButton btnDesc = new JButton("INFO");
		btnDesc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, prod.getDescripcion(), "INFO", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		add(btnDesc, gbc);
		
		gbc.gridx++;
		
		JLabel lblStock = new JLabel("" + prod.getStock());
		add(lblStock, gbc);
		
		gbc.gridx++;
		
		JLabel lblPreciop = new JLabel("" + prod.getPrecio() + "€");
		add(lblPreciop, gbc);
		
		gbc.gridx++;
		
		JButton btnComprar = new JButton("COMPRAR");
		add(btnComprar, gbc);
		
		gbc.gridx = 0;
		gbc.gridy ++;
		
		productos.put(prod, new Component[]{lblStock, btnComprar});
	}
	
	private void reGenerarLineaProducto(Producto prod, GridBagConstraints gbc) {
		
		JLabel lblNombreP = new JLabel(prod.getNombre());
		add(lblNombreP, gbc);
		
		gbc.gridx++;
		
		JButton btnDesc = new JButton("INFO");
		btnDesc.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, prod.getDescripcion(), "INFO", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
		add(btnDesc, gbc);
		
		gbc.gridx++;
		
		add(productos.get(prod)[0], gbc);
		
		gbc.gridx++;
		
		JLabel lblPreciop = new JLabel("" + prod.getPrecio());
		add(lblPreciop, gbc);
		
		gbc.gridx++;
		
		add(productos.get(prod)[1], gbc);
		
		gbc.gridx = 0;
		gbc.gridy ++;
	}
	
	public void incrementarStock(Producto prod, int cantidad) {
		Iterator<Producto> it = productos.keySet().iterator();
		while (it.hasNext()) {
			Producto producto = (Producto) it.next();
			if(prod.equals(producto)) {
				JLabel lblSt = (JLabel) productos.get(producto)[0];
				int cantActual = Integer.parseInt(lblSt.getText());
				lblSt.setText("" + (cantActual + cantidad));
				producto.setStock(cantActual + cantidad); 
			}
		}
		
	}
	
	private void eventos() {
		
		Iterator<Producto> it = productos.keySet().iterator();
		while (it.hasNext()) {
			Producto prod = it.next();
			JButton btn = (JButton) productos.get(prod)[1];
			btn.addActionListener(new EscuchadorComprar(prod, (JLabel) productos.get(prod)[0]));
		}
		
	}
	
	private class EscuchadorComprar implements ActionListener{
		private Producto p;
		private JLabel lblStock;
		
		public EscuchadorComprar(Producto prod, JLabel lblStock) {
			p = prod;
			this.lblStock = lblStock;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int cantidadComprada = -1;
			do {
				String respuesta = null;
				try {
					respuesta = JOptionPane.showInputDialog("Introduce la cantidad a comprar.");
					cantidadComprada = Integer.parseInt(respuesta);
				}catch (NumberFormatException ex) {
					if(respuesta == null)
						return;
				}
			} while (cantidadComprada <= 0 || cantidadComprada > p.getStock());
			
			vp.getBbdd().aniadirACarro(vp.getUsuarioActual(), p, cantidadComprada);
			
			int nuevoStock = p.getStock()-cantidadComprada;
			
			vp.getBbdd().actualizarStock(p.getIdProd(), nuevoStock);
			if (nuevoStock == 0) {
				removeAll();
				productos.remove(p);
				reDibujarLleno();
				revalidate();
				repaint();
			}else {
				p.setStock(nuevoStock);
				lblStock.setText(""+nuevoStock);
			}
			
			vp.regenerarContenidoCarro();
		}

	}
}
