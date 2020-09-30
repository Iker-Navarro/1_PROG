package ejer4;

public class NumCapic {
	long num;
	boolean esCapicua;
	
	NumCapic() {
		
	}
	
	NumCapic(long num) {
		this.num = num;
		esCapicua = capicua();
	}
	
	int cantDigitos() {
		int res = 0;
		for(long i = num; i != 0; i/=10, res++);
		return res;
	}
	
	int digitoEnPosicionN(int n) {
		
		return (int) (num/potencia(10,(n-1))%10);
	
	}
	
	private int potencia(int base, int exponente) {
		int resp = 1;
		for(;exponente > 0; exponente--) {
			resp *= base;
		}
		return resp;
	}
	
	boolean capicua() {
		int cantdigitos = cantDigitos();
		for(int i = 1; i <= cantDigitos()/2; i++) {
			//System.out.println(digitoEnPosicionN(i) + " - " + digitoEnPosicionN(cantDigitos()- (i-1)));
			if (digitoEnPosicionN(i) != digitoEnPosicionN(cantdigitos- (i-1))) {
				return false;
			}	
		}
		return true;
	}
}
