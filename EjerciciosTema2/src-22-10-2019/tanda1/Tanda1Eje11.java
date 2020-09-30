package tanda1;

public class Tanda1Eje11 {

	public static void main(String[] args) {
		
		int num, nnum, i;
		
		System.out.print("Introduce un numero: ");
		num = Consola.leeInt();
		
		System.out.print(num + " = ");
		for(nnum = num, i = 2; nnum != 1;) {
			if (nnum % i == 0) {
				nnum/=i;
				if(nnum == 1) {
					System.out.print(i);
				}
				else {
					System.out.print(i + " * ");					
				}
			}
			else {
				i++;
			}
		}
	}

}
