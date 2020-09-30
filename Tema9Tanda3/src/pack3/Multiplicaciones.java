package pack3;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;

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
	private static final int N = 5;
	
	private JButton[] botones;
	private ArrayList<JLabel> labels; 
	private ArrayList<Integer> numerosFallados;
	
	public Multiplicaciones() {
		numerosFallados = new ArrayList<Integer>();
		
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
		
		ArrayList<Integer> numerosUtilizados = generarArrayNumeros();
		
		botones = new JButton[N];
		JPanel pnlButtons = new JPanel(new GridLayout(0,1));
		
		labels = new ArrayList<JLabel>();
		JPanel pnlLabels = new JPanel(new GridLayout(0,1));
		
		// Dimension que utilizarán todos los botones y labels
		Dimension dimensionElementos = new Dimension(50, 40);
		Icon iconoFallo = UIManager.getIcon("OptionPane.errorIcon");
		
		for (int i = 0; i < botones.length; i++) {
			botones[i] = new JButton(""+numerosUtilizados.get(i));
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
		
		addWindowListener(new GrabadorFallosListener(numerosFallados));
	}
	
	private static int aleatorio(int min, int max) {
		return (int) (Math.random()*(max - min + 1) + min);
	}
	
	private static ImageIcon redim (String fichImag, int ancho, int alto){
	            ImageIcon imIcon=new ImageIcon(fichImag);
	            Image im=imIcon.getImage();
	            Image im2= im.getScaledInstance(ancho, alto, 0);
	            return new ImageIcon(im2);
	}
	
	private static ArrayList<Integer> generarArrayNumeros() {
		final int LIMITE_INFERIOR = 2, LIMITE_SUPERIOR = 20;
		int n, diferencia = LIMITE_SUPERIOR - LIMITE_INFERIOR;
		
		if(N > diferencia)
			n = diferencia;
		else
			n = N;
		
		ArrayList<Integer> vuelta = new ArrayList<Integer>();
		for(int i = 0; i < n; i++) {
			int num;
			do {
				num = aleatorio(LIMITE_INFERIOR, LIMITE_SUPERIOR);
			}while(vuelta.contains(num));
			
			vuelta.add(num);
		}		
		
		ordenarArrayList(vuelta);
		
		return vuelta;
	}
	
	private static void ordenarArrayList(ArrayList<Integer> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			for (int j = i + 1; j < arrayList.size(); j++) {
				if(arrayList.get(i) > arrayList.get(j)) {
					int aux = arrayList.get(i);
					arrayList.set(i, arrayList.get(j));
					arrayList.set(j, aux);
				}
			}
		}
	}
	
	public class EscuchadorOperMultiplicar implements ActionListener{
		
		private int indice, fallos;
		
		public EscuchadorOperMultiplicar(int i) {
			indice = i;
			fallos = 0;
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
				else {
					fallos++;
					if(fallos == 3) {
						src.setEnabled(false);
						labels.get(indice).setIcon(null);
						numerosFallados.add(x);
					}
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
}