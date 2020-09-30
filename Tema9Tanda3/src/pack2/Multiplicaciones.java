package pack2;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;

public class Multiplicaciones extends JFrame{
	
	private JButton[] botones;
	private ArrayList<JLabel> labels; 
	
	public Multiplicaciones() {
		dibujar();
		eventos();
		
		setVisible(true);
	}
	
	private void dibujar() {
		setTitle("Multiplying tables");
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
		
		// TITULO
		JLabel lblTitulo = new JLabel("Practise multiplying tables");
		lblTitulo.setAlignmentX(CENTER_ALIGNMENT);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		lblTitulo.setPreferredSize(new Dimension(400, 40));
		
		// PANEL PRINCIPAL
		JPanel pnlPrincipal = new JPanel(new GridLayout(0, 2, 30, 0));
		
		botones = new JButton[8];
		JPanel pnlButtons = new JPanel(new GridLayout(0,1));
		
		labels = new ArrayList<JLabel>();
		JPanel pnlLabels = new JPanel(new GridLayout(0,1));
		
		// Dimension que utilizarán todos los botones y labels
		Dimension dimensionElementos = new Dimension(50, 40);
		Icon iconoFallo = UIManager.getIcon("OptionPane.errorIcon");
		
		for (int i = 0; i < botones.length; i++) {
			botones[i] = new JButton(""+(i+2));
			botones[i].setPreferredSize(dimensionElementos);
			pnlButtons.add(botones[i]);
			
			JLabel lbl = new JLabel(iconoFallo);
			lbl.setPreferredSize(dimensionElementos);
			
			labels.add(lbl);
			
			pnlLabels.add(lbl);
		}
		
		pnlPrincipal.add(pnlButtons);
		pnlPrincipal.add(pnlLabels);
		
		
		// para centrar el pnlPrincipal en la ventana:
		JPanel contenedorPrincipal = new JPanel(new FlowLayout(FlowLayout.CENTER));
		contenedorPrincipal.add(pnlPrincipal);
		
		//--------------------------
		
		add(lblTitulo);
		add(contenedorPrincipal);
		
		//--------------------------
		
		setLocationRelativeTo(null);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void eventos() {
		for (int i = 0; i < botones.length; i++) {
			botones[i].addActionListener(new EscuchadorOperMultiplicar(i));
		}
		
	}
	
	private static int aleatorio(int min, int max) {
		return (int) (Math.random()*(max - min + 1) + min);
	}
	
	public class EscuchadorOperMultiplicar implements ActionListener{
		
		int indice;
		
		public EscuchadorOperMultiplicar(int i) {
			indice = i;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton src = (JButton) e.getSource();
			int x = Integer.parseInt(src.getText());
			int y = aleatorio(1, 9);
			
			String entrada = JOptionPane.showInputDialog(x + " X " + y + " =");
			if(entrada == null || entrada.equals(""))
				return;
			try {
				if(Integer.parseInt(entrada) == x*y) {
					labels.get(indice).setIcon(redim("img/check.png", 40, 40));
					src.setEnabled(false);
				}
			} catch (NumberFormatException ex) {
				Object[] opc= {"OK"};
				JOptionPane.showOptionDialog (
						null,
						"Introduce únicamente valores numéricos",
						"Error",
						JOptionPane.DEFAULT_OPTION,
						JOptionPane.WARNING_MESSAGE,
						null,
						opc, opc [0]);
			}
			
			
		}
		
	}
	private static ImageIcon redim (String fichImag, int ancho, int alto)
	{
	            ImageIcon imIcon=new ImageIcon(fichImag);
	            Image im=imIcon.getImage();
	            Image im2= im.getScaledInstance(ancho, alto, 0);
	            return new ImageIcon(im2);
	}
}