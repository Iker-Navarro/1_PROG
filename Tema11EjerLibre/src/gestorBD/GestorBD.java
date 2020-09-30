package gestorBD;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;

import beans.Compra;
import beans.Producto;
import beans.Transaccion;
import beans.Usuario;

public class GestorBD {
	private Connection con;
	
	public GestorBD() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/tema11ejerlibre","root", "admin");
			
		} catch (ClassNotFoundException e) {
			System.out.println("Error de driver");
		} catch (SQLException e) {
			System.out.println("No se puede conectar a la BBDD");
		}
	}
	
	public boolean usuarioDisponible(String username) {
		try {
			Statement st = con.createStatement(); 
			ResultSet rs =  st.executeQuery("select count(*) from usuarios where USUARIO = \"" + username + "\"");
			
			rs.next();
			if(rs.getInt(1) == 0)
				return true;
			return false;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	public Usuario accesoValido(String username, String pass) {
		try {
			Statement st = con.createStatement(); 
			ResultSet rs =  st.executeQuery("select * from usuarios where USUARIO = \"" + username + "\" and PASSWORD = \"" + pass + "\"");
			
			if(rs.next()) {
				return new Usuario(
						rs.getString("USUARIO"),
						rs.getString("NOMBRE"),
						rs.getString("APELLIDOS"),
						rs.getDate("FECHA_ALTA"),
						rs.getString("PASSWORD"),
						rs.getString("TIPO"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	public boolean darDeAlta(Usuario u) {
		try {
			SimpleDateFormat formateador = new SimpleDateFormat("YYYY-MM-dd");
			String strFecha = formateador.format(u.getFechaAlta());
			
			String sentencia = ""
					+ "INSERT INTO USUARIOS "
					+ "(USUARIO, NOMBRE, APELLIDOS, FECHA_ALTA, PASSWORD, TIPO) "
					+ "VALUES (\"" 
					+ u.getUsuario() + "\", \""
					+ u.getNombre() + "\", \""
					+ u.getApellidos() + "\", '"
					+ strFecha + "', \""
					+ u.getPassword() + "\", \""
					+ u.getTipo() + "\")";
			
			//System.out.println(sentencia);
			Statement st = con.createStatement(); 
			int n =  st.executeUpdate(sentencia);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
		
	}
	
	public ArrayList<Producto> todosProductos(){
		try {
			Statement st = con.createStatement(); 
			ResultSet rs =  st.executeQuery("select * from productos where stock > 0");
			ArrayList<Producto> vuelta = new ArrayList<Producto>();
			while(rs.next()) {
				vuelta.add(new Producto(rs.getInt("ID_PROD"), rs.getString("NOMBRE_PROD"), rs.getString("DESC_PROD"), rs.getDouble("PRECIO"), rs.getInt("STOCK"), rs.getString("ID_DUENIO")));
			}
			return vuelta;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private int transaccionAbierta(Usuario u) {
		try {
			String sentencia = "SELECT ID_TRANSACCION, FINALIZADA FROM TRANSACCIONES WHERE ID_USUARIO = \"" + u.getUsuario() + "\"  ORDER BY ID_TRANSACCION DESC LIMIT 1";
			Statement st = con.createStatement(); 
			ResultSet rs =  st.executeQuery(sentencia);
			
			if(rs.next()) {
				if(rs.getBoolean("FINALIZADA"))
					return 0;
				else
					return rs.getInt("ID_TRANSACCION");
			}	
			else 
				return 0;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return -1;
		}
	}
	
	private int cantidadYaAniadida(int id_transaccion, Producto p) {
		try {
			String sentencia = "SELECT CANTIDAD FROM COMPRAS WHERE ID_TRANSACCION = " + id_transaccion + " AND ID_PRODUCTO = " + p.getIdProd();
			Statement st = con.createStatement(); 
			ResultSet rs =  st.executeQuery(sentencia);
			if(rs.next())
				return rs.getInt("CANTIDAD");
			else
				return 0;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return 0;
		}
	}
	
	public void aniadirACarro(Usuario usuarioActual, Producto p, int cantidadComprada) {
		try {
			Statement st = con.createStatement();
			String sentencia;
			int transaccionAbierta = transaccionAbierta(usuarioActual);
			if(transaccionAbierta == 0) {
				sentencia = "INSERT INTO TRANSACCIONES (ID_USUARIO, FINALIZADA) VALUES (\""
						+ usuarioActual.getUsuario() + "\", "
						+ " FALSE)";
				int n =  st.executeUpdate(sentencia);
				transaccionAbierta = transaccionAbierta(usuarioActual);
			}
			// EN ESTE PUNTO SABEMOS QUE EXISTE UNA TRANSACCION ABIERTA
			int cantidadActual = cantidadYaAniadida(transaccionAbierta, p);
			if(cantidadActual == 0) {
				sentencia = "INSERT INTO COMPRAS (ID_TRANSACCION, ID_PRODUCTO, CANTIDAD) VALUES ("
						+ transaccionAbierta + ", "
						+ p.getIdProd() + ", "
						+ cantidadComprada + ")";
			}
			else {
				sentencia = "UPDATE COMPRAS SET CANTIDAD = " + (cantidadActual + cantidadComprada) +
						" WHERE ID_TRANSACCION = " + transaccionAbierta +
						" AND ID_PRODUCTO = " + p.getIdProd();
			}
			int n =  st.executeUpdate(sentencia);

		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void actualizarStock(int id_prod, int nuevoStock) {	
		try {			
			Statement st = con.createStatement();
			String sentencia = "UPDATE PRODUCTOS SET STOCK = " + nuevoStock + " WHERE ID_PROD = " + id_prod;
					
			// System.out.println(sentencia);
			int n =  st.executeUpdate(sentencia);
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Compra> obtenerDatosCarro(Usuario u) {
		int transaccionAbierta = transaccionAbierta(u);
		if(transaccionAbierta > 0) {
			try {			
				ArrayList<Compra> compras = new ArrayList<Compra>();
				Statement st = con.createStatement();
				String sentencia = "SELECT NOMBRE_PROD, ID_PRODUCTO, COMPRAS.CANTIDAD, PRECIO FROM PRODUCTOS, COMPRAS WHERE ID_PRODUCTO = ID_PROD AND ID_TRANSACCION = " + transaccionAbierta ;
						
				// System.out.println(sentencia);
				ResultSet rs =  st.executeQuery(sentencia);
				while(rs.next()) {
					compras.add(new Compra(rs.getString("NOMBRE_PROD"), rs.getInt("ID_PRODUCTO"), transaccionAbierta, rs.getInt("CANTIDAD"), rs.getDouble("PRECIO")));
				}
				return compras;
				
			}catch (SQLException e) {
				System.out.println(e.getMessage());
			}
		}
		return null;
	}
	
	public void borrarCompra(Compra compra) {
		try {			
			Statement st = con.createStatement();
			String sentencia = "DELETE FROM COMPRAS WHERE ID_PRODUCTO = " + compra.getId_producto() + " AND ID_TRANSACCION = " + compra.getId_transaccion();
			int n =  st.executeUpdate(sentencia);
			
			sentencia = "SELECT COUNT(*) FROM COMPRAS WHERE ID_TRANSACCION = " + compra.getId_transaccion();
			ResultSet rs =  st.executeQuery(sentencia);
			rs.next();
			
			if(rs.getInt(1) == 0) {
				// si ya no quedan elementos en la transaccion la eliminamos
				sentencia = "DELETE FROM TRANSACCIONES WHERE ID_TRANSACCION = " + compra.getId_transaccion();
				n =  st.executeUpdate(sentencia);
			}
			
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Producto obtenerProducto(int id_prod) {
		try {
			Statement st = con.createStatement(); 
			ResultSet rs =  st.executeQuery("select * from productos where id_prod = " + id_prod);
			rs.next();
			
			return new Producto(rs.getInt("ID_PROD"), rs.getString("NOMBRE_PROD"), rs.getString("DESC_PROD"), rs.getDouble("PRECIO"), rs.getInt("STOCK"), rs.getString("ID_DUENIO"));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public Transaccion realizarCompra(Usuario usuario) {
		try {	
			generarFactura(usuario);
			
			Statement st = con.createStatement();
			
			String sentencia = "SELECT ID_TRANSACCION FROM TRANSACCIONES WHERE ID_USUARIO = \"" + usuario.getUsuario() + "\" AND FINALIZADA = FALSE";
			ResultSet rs = st.executeQuery(sentencia);
			rs.next();
			int id_transaccion = rs.getInt("ID_TRANSACCION");
			
			sentencia = "UPDATE TRANSACCIONES SET FECHA = NOW(), FINALIZADA = TRUE WHERE ID_USUARIO = \"" + usuario.getUsuario() + "\" AND FINALIZADA = FALSE";
			int n =  st.executeUpdate(sentencia);
			
			return new Transaccion(id_transaccion,new Date());
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	private void generarFactura(Usuario usuario) {
		try {	
			Statement st = con.createStatement();
			String sentencia = "SELECT * FROM COMPRAS WHERE ID_TRANSACCION IN (SELECT ID_TRANSACCION FROM TRANSACCIONES WHERE ID_USUARIO = \"" + usuario.getUsuario() + "\" AND FINALIZADA = FALSE) ";
			ResultSet rs =  st.executeQuery(sentencia);
			if(rs.next()) {
				int total = 0;
				SimpleDateFormat formateador = new SimpleDateFormat("dd/MM/YYYY");
				BufferedWriter bw = new BufferedWriter(new FileWriter("facturas/factura_" + rs.getInt("ID_TRANSACCION") + ".txt"));
				bw.write("FACTURA #" + rs.getInt("ID_TRANSACCION") + "\n\n");
				bw.write("CLIENTE: " + usuario.getNombre() + " " + usuario.getApellidos() + "\n");
				bw.write("FECHA: " + formateador.format(new Date()) + "\n");
				bw.write("-----------------------\n");
				bw.write("PRODUCTO\tCANTIDAD\tPRECIO\n");
				do {
					Producto p = obtenerProducto(rs.getInt("ID_PRODUCTO"));
					bw.write(p.getNombre() + "\t" + rs.getInt("CANTIDAD") + " unidades\t" + p.getPrecio() + "€\n");
					total += rs.getInt("CANTIDAD") * p.getPrecio();
				}while(rs.next());
				bw.write("-----------------------\n");
				bw.write("TOTAL \t" + total + "€\n");
				bw.write("-----------------------\n");
				bw.close();
			}
		}catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			// Problemas con el fichero generado
		}
	}
	
	public LinkedHashMap<Transaccion, ArrayList<Compra>> transaccionesUsuario(Usuario u){
		LinkedHashMap<Transaccion, ArrayList<Compra>> transacciones = new LinkedHashMap<Transaccion, ArrayList<Compra>>();
		try {	
			Statement st = con.createStatement();
			String sentencia = "SELECT ID_TRANSACCION, FECHA FROM TRANSACCIONES WHERE ID_USUARIO = \"" + u.getUsuario() + "\" AND FINALIZADA = TRUE";
			ResultSet rs =  st.executeQuery(sentencia);
			
			while(rs.next()) {
				ArrayList<Compra> comprasEnTransaccion = comprasEnTransaccion(rs.getInt("ID_TRANSACCION"));
				transacciones.put(new Transaccion(rs.getInt("ID_TRANSACCION"), rs.getDate("FECHA")), comprasEnTransaccion);
			}
			return transacciones;
		}catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public ArrayList<Compra> comprasEnTransaccion(int id_transaccion){
		try {
			ArrayList<Compra> comprasEnTransaccion = new ArrayList<Compra>();
			String sentencia = "SELECT NOMBRE_PROD, ID_PROD, CANTIDAD, PRECIO FROM PRODUCTOS, COMPRAS WHERE ID_TRANSACCION = " + id_transaccion + " AND ID_PRODUCTO = ID_PROD";
			
			Statement st = con.createStatement();
			ResultSet rs =  st.executeQuery(sentencia);
			while(rs.next()) {
				comprasEnTransaccion.add(new Compra(rs.getString("NOMBRE_PROD"), rs.getInt("ID_PROD"), id_transaccion, rs.getInt("CANTIDAD"), rs.getInt("PRECIO")));
			}
			
			return comprasEnTransaccion;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}
