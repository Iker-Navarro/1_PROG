package tanda1;

public class Tanda1Eje8 {

	public static void main(String[] args) {
		/*
		 * n=5;
		 * 1
		 * 2	1
		 * 3	1	-1
		 * 4	1	-2	-5
		 * 5	1	-3	-7	-11
		 * 
		 */
		System.out.print("Introduce un valor para n: ");
		int n = Consola.leeInt();
		for(int i = 1; i <= n; i++) {
			for(int j = 1, num = i; j <= i; j++) {
				System.out.print(num + "\t");
				num -= (i-1);
			}
			System.out.print("\n");
		}
	}

}
