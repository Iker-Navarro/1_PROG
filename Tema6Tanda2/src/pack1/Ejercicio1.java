package pack1;

import java.util.Arrays;

public class Ejercicio1 {
	public static void main(String[] args) {
		
		int n, m;
		
		System.out.print("Cantidad de alumnos: ");
		n = Consola.leeInt();
		System.out.print("Cantidad de asignaturas: ");
		m = Consola.leeInt();
		
		int[][] notas = new int[n][m];
		
		rellenar(notas);
		
		ver(notas);
		
		System.out.println(mediaAlumno(notas, 1));
		
		int[] notasMaximas = maxNotas(notas);
		
		System.out.println(Arrays.toString(notasMaximas));
		
		int[][] a = cantidadNotas(notas);
		
		System.out.println(Arrays.deepToString(a));
		
		}
	
	private static void rellenar(int[][] m) {
		for (int j = 0; j < m[0].length; j++) {
			for (int i = 0; i < m.length; i++) {
				System.out.print("Nota en asignatura " + (j+1) + " del alumno " + (i + 1)+ ": ");
				m[i][j] = Consola.leeInt();
			}
		}
	}
	
	private static double mediaAlumno(int[][] m, int indiceAlumno) {
		indiceAlumno--;
		int suma = 0;
		for (int j = 0; j < m[indiceAlumno].length; j++) {
			suma+= m[indiceAlumno][j];
		}
		
		return suma/((double) m[indiceAlumno].length);
	}
	
	private static void ver(int[][] m) {
		System.out.print("        \t");
		for (int j = 0; j < m[0].length; j++) {
			System.out.print("A" + (j+1) + "\t");
		}
		System.out.println();
		for(int i = 0; i < m.length; i++) {
			System.out.print("Alumno " + (i+1) + "\t");
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + "\t");
			}
			System.out.println();
		}
	}
	
	private static int[] maxNotas(int[][] m) {
		int[] notasMaximas = new int[m.length];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				if(notasMaximas[i] < m[i][j]) {
					notasMaximas[i] = m[i][j];
				}
			}
		}
		return notasMaximas;
	}
	
	private static int[][] cantidadNotas(int[][] m){
		int[][] vuelta = new int[m.length][4];
		
		for (int i = 0; i < m.length; i++) {
			int suspensos = 0, aprobados = 0, notables = 0, sobresalientes = 0;
			for (int j = 0; j < m[i].length; j++) {
				if(m[i][j] < 5) 
					suspensos++;
				else if(m[i][j] < 7)
					aprobados++;
				else if(m[i][j] < 9)
					notables++;
				else 
					sobresalientes++;
			}
			
			vuelta[i][0] = suspensos;
			vuelta[i][1] = aprobados;
			vuelta[i][2] = notables;
			vuelta[i][3] = sobresalientes;
		}
		
		return vuelta;
	}
}
