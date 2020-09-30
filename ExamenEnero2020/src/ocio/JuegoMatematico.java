package ocio;

public class JuegoMatematico extends Juego{
	private static final int N = 5;
	
	private int n;
	
	public JuegoMatematico(int duracion, Jugador j, int n) {
		super(duracion, j);
		this.n = n;
	}
	
	public JuegoMatematico(int duracion) {
		super(duracion);
		n = N;
	}

	@Override
	public boolean jugar() {
		boolean respondidoCorrectamente;
		int aciertos = 0;
		
		System.out.println("--JUEGO MATEMATICO--");
		if(getJ()==null)
			System.out.println("Comenzando partida para jugador Invitado");
		else
			System.out.println("Comenzando partida para el jugador " + getJ().getNombre());
		
		for(int i = 1; i <= n; i++ ) {
			int tipoPregunta = aleatorio(1, 2);
			System.out.println("Pregunta numero " + i + " de " + n);

			respondidoCorrectamente = tipoPregunta == 1 ? tipo1() : tipo2();

			if(respondidoCorrectamente) {
				if(getJ()==null)
					System.out.println("El Jugador Invitado HA ACERTADO" );
				else
					System.out.println("El Jugador " + getJ().getNombre() + " HA ACERTADO");
				aciertos++;
			}
			else {
				if(getJ()==null)
					System.out.println("El Jugador Invitado HA FALLADO" );
				else
					System.out.println("El Jugador " + getJ().getNombre() + " HA FALLADO");
			}
		}
		
		return (aciertos*100)/n >= 70 ? true : false;
	}
	
	//Preguntas de distancia entre puntos
	private boolean tipo1() {
		double respuesta, rCorrecta;
		Punto p1 = new Punto(aleatorio(-50, 40), aleatorio(-50, 40));
		Punto p2 = new Punto(aleatorio(-50, 40), aleatorio(-50, 40));
		while(p1.esIgualA(p2)) {
			p2.setX(aleatorio(-50, 40));
			p2.setY(aleatorio(-50, 40));
		}
		System.out.println("Calcula la distancia entre los siguentes puntos:");
		System.out.println(p1 + " y " + p2);
		
		System.out.print("\nDISTANCIA = ");
		respuesta = Consola.leeDouble();
		rCorrecta = p1.distanciaA(p2); 
		if(respuesta >= rCorrecta - 0.2 && respuesta <= rCorrecta + 0.2)
			return true;
		return false;
	}
	
	//Preguntas de sumar digitos recursivamente
	private boolean tipo2() {
		final int ND = 6;
		
		int rCorrecta, respuesta;
		long numero = aleatorio(1,(int) Math.pow(10, ND))-1;
		
		//en el caso que el primer digito sea 0 añadimos un numero aleatorio al final
		if (numero < Math.pow(10, ND-1)) {
			numero = Long.parseLong("" + numero + aleatorio(0,9));
		}
		
		rCorrecta = calcularTipo2(numero);
		
		System.out.println("Calcula la suma recursiva de los digitos del siguiente numero:");
		System.out.println(numero);
		
		System.out.print("RESPUESTA: ");
		respuesta = Consola.leeInt();
		
		if(respuesta == rCorrecta) 
			return true;
		return false;
	};
	
	private int calcularTipo2(long numero) {
		int respuesta = 0;
		
		for(;numero/10!=0;numero/=10) {
			respuesta+=numero%10;
		}
		respuesta+=numero%10;
		
		return respuesta;
	}
	
	private static int aleatorio(int min, int max) {
		return (int) ((Math.random()*(max - min +1))+min);
	}
	
	@Override
	public String toString() {
		return super.toString() + "Numero de preguntas: " + n;
	}
}
