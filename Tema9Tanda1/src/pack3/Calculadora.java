package pack3;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

public class Calculadora extends JFrame{
	
	private static final String[] SISTEMAS_NUMERICOS = {"Decimal", "Binario", "Octal", "Hexadecimal"};   
	private static final char[] OPERACIONES = {'+', '-', 'X', '/', '='};
	
	private JLabel lblResultado;
	private JButton[] numeros;
	private ArrayList<JRadioButton> numberSystems;
	private JButton[] operaciones;
	
	Calculadora(){
		setTitle("Calculadora");
		setLayout(new BorderLayout());
		
		// Panel Norte
		lblResultado = new JLabel("0", SwingConstants.RIGHT);
		lblResultado.setPreferredSize(new Dimension(-1, 75));
		lblResultado.setBackground(Color.YELLOW);
		lblResultado.setOpaque(true);
		lblResultado.setFont(new Font("Serif", Font.PLAIN, 30));

		
		// Panel Oeste
		JPanel panelOeste = new JPanel();
		BoxLayout bl = new BoxLayout(panelOeste, BoxLayout.Y_AXIS);
		panelOeste.setLayout(bl);
		panelOeste.setPreferredSize(new Dimension(150, -1));
		
		ButtonGroup grupo = new ButtonGroup();
		
		numberSystems = new ArrayList<JRadioButton>();
		for (int i = 0; i < SISTEMAS_NUMERICOS.length; i++) {
			numberSystems.add(new JRadioButton(SISTEMAS_NUMERICOS[i]));
			panelOeste.add(numberSystems.get(i));
			grupo.add(numberSystems.get(i));
		}
		
		// Panel central
		JPanel panelCentral = new JPanel();
		panelCentral.setLayout(new GridLayout(0,3));
		numeros = new JButton[10];
		for (int i = 0; i < 10; i++) {
			numeros[i] = new JButton(""+i);
			panelCentral.add(numeros[i]);
		}
		
		JPanel panelSur = new JPanel();
		panelSur.setLayout(new FlowLayout(FlowLayout.CENTER));
		operaciones = new JButton[OPERACIONES.length];
		
		for (int i = 0; i < operaciones.length; i++) {
			operaciones[i] = new JButton(""+OPERACIONES[i]);
			panelSur.add(operaciones[i]);	
		}
		
		//-------------------------------------------------------
		
		add(lblResultado, BorderLayout.NORTH);
		add(panelOeste, BorderLayout.WEST);
		add(panelCentral, BorderLayout.CENTER);
		add(panelSur, BorderLayout.SOUTH);
			
		//-------------------------------------------------------
		
		
		setSize(550, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		Calculadora c = new Calculadora();
	}
}
