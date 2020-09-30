package pack1;

public class Empleado {
	final static int SAL_A = 20, SAL_B = 25, SAL_C = 30;
	
	String nombre;
	char categoria;
	int horasTrabajadas;
	
	Empleado(String nombre, char categoria, int horasTrabajadas){
		this.nombre = nombre;
		this.horasTrabajadas = horasTrabajadas < 0 ? 0 : horasTrabajadas;
		
		if (categoria != 'a' && categoria != 'b' && categoria != 'c')
			this.categoria = 'a';
		else
			this.categoria = categoria;
	}
	
	double salario() {
		switch(categoria) {
		case 'a':
			return horasTrabajadas * SAL_A;
		case 'b':	
			 return horasTrabajadas * SAL_B;
		case 'c':
			return horasTrabajadas * SAL_C;
		}
		return -1;
	}
	
	void ver(){
		System.out.println("Nombre: " + nombre);
		System.out.println("Categoria: " + categoria);
		System.out.println("Horas Trabajadas: " + horasTrabajadas);
	}
}
