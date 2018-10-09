package calculRecursif;

public class SommeProduit {
	
	public static int somme(int a, int b) {
		System.out.println("somme : " + a + " ; " + b);
		if (b > 0) {
			return somme(a + 1, b - 1);
		}
		return a;
	}
	
	public static int produit(int a, int b) {
		System.out.println("produit : " + a + " ; " + b);
		if (a == 0 || b == 0) {
			return 0;
		}
		
		if (b > 1) {
			return somme(a, produit(a, b - 1));
		}
		return a;
	}
}
