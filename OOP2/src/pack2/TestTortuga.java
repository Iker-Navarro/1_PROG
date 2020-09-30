package pack2;

public class TestTortuga {
	public static void main(String[] args) {
		Tortuga t1 = new Tortuga (-740);
		System.out.println(Tortuga.tortugasAtleticas);
		t1.verPos();
		t1.girarIzquierda(-720);
		t1.mirarDerecha();
		System.out.println(t1.angulo);
		t1.avanzar(5);
		t1.verPos();
		t1.retroceder(5);
		t1.verPos();
		//t1.avanzar(10);
		//t1.verPos();
		/*
		t1.girarIzquierda(20);
		t1.retroceder(20);
		t1.verPos();
		t1.avanzar(30);
		t1.verPos();
		t1.retroceder(300);
		t1.verPos();
		System.out.println(t1.distanciaTotal);
		System.out.println(Tortuga.tortugasAtleticas);
		t1.avanzar(300);
		System.out.println(Tortuga.tortugasAtleticas);
		*/
	}
}
