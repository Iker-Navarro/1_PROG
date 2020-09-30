package tanda1;

public class Tanda1Eje3 {
	public static void main(String[] args) {
		
		int a,b,c;
		
		System.out.print("a");
		a = Consola.leeInt();
		System.out.print("b");
		b = Consola.leeInt();
		System.out.print("c");
		c = Consola.leeInt();
		
		if(a > b && a > c) {
			if(b < c) {
				System.out.print(c);
			}
			else {
				System.out.print(b);
			}
		}
		else if(b > c){
			if(a < c) {
				System.out.print(c);
			}
			else {
				System.out.print(a);
			}
		}
		else {
			if(a < b) {
				System.out.print(b);
			}
			else {
				System.out.print(a);
			}
		}
	}
}
