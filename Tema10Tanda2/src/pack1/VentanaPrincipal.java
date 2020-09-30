package pack1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class VentanaPrincipal extends JFrame{
	private static final HoraConsulta H_INICIO = new HoraConsulta(9, 0), H_FIN = new HoraConsulta(15, 30);
	
	private GestorXML gestor;
	
	private JList<HoraConsulta> lstHoras;
	private JComboBox<String> cmbMedicos;
	private JTextField txtPaciente;
	private JButton btnAniadir;
	
	public VentanaPrincipal(String nomFichGestor) {
			
		File f = new File(nomFichGestor);
		if(!f.exists())
			gestor = new GestorXML(true, f);
		else {
			int opcion = JOptionPane.showConfirmDialog(null, "¿Desea resetearel fichero de medicos?", "RESETEAR" ,JOptionPane.YES_NO_OPTION);
			
			if(opcion == JOptionPane.YES_OPTION)
				gestor = new GestorXML(true, f);
			else
				gestor = new GestorXML(false, f);
				
		}		
		dibujar();
		
		eventos();
		
		setVisible(true);
	}
	
	private void dibujar() {
		
		setTitle("Consultas Médicas");
		
		setLayout(new BorderLayout(40, 10));
		getRootPane().setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		
		// WEST
		JPanel pnlOeste = new JPanel(new BorderLayout(10, 10));
		
		JLabel lblTituloOeste = new JLabel("Elije una o más horas");
		
		HoraConsulta[] consultas = HoraConsulta.consultasEntre(H_INICIO, H_FIN, 30);
		
		lstHoras = new JList<HoraConsulta>(consultas);
		
		JScrollPane scrllLista = new JScrollPane(lstHoras, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		pnlOeste.add(lblTituloOeste, BorderLayout.NORTH);
		pnlOeste.add(scrllLista, BorderLayout.CENTER);
		
		
		// EAST
		
		JPanel pnlEste = new JPanel(new GridLayout(0, 1));
		
		JLabel lblMedicos = new JLabel("Elije un médico");
		pnlEste.add(lblMedicos);
		
		cmbMedicos = new JComboBox<String>(gestor.todosMedicos());
		pnlEste.add(cmbMedicos);
		
		JLabel lblPaciente = new JLabel("Nombre del Paciente");
		pnlEste.add(lblPaciente);
		
		txtPaciente = new JTextField();
		txtPaciente.setPreferredSize(new Dimension(200, 50));
		pnlEste.add(txtPaciente);
		
		// SOUTH
		
		JPanel pnlSur = new JPanel(new FlowLayout(FlowLayout.CENTER));
		
		btnAniadir = new JButton("AÑADIR");
		
		pnlSur.add(btnAniadir);
		
		//------------------------------
		
		add(pnlOeste, BorderLayout.WEST);
		add(pnlEste, BorderLayout.EAST);
		add(pnlSur, BorderLayout.SOUTH);
		
		//------------------------------

		pack();
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	private void eventos() {
		btnAniadir.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				List<HoraConsulta> horasConsultas = lstHoras.getSelectedValuesList();
				String paciente = txtPaciente.getText();
				if(horasConsultas.size() != 0 && !paciente.equals("")) {
					
					String medico = (String) cmbMedicos.getSelectedItem();
					ArrayList<HoraConsulta> asignacionesCorrectas = new ArrayList<HoraConsulta>(); 
					ArrayList<HoraConsulta> asignacionesIncorrectas = new ArrayList<HoraConsulta>(); 
					
					for (HoraConsulta horaConsulta : horasConsultas) {
						if(gestor.comprobarDisponibilidad(medico, horaConsulta)) {
							asignacionesCorrectas.add(horaConsulta);
							gestor.aniadirConsulta(medico, horaConsulta, paciente);
						}
						else {
							asignacionesIncorrectas.add(horaConsulta);
						}
					}
					
					// Mensaje de error
					if(asignacionesIncorrectas.size() != 0) {
						String horas = "";
						for (HoraConsulta horaConsulta : asignacionesIncorrectas) {
							horas += horaConsulta + "\n";
						}
						JOptionPane.showMessageDialog(
								null,
								"CONSULTAS NO ASIGNADAS: " + paciente + " - " + medico + "\n" + horas,
								"Asignación errónea",
								JOptionPane.ERROR_MESSAGE);
					}
					
					// Mensaje de asignación correcta
					if(asignacionesCorrectas.size() != 0) {
						String horas = "";
						for (HoraConsulta horaConsulta : asignacionesCorrectas) {
							horas += horaConsulta + "\n";
						}
						JOptionPane.showMessageDialog(
								null,
								"CONSULTAS ASIGNADAS: " + paciente + " - " + medico + "\n" + horas,
								"Asignación correcta",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});
	}
}
