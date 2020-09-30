package pack3;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelVisorImagenes extends JPanel{
	
	private String ruta;
	
	private HashSet<String> extensiones;
	private Imagen[] todasImagenes;
	
	private JCheckBox[] checks;
	private JComboBox<Imagen> cmbImagenes;
	private DefaultComboBoxModel<Imagen> mdlImagenes;
	private JLabel lblImagenes;
	
	public PanelVisorImagenes(String ruta) {
		this.ruta = ruta;
		if(obtenerImgYExt()) {
			dibujar();
			eventos();
		}else {
			System.out.println("Error con la ruta o contenido de la misma");
		}	
	}
	
	private void dibujar() {
		
		setLayout(new BorderLayout());
		
		JPanel pnlNorte = new JPanel();
		BoxLayout bl = new BoxLayout(pnlNorte, BoxLayout.Y_AXIS);
		pnlNorte.setLayout(bl);
		
		// CHECKS
		JPanel pnlNorteChecks =  new JPanel(new FlowLayout(FlowLayout.CENTER));
		checks = new JCheckBox[extensiones.size()];
		
		int i = 0;
		for (String extension : extensiones) {
			checks[i] = new JCheckBox(extension);
			checks[i].setSelected(true);
			pnlNorteChecks.add(checks[i]);
			i++;
		}
		pnlNorte.add(pnlNorteChecks);
		
		// COMBO
		mdlImagenes = new DefaultComboBoxModel<Imagen>(todasImagenes);
		
		cmbImagenes = new JComboBox<Imagen>(mdlImagenes);
		
		pnlNorte.add(cmbImagenes);
		
		// CENTRO
		
		JPanel pnlCentro = new JPanel(new FlowLayout(FlowLayout.CENTER));
		pnlCentro.setPreferredSize(new Dimension(400,200));
		
		lblImagenes = new JLabel("ELIJE IMAGEN DEL COMBO");
		
		pnlCentro.add(lblImagenes);
		//------------------------------------
		
		add(pnlNorte, BorderLayout.NORTH);
		add(pnlCentro, BorderLayout.CENTER);
		
		//------------------------------------		
	}
	
	private void eventos() {
		//eventos de selección de imágen
		cmbImagenes.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Imagen i = (Imagen) cmbImagenes.getSelectedItem();
				// si el combo tiene marcada la opción -1 es porque no tiene ninguna imágen
				if(i == null) {
					lblImagenes.setIcon(null);
					lblImagenes.setText("ELIJE IMAGEN DEL COMBO");
				}else{
					lblImagenes.setText("");
					lblImagenes.setIcon(redim(ruta + "/" + i.getNombre(), getBounds()));
				}
			}
		});
		
		// Crear evento para los check
		ActionListener escuchadorChecks = new ActionListener() {		
			@Override
			public void actionPerformed(ActionEvent e) {
				JCheckBox origen = (JCheckBox) e.getSource();
				String ext = origen.getText();
				// Añadir imágenes al mdl
				if(origen.isSelected()) {
					ArrayList<Imagen> nuevas = imagenesDeExtension(ext);
					for (Imagen imagen : nuevas) {
						mdlImagenes.addElement(imagen);
					}
				}
				// Quitar imágenes del mdl
				else {
					// Eliminar elementos con misma extension que la marcada en el check
					for(int i = 0; i < mdlImagenes.getSize();) {
						if(mdlImagenes.getElementAt(i).getExtension().equalsIgnoreCase(ext))
							mdlImagenes.removeElementAt(i);
						else 
							i++;
					}
					// Si es el ultimo marcar indice -1 y borrar todo
					if(mdlImagenes.getSize() == 0) {
						cmbImagenes.setSelectedIndex(-1);
						mdlImagenes.removeAllElements();
					}
				}
			}
		};
		
		// Añadir escuchador a todos los checks
		for (JCheckBox check : checks) {
			check.addActionListener(escuchadorChecks);
			
		}
	}
	
	private ArrayList<Imagen> imagenesDeExtension(String extension){
		ArrayList<Imagen> vuelta = new ArrayList<Imagen>();
		
		for (Imagen imagen : todasImagenes) {
			if(imagen.getExtension().equalsIgnoreCase(extension))
				vuelta.add(imagen);
		}
		
		return vuelta;
	}
	
	private boolean obtenerImgYExt(){
		// comprobar que ruta dada es correcta
		File f = new File(ruta);
		
		if(!f.exists() || !f.isDirectory()) {
			return false;
		}
		
		extensiones = new HashSet<String>();
		
		// Guardar contenido de la ruta
		String[] contenido = f.list();
				
		// Llenar contenidoValido con todas las imagenes de la ruta (y guardar extension)
		ArrayList<Imagen> contenidoValido = new ArrayList<Imagen>(); 		
		for (String archivo : contenido) {
			if(Imagen.esImagen(ruta + "/" + archivo)) {
				File imagen = new File(ruta + "/" + archivo);
				Imagen i = new Imagen(archivo, imagen.length());
				contenidoValido.add(i);
				extensiones.add(i.getExtension());
			}
		}
		
		if(contenidoValido.size()==0)
			return false;
		
		// Llenar array propiedad
		todasImagenes = new Imagen[contenidoValido.size()];
		for (int i = 0; i < todasImagenes.length; i++) {
			todasImagenes[i] = contenidoValido.get(i);
		}
		
		return true;
		
	}
	
	private static ImageIcon redim (String fichImag, Rectangle2D r){
	            ImageIcon imIcon=new ImageIcon(fichImag);
	            Image im=imIcon.getImage();
	            Image im2= im.getScaledInstance((int) r.getWidth(), (int) r.getHeight(), 0);
	            return new ImageIcon(im2);
	}
	
	/*public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		PanelVisorImagenes pan = new PanelVisorImagenes("img");
		
		frame.add(pan);
		
		frame.pack();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);	
	}*/

	public Imagen imgSeleccionada() {
		return (Imagen) cmbImagenes.getSelectedItem();
	}
}
