package pack3;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GrabadorFallosListener extends WindowAdapter{
	
	private ArrayList<Integer> numerosFallados;
	
	public GrabadorFallosListener(ArrayList<Integer> fallados) {
		numerosFallados = fallados;
	}
	
	@Override
	public void windowClosing(WindowEvent arg0) {
		if(numerosFallados.size()>0) {
			String nombre;
			do {
				nombre = JOptionPane.showInputDialog("Se creará un ficeho con los números que tienes que practicar\nIntroduce tu nombre:");
			}while(nombre == null || nombre.equals(""));
			
			String nomFich = nombre + "_fallos.txt";
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter(nomFich));
				bw.write("Numeros fallados:\n");
				for (Integer num : numerosFallados) {
					bw.write(num + "\n");
				}
				bw.close();
			} catch (IOException e) {
				//Problema en la creación del documento
			}
		}
	}
}
