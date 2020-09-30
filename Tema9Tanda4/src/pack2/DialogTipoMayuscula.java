package pack2;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DialogTipoMayuscula extends JDialog{
	
	private final static String[] OPCIONES_STR = {"CAMBIAR A MAYUSCULAS", "Cambiar a mayusculas", "Cambiar A Mayusculas"};
	
	private JRadioButton[] radOpciones;

	private TransformaTexto owner;
	
	public DialogTipoMayuscula(TransformaTexto owner) {
		this.owner = owner;
		
		dibujar();
		eventos();
	
	}
	
	private void dibujar() {
		
		
		setTitle("tipo de MAYUSCULAS");
		setLayout(new FlowLayout());
		setModal(true);
	
		JPanel pnlRadio = new JPanel(new GridLayout(3, 1));
		
		ButtonGroup grupo = new ButtonGroup();
		
		radOpciones = new JRadioButton[3];
		
		for (int i = 0; i < OPCIONES_STR.length; i++) {
			radOpciones[i] = new JRadioButton(OPCIONES_STR[i]);
			grupo.add(radOpciones[i]);
			pnlRadio.add(radOpciones[i]);
		}
		radOpciones[0].setSelected(true);
		
		pnlRadio.setBorder(BorderFactory.createTitledBorder("Elija tipo de mayusculas"));
		
		//------------------------
		
		add(pnlRadio);
		
		//------------------------
		
		setLocationRelativeTo(null);
		pack();
	}
	
	private void eventos() {
		EscuchadorRadio escuchador = new EscuchadorRadio(); 
		
		for (int i = 0; i < radOpciones.length; i++) {
			radOpciones[i].addActionListener(escuchador);
		}
	}
	
	private class EscuchadorRadio implements ActionListener{		
		@Override
		public void actionPerformed(ActionEvent e) {
			String opcion = ((JRadioButton)e.getSource()).getText();
			
			String contenidoActual = owner.contenidoTxtArea();
			String nuevoContenido = "";
			
			if(opcion.equals(OPCIONES_STR[0]))
				nuevoContenido = TransformaStrings.todoMayusculas(contenidoActual);
			else if (opcion.equals(OPCIONES_STR[1]))
				nuevoContenido = TransformaStrings.frasesMayusculas(contenidoActual);
			else if(opcion.equals(OPCIONES_STR[2]))
				nuevoContenido = TransformaStrings.palabrasMayusculas(contenidoActual);
			
			owner.modificarTxtArea(nuevoContenido);
			setVisible(false);
		}
	}
	
	public JRadioButton[] getRadOpciones() {
		return radOpciones;
	}
}
