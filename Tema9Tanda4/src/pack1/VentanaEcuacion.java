package pack1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class VentanaEcuacion extends JFrame implements ActionListener{
	
	private JTextField txtA, txtB, txtC;
	private JButton btnResolver; 
	private JLabel lblR1, lblR2; 
	
	public VentanaEcuacion() {
		dibujar();
		eventos();
		setVisible(true);
	}
	
	private void dibujar() {
		final Dimension DIMENSION_TXT = new Dimension(100,25);

		setTitle("Ecuación de segundo grado");
		setLayout(new BorderLayout());
		
		// NORTE
		JPanel pnlNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		txtA = new JTextField(7);
		pnlNorte.add(txtA);
		
		JLabel lbl1 = new JLabel("x2 +");
		pnlNorte.add(lbl1);
		
		txtB = new JTextField(7);
		pnlNorte.add(txtB);
		
		JLabel lbl2 = new JLabel("x +");
		pnlNorte.add(lbl2);
		
		txtC = new JTextField(7);
		pnlNorte.add(txtC);
		
		JLabel lbl3 = new JLabel("= 0");
		pnlNorte.add(lbl3);
		
		// CENTRO
		JPanel pnlCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btnResolver = new JButton("RESOLVER");
		pnlCentral.add(btnResolver);
		
		// SUR
		JPanel pnlSur = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JLabel lbl4 = new JLabel("Solución 1:");
		pnlSur.add(lbl4);
		
		lblR1 = new JLabel();
		lblR1.setPreferredSize(DIMENSION_TXT);
		lblR1.setBackground(Color.YELLOW);
		lblR1.setOpaque(true);
		pnlSur.add(lblR1);
		
		JLabel lbl5 = new JLabel("Solución 2:");
		pnlSur.add(lbl5);
		
		lblR2 = new JLabel();
		lblR2.setPreferredSize(DIMENSION_TXT);
		lblR2.setBackground(Color.YELLOW);
		lblR2.setOpaque(true);
		pnlSur.add(lblR2);
		
		//---------------------------
		
		add(pnlNorte, BorderLayout.NORTH);
		add(pnlCentral, BorderLayout.CENTER);
		add(pnlSur, BorderLayout.SOUTH);
		
		//---------------------------
		
		setLocationRelativeTo(null);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void eventos() {
		btnResolver.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		try {
			int a = Integer.parseInt(txtA.getText());
			int b = Integer.parseInt(txtB.getText());
			int c = Integer.parseInt(txtC.getText());
				
			SolucionCuadratica soluciones = solucion(a,b,c);
			
			lblR1.setText(""+soluciones.getR1());
			lblR2.setText(""+soluciones.getR2());
			
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(null, "Los coheficientes deben ser numéricos", "ERROR", JOptionPane.ERROR_MESSAGE);
			clear();
		} catch (RaizException e1) {
			JOptionPane.showMessageDialog(null, e1.getMessage(), "AVISO", JOptionPane.INFORMATION_MESSAGE);
			clear();
		}
		
	}
	
	private void clear() {
		txtA.setText("");
		txtB.setText("");
		txtC.setText("");
		
		lblR1.setText("");
		lblR2.setText("");
	}
	
	private SolucionCuadratica solucion(int a, int b, int c) throws RaizException {
		if(a == 0)
			throw new RaizException("Error, la equación no es cuadrática");
		
		int interiorRaiz = b*b - 4*a*c;
		
		if(interiorRaiz < 0)
			throw new RaizException("Las soluciones de esta equación son imaginárias");
		
		int multiplicacion = 2*a;
		
		double r1 = (-b + Math.sqrt(interiorRaiz))/multiplicacion;
		double r2 = (-b - Math.sqrt(interiorRaiz))/multiplicacion;
		
		return new SolucionCuadratica(r1, r2);
	}
}
