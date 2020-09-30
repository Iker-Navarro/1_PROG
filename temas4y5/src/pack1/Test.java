package pack1;

public class Test {
	public static void main(String[] args) {
		Empleado e = new Empleado("Empleado 1", 'a', 30);
		Jefe j = new Jefe(5 ,"Jefe 1", 'a', 30);
		EmpleadoComision ec = new EmpleadoComision(3500, 25, "Empleado a Comision 1", 'c', 30);
		
		e.ver();
		System.out.println(e.salario());
		System.out.println("------------");
		
		j.ver();
		System.out.println(j.salario());
		System.out.println("------------");
		
		ec.ver();
		System.out.println(ec.salario());
		System.out.println("------------");
	}
}
