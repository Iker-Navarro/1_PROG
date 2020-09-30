package pack4;

public class Test {
	public static void main(String[] args) {
		Agenda a1 = new Agenda(3);
		
		a1.aniadeEntrada("Usuario 1", new String[] {"999 999 999", "888 888 888", "777 777 777"});
		a1.aniadeEntrada("Usuario 2", new String[] {"666 666 666", "333 333 333"});
		a1.aniadeEntrada("Usuario 1", new String[] {"999 999 999", "888 888 888", "777 777 777", "666 666 666", "555 555 555", "666 666 666"});
		a1.aniadeEntrada("Usuario 3", new String[] {"999 999 999"});
		a1.aniadeEntrada("Usuario 4", new String[] {"111 111 111"});
		
		a1.ver();
		
		System.out.println(a1.buscaNombre("Usuario 1"));
		System.out.println(a1.buscaNombre("Usuario 0"));
		
		System.out.println(a1.cuantosTelefonos("666 666 666") + " usuarios tienen registrado el telefono dado.");
	}
}
