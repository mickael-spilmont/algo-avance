package calculRecursif;

public class Persistance {
	
	public static int produitDesChiffres(int n) {
		if (n < 10) return n;
		
		return n % 10 * produitDesChiffres(n / 10);
	}
	
	public static int persitance(int n) {
		if (n < 10) return 0;
		
		return 1 + persitance(produitDesChiffres(n));
	}
}
