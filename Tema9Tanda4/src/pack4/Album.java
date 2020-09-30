package pack4;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import pack3.Imagen;
import pack3.PanelVisorImagenes;

public class Album extends JFrame{
	
	private PanelVisorImagenes pnlImg;
	
	private JList<Imagen> lstImg;
	private DefaultListModel<Imagen> mdlImg;
	
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnVaciar;
	
	public Album() {
		dibujar();
		eventos();
		
		setVisible(true);
	}
	
	private void dibujar() {
		setLayout(new BorderLayout());
	
		pnlImg = new PanelVisorImagenes("img");
		
		JPanel pnlDerecho = new JPanel(new BorderLayout(0,0));
		
		// OESTE
		btnAgregar = new JButton(">>>");
		
		// CENTRO
		
		mdlImg = new DefaultListModel<Imagen>(); 
		lstImg = new JList<Imagen>(mdlImg);
		
		JScrollPane scrollPane = new JScrollPane(lstImg, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED); 
		
		// ESTE
		JPanel pnlEste = new JPanel(new GridLayout(2, 1));
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setEnabled(false);
		
		btnVaciar = new JButton("VACIAR ALBUM");
		if(mdlImg.getSize() == 0)
			btnVaciar.setEnabled(false);
		pnlEste.add(btnEliminar);
		pnlEste.add(btnVaciar);
		
		pnlDerecho.add(btnAgregar, BorderLayout.WEST);
		pnlDerecho.add(scrollPane, BorderLayout.CENTER);
		pnlDerecho.add(pnlEste, BorderLayout.EAST);
		
		//------------------------------
		
		add(pnlImg, BorderLayout.CENTER);
		add(pnlDerecho, BorderLayout.EAST);
		
		//------------------------------
		
		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void eventos() {
		
		BotonesListenter escuchadorBtn = new BotonesListenter(); 
		
		btnAgregar.addActionListener(escuchadorBtn);
		btnEliminar.addActionListener(escuchadorBtn);
		btnVaciar.addActionListener(escuchadorBtn);
		
		lstImg.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				if(mdlImg.size() == 0) {
					btnEliminar.setEnabled(false);
					btnVaciar.setEnabled(false);
				}
				else {
					// comprobar que haya algo seleccionado para habilitar boton
					if(!((JList<Imagen>) arg0.getSource()).isSelectionEmpty())
						btnEliminar.setEnabled(true);
				}
			}
		});

		// Eventos de apertura y cerrado de ventana
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				String nomUsuario = JOptionPane.showInputDialog ("Cual es tu nombre?");
				
				if (nomUsuario == null)
					return;
				
				String archivo = nomUsuario + "_album.obj";
				
				File f = new File(archivo);
				
				if(f.exists()) {
					try {
						ObjectInputStream ois = new ObjectInputStream(new FileInputStream(f));
						Imagen[] contenido = (Imagen[]) ois.readObject();
						ois.close();
						if(contenido.length > 0)
							btnVaciar.setEnabled(true);
						for (Imagen img : contenido) {
							mdlImg.addElement(img);
						}
					} catch (Exception exc) {
						// log de error en lectura de archivo
					}
				}
				
				super.windowOpened(e);
			}
			
			@Override
			public void windowClosing(WindowEvent e) {
				String input = JOptionPane.showInputDialog ("Introduce tu nombre para guardar el Album creado");
				
				if(input == null || input.equals(""))
					return;
				
				String nomArchivo = input + "_album.obj";
				
				File f = new File(nomArchivo);
				
				if(f.exists()) {
					int opcion = JOptionPane.showConfirmDialog(null, "Se sobreescribirá la versión anterior", "AVISO", JOptionPane.YES_NO_OPTION);
					System.out.println(opcion);
					if(opcion == JOptionPane.NO_OPTION)
						return;	
				}
				try {
					ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nomArchivo));
					
					Imagen[] contenido = new Imagen[mdlImg.size()];
					for(int i = 0; i < mdlImg.getSize(); i++) {
						contenido[i] = mdlImg.getElementAt(i);
					}
					
					oos.writeObject(contenido);
					
					oos.close();
				} catch (IOException e1) {
					// log de error en escritura de archivo
				}
				
				super.windowClosed(e);
			}
		});
	}
	
	private class BotonesListenter implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = ((JButton) e.getSource());
			
			if(source == btnAgregar) {
				Imagen i = pnlImg.imgSeleccionada();
				if(!mdlImg.contains(i)) {
					mdlImg.addElement(i);
					btnVaciar.setEnabled(true);
				}
			}
			else if(source == btnEliminar) {
				mdlImg.removeElementAt(lstImg.getSelectedIndex());
				btnEliminar.setEnabled(false);
			}
			else if(source == btnVaciar) {
				mdlImg.removeAllElements();
				btnVaciar.setEnabled(false);
				
			}
			
		}
	}
}
