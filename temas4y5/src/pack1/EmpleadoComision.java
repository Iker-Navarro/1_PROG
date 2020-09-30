package pack1;

public class EmpleadoComision extends Empleado {
	double ventas, comision;
	
	EmpleadoComision(double ventas, double comision, String nombre, char categoria, int horasTrabajadas) {
		super(nombre, categoria, horasTrabajadas);
		this.ventas = ventas< 0 ? 0 : ventas;
		
		if (comision < 0) 
			this.comision = 0;
		else if (comision > 100) 
			this.comision = 100;
		else
			this.comision = comision;
	}
	
	double salario(){
		return super.salario() + (ventas * comision/100);
	}
	
	void ver() {
		super.ver();
		System.out.println("Ventas: " + ventas);
		System.out.println("Comision: " + comision);
	}
	
}
