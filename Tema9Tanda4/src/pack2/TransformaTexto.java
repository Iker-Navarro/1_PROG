package pack2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class TransformaTexto extends JFrame{
	
	private JTextArea txtContenido;
	private JButton btnVaciar, btnMayusculas, btnInvertir;
	private DialogTipoMayuscula dialogMayus;
	
	public TransformaTexto() {
	
		dibujar();
		eventos();
		
		setVisible(true);
	}
	
	private void dibujar() {
		
		setTitle("Modificar Texto");
		setLayout(new BorderLayout());

		dialogMayus = new DialogTipoMayuscula(this);
		dialogMayus.setVisible(false);
		
		// NORTE
		JPanel pnlNorte = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		JLabel lblTitulo = new JLabel("Escriba aqui el texto:");
		
		pnlNorte.add(lblTitulo);
		
		// CENTRO
		JPanel pnlCentral = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		txtContenido = new JTextArea();
		
		JScrollPane scrPanel = new JScrollPane(txtContenido , JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrPanel.setPreferredSize(new Dimension(250, 100));
		
		pnlCentral.add(scrPanel);
		
		// SUR
		JPanel pnlSur = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btnVaciar = new JButton("VACIAR");
		btnMayusculas = new JButton("MAYUSCULAS");
		btnInvertir = new JButton("INVERTIR");
		
		pnlSur.add(btnVaciar);
		pnlSur.add(btnMayusculas);
		pnlSur.add(btnInvertir);
		
		//--------------------------------
		
		add(pnlNorte, BorderLayout.NORTH);
		add(pnlCentral, BorderLayout.CENTER);
		add(pnlSur, BorderLayout.SOUTH);
		
		//--------------------------------
		
		setLocationRelativeTo(null);
		pack();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void eventos() {
		btnVaciar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtContenido.setText("");
			}
		});
		
		btnInvertir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				txtContenido.setText(TransformaStrings.invertirString(txtContenido.getText()));
			}
		});
		
		btnMayusculas.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogMayus.getRadOpciones()[0].setSelected(true);
				dialogMayus.setVisible(true);
			}
		});
	}
	
	public String contenidoTxtArea() {
		return txtContenido.getText();
	}
	
	public void modificarTxtArea(String nuevoContenido) {
		txtContenido.setText(nuevoContenido);
	}
}
