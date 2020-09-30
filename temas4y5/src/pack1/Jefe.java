package pack1;

public class Jefe extends Empleado {
	int nEmp;
	
	Jefe(int nEmp,String nombre, char categoria, int horasTrabajadas) {
		super(nombre, categoria, horasTrabajadas);
		this.nEmp = nEmp < 0 ? 0 : nEmp;
	}
	
	double salario() {
		return nEmp < 10 ? 2500 : 3000;
	}
	
	void ver(){
		super.ver();
		System.out.println("Trabajadores a su cargo: " + nEmp);
	}
}
