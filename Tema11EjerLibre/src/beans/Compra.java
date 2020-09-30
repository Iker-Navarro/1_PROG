package beans;

public class Compra {
	private String nombre_producto;
	private int id_producto;
	private int id_transaccion;
	private int cantidad;
	private double precio;
	

	public Compra(String nombre_producto, int id_producto, int id_transaccion, int cantidad, double precio) {
		this.nombre_producto = nombre_producto;
		this.id_producto = id_producto;
		this.id_transaccion = id_transaccion;
		this.cantidad = cantidad;
		this.precio = precio;
	}

	public String getNombre_producto() {
		return nombre_producto;
	}
	
	public int getId_producto() {
		return id_producto;
	}

	public int getId_transaccion() {
		return id_transaccion;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public double getPrecio() {
		return precio;
	}
}
