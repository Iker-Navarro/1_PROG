package pack2_2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class Numeros {
	
	public static int generar1Num(int min, int max) {
		return (int) (Math.random()*(max - min + 1) + min);
	}
	
	public static ArrayList<Integer> generarNums(int min, int max, int cant){
		ArrayList<Integer> vuelta = new ArrayList<Integer>();
		
		if(max - min + 1 < cant) {
			System.out.println("Error");
			return vuelta;
		}
		
		if (min > max) {
			int aux = min;
			min = max;
			max = aux;
		}
		
		for(int i = 0; i < cant; i++) {
			int actual;
			do {
				actual = generar1Num(min, max);
			} while (vuelta.contains(actual));
			
			vuelta.add(actual);
		}
		return vuelta;
	}
	
	public static void aniade1Num(String nomFich, int num) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(nomFich, true));
		dos.writeInt(num);
		dos.close();
	}
	
	public static void aniadeNums(String nomFich, ArrayList<Integer> nums) throws IOException {
		DataOutputStream dos = new DataOutputStream(new FileOutputStream(nomFich, true));
		for (Integer num : nums) {
			dos.writeInt(num);
		}
		dos.close();
	}
	
	public static void vaciar(String nomFich) throws IOException {
		File f = new File(nomFich);
		
		if (!f.exists()) {
			System.out.println("El archivo no existe");
		}
		else {
			
			FileOutputStream fos = new FileOutputStream(f);
			fos.close();
		}
			
	}
	
	public static void ver(String nomFich) throws IOException {
		
		File f = new File(nomFich);

		if (!f.exists()) {
			System.out.println("El archivo no existe");
			return;
		}
		
		DataInputStream dis = new DataInputStream(new FileInputStream(f));
		int intsDisponibles = dis.available() / Integer.BYTES;
		
		while(intsDisponibles > 0) {
			int actual = dis.readInt();
			System.out.print(actual + " ");
			intsDisponibles--;
		}
		System.out.println();
		dis.close();
	}
	
	public static Integer BuscarEnFichero(String nomFich, int p) throws IOException {
		File f = new File(nomFich);
		
		if(!f.exists()) 
			return null;
		
		DataInputStream dis = new DataInputStream(new FileInputStream(f));
		int total = dis.available()/Integer.BYTES;
		p--;
		if(p < 0 || p >= total) {
			dis.close();
			return null;
		}

		dis.skip(p * Integer.BYTES);
		Integer vuelta = dis.readInt();
		dis.close();
		
		return vuelta;
	}
	
	public static int fileLength(String nomFich) throws IOException {
		DataInputStream dis = new DataInputStream(new FileInputStream(nomFich));
		int available = dis.available();
		dis.close();
		return available/Integer.BYTES;	
	}
	
	public static void main(String[] args) throws IOException {
		ArrayList<Integer> numeros = new ArrayList<Integer>();
		numeros.add(15);
		numeros.add(14);
		numeros.add(12);
		numeros.add(120);
		numeros.add(1550);
		
		String n = "fich1.bin";
		vaciar(n);
		
		aniadeNums(n, numeros);
		aniade1Num(n, generar1Num(1, 5));
		aniadeNums(n, generarNums(1, 50, 5));
		
		ver(n);
		
		Integer buscado = BuscarEnFichero(n, 6);
		if(buscado != null)
			System.out.println(buscado);
		
		buscado = BuscarEnFichero(n, 9);
		if(buscado != null)
			System.out.println(buscado);
		
		buscado = BuscarEnFichero(n, fileLength(n));
		if(buscado != null)
			System.out.println(buscado);
		
		String n2 = "fich2.bin";
		vaciar(n2);
		
		aniadeNums(n2, generarNums(50, 70, 10));
		
		
		ArrayList<Integer> numPosicionesImpares = new ArrayList<Integer>();
		for (int i = 2; i <= fileLength(n2); i+=2) {
			numPosicionesImpares.add(BuscarEnFichero(n2, i));
		}
		
		aniadeNums(n, numPosicionesImpares);
		
		ver(n);
		ver(n2);
	}
}
