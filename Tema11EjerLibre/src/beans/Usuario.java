package beans;

import java.util.Date;

public class Usuario {
	private String usuario;
	private String nombre;
	private String apellidos;
	private Date fechaAlta;
	private String password;
	private String tipo;
	
	public Usuario(String usuario, String nombre, String apellidos, Date fechaAlta, String password, String tipo) {
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fechaAlta = fechaAlta;
		this.password = password;
		this.tipo = tipo;
	}
	
	public String getUsuario() {
		return usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public String getPassword() {
		return password;
	}

	public String getTipo() {
		return tipo;
	}
	
	
	
}
