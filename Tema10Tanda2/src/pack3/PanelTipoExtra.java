package pack3;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import pack3.PanelTipoPlato.EscuchadorPlato;

public class PanelTipoExtra extends JPanel{
	
	private ArrayList<Plato> seleccionados;
	
	private Plato[] platos;
	private JCheckBox[] chkElementos;
	private JLabel lblPrecio;
	
	public PanelTipoExtra(GestorXML gestor) {	
		seleccionados = new ArrayList<Plato>();
		
		platos = gestor.damePlatos("adicional");
		chkElementos = new JCheckBox[platos.length];
		
		dibujar();
		eventos();
	}

	public void dibujar() {
		BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(bl);
		
		JLabel lblTit = new JLabel("EXTRAS (precio adicional)");
		lblTit.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLUE));
		
		add(lblTit);
		
		for(int i = 0; i < platos.length; i++) {
			chkElementos[i] = new JCheckBox(platos[i].getNombre());
			add(chkElementos[i]);
		}
		
		// Es un espacio y no totalmente vacio para que al hacer pack() deje espacio para el precio
		lblPrecio = new JLabel(" ");
		add(lblPrecio);
		
	}
	
	private void eventos() {
		for(int i = 0; i < chkElementos.length; i++) {
			chkElementos[i].addActionListener(new EscuchadorPlatoAdicional(platos[i]));
		}
	}
	
	public Plato[] obtenerSeleccionados() {
		ArrayList<Plato> seleccionados = new ArrayList<Plato>();
		for (int i = 0; i < chkElementos.length; i++) {
			if(chkElementos[i].isSelected())
				seleccionados.add(platos[i]);
		}
		
		Plato[] vuelta = new Plato[seleccionados.size()];
		for (int i = 0; i < seleccionados.size(); i++) {
			vuelta[i] = seleccionados.get(i);
		}
		
		return vuelta;
	}
	
	public class EscuchadorPlatoAdicional implements ActionListener{		
		private Plato plato;
		
		public EscuchadorPlatoAdicional(Plato seleccionado) {
			this.plato = seleccionado;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// obtener precio almacenado en check
			double precio = plato.getPrecio();
			
			if(((JCheckBox)e.getSource()).isSelected()) {
				// si se ha seleccionado añadir a precios
				seleccionados.add(plato);
				lblPrecio.setText(lblPrecio.getText().equals(" ") ? precio + "€ " : lblPrecio.getText() + "+ " + precio + "€ ");
			} 
			else {
				// si se ha des-seleccionado generar la cadena de vuelta eliminando del arrayList el valor que ya no está seleccionado
				String nuevo = ""; 
				boolean eliminado = false;
				
				Iterator<Plato> it = seleccionados.iterator();
				while (it.hasNext()) {
					Plato pActual = it.next();
					
					if(!eliminado && precio == pActual.getPrecio()) {
						it.remove();
						eliminado = true;
					}
					else {
						nuevo += pActual.getPrecio() + "€ + ";
					}
				}
				
				// El tema del espacio para el tema de pack() mencionado en dibujar() y el substring para eliminar el ultimo "+ "
				lblPrecio.setText(nuevo.equals("") ? " " : nuevo.substring(0, nuevo.length()-2));
			}
		}
		
	}
}
