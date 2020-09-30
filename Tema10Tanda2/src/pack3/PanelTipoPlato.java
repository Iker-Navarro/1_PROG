package pack3;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class PanelTipoPlato extends JPanel{
	
	private Plato[] platos;
	private JRadioButton[] radElementos;
	private JLabel lblPrecio;
	
	public PanelTipoPlato(String tipo, GestorXML gestor) {	
		
		platos = gestor.damePlatos(tipo);
		radElementos = new JRadioButton[platos.length];
		
		dibujar(tipo);
		eventos();
	}
	
	public void dibujar(String tipo) {				
		BoxLayout bl = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(bl);
		
		JLabel lblTit = new JLabel("ELIJE UN " + tipo.toUpperCase());
		lblTit.setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLUE));
		
		add(lblTit);
		
		ButtonGroup grupo = new ButtonGroup();
		
		for(int i = 0; i < platos.length; i++) {
			radElementos[i] = new JRadioButton(platos[i].getNombre()); 
			grupo.add(radElementos[i]);
			add(radElementos[i]);
		}
		
		// Es un espacio y no totalmente vacio para que al hacer pack() deje espacio para el precio
		lblPrecio = new JLabel(" ");
		add(lblPrecio);
	}
	
	private void eventos() {		
		for(int i = 0; i < radElementos.length; i++) {
			radElementos[i].addActionListener(new EscuchadorPlato(platos[i]));
		}
	}
	
	public Plato obtenerSeleccionado() {
		for (int i = 0; i < radElementos.length; i++) {
			if(radElementos[i].isSelected())
				return platos[i];
		}
		return null;
	}
	
	public class EscuchadorPlato implements ActionListener{
		
		private Plato plato;
		
		public EscuchadorPlato(Plato seleccionado) {
			this.plato = seleccionado;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			lblPrecio.setText("PRECIO: " + plato.getPrecio() + "€");
		}
		
	}
}
