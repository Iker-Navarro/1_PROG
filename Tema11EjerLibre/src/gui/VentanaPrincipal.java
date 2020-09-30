package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import beans.Compra;
import beans.Producto;
import beans.Transaccion;
import beans.Usuario;
import gestorBD.GestorBD;

public class VentanaPrincipal extends JFrame{
	private GestorBD bbdd;
	
	private Usuario usuarioActual;
	
	private JLabel lblUsuario;
	
	private PanelLogin pnlLogin;
	private PanelRegistro pnlRegistro;
	private JPanel pnlMenu;
	private PanelTienda pnlTienda;
	private PanelCarro pnlCarro;
	private PanelTransacciones pnlTransacciones;
	
	private JPanel pnlPrincipal;
	
	public VentanaPrincipal() {
		pnlLogin = new PanelLogin(this);
		
		bbdd = new GestorBD();
		
		dibujar();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void dibujar() {
		setLayout(new BorderLayout());
		Color cTit = new Color(232, 179, 111);
		
		JPanel pnlNorte = new JPanel(new GridBagLayout());
		pnlNorte.setBackground(cTit);
		pnlNorte.setPreferredSize(new Dimension(100, 50));
		pnlNorte.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, Color.BLACK));
		
		JLabel lblTitulo = new JLabel("TIENDA");
		lblTitulo.setFont(new Font("SansSerif", Font.BOLD, 25));
		
		pnlNorte.add(lblTitulo);
		
		pnlPrincipal = new JPanel();
		pnlPrincipal.add(pnlLogin);
		
		JPanel pnlSur = new JPanel(new GridBagLayout());
		pnlSur.setBackground(cTit);
		pnlSur.setPreferredSize(new Dimension(100, 20));
		pnlSur.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
		lblUsuario = new JLabel(" ");
		pnlSur.add(lblUsuario);
		
		add(pnlNorte, BorderLayout.NORTH);
		add(pnlPrincipal, BorderLayout.CENTER);
		add(pnlSur, BorderLayout.SOUTH);
		
		pack();
		setLocationRelativeTo(null);
	}
	
	public void movRegistro() {
		if(pnlRegistro == null)
			pnlRegistro = new PanelRegistro(this);
		
		cambiarPanel(pnlRegistro);
	}
	
	public void movLogin() {
		cambiarPanel(pnlLogin);
	}
	
	public void movTienda() {			
		cambiarPanel(pnlTienda);	
	}
	
	public void movTransacciones() {
		cambiarPanel(pnlTransacciones);	
	}
	
	public void iniciarSesion() {
		switch (usuarioActual.getTipo()) {
		case "USER":
			pnlTienda = new PanelTienda(this);
			pnlMenu = new PanelMenuUsuario(this);
			pnlCarro = new PanelCarro(this);
			pnlTransacciones = new PanelTransacciones(this);
			movMenuPrincipal();
			lblUsuario.setText("Identificado como " + usuarioActual.getUsuario());
			break;
		case "PROV":
			//TODO
			movMenuPrincipal();
			break;
		case "ADMIN":
			//TODO
			movMenuPrincipal();
			break;
		default:
			break;
		}
	}
	
	public void movMenuPrincipal() {
		cambiarPanel(pnlMenu);
	}
	
	public void movCarro() {
		// TODO cambiar esto
		// pnlCarro = new PanelCarro(this);
		cambiarPanel(pnlCarro);
	}
	
	private void cambiarPanel(JPanel pnl) {
		pnlPrincipal.removeAll();
		pnlPrincipal.add(pnl);
		pnlPrincipal.revalidate();
		pnlPrincipal.repaint();
		pack();
	}
	
	public void regenerarContenidoCarro() {
		pnlCarro.regenerarContenidoCarro();
	}
	
	public void regenerarContenidoTienda(Producto p, int cantidad) {
		pnlTienda.regenerarContenidoTienda(p, cantidad);
	}
	
	public void aniadirNuevaTransaccion(Transaccion t, ArrayList<Compra> compras) {
		pnlTransacciones.aniadirTransaccion(t, compras);
	}
	
	public GestorBD getBbdd() {
		return bbdd;
	}

	public void vaciarEtiquetaUsuario() {
		lblUsuario.setText("");
	}
	
	public void setUsuarioActual(Usuario usuarioActual) {
		this.usuarioActual = usuarioActual;
	}
	
	public Usuario getUsuarioActual() {
		return usuarioActual;
	}
}
