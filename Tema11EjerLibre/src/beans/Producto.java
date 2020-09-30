package beans;

public class Producto {
	private int idProd;
	private String nombre;
	private String descripcion;
	private double precio;
	private int stock;
	private String usuDuenio;
	
	public Producto(int idProd, String nombre, String descripcion, double precio, int stock, String usuDuenio) {
		this.idProd = idProd;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.usuDuenio = usuDuenio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		if (idProd != other.idProd)
			return false;
		return true;
	}



	public int getIdProd() {
		return idProd;
	}
	
	public String getNombre() {
		return nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public double getPrecio() {
		return precio;
	}

	public int getStock() {
		return stock;
	}
	
	public void setStock(int stock) {
		this.stock = stock;
	}
}
