package structures;

public class Fifo<E> {
	
	private class Cellule<F> {
		F donnee;
		Cellule<F> lien;
		
		Cellule ( F donnee) {
			this.donnee = donnee;
			lien = null;
		}
	}
	
	private Cellule<E> tete;
	private Cellule<E> queue;
	
	/**
	 * Constructeur de Fifo
	 */
	public Fifo() {
		this.tete = null;
		this.queue = null;
	}
	
	public boolean estVide() {
		return tete ==  null;
	}
	
	public E donneeTete() {
		return tete.donnee;
	}
	
	public void ajouter(E element) {
		Cellule<E> cellule = new Cellule<>(element);
		
		if (estVide()) {
			tete = cellule;
			queue = cellule;
		}
		
		queue = cellule;
		queue.lien = cellule;
	}
	
	public E retirer() {
		Cellule<E> temp = tete;
		tete = tete.lien;
		
		if (estVide()) {
			queue = null;
		}
		
		return temp.donnee;
	}
	
	public String toString() {
		StringBuilder chaine = new StringBuilder();
		
		Cellule<E> lienLocal = tete;
		while (lienLocal != null) {
			chaine.append("\n").append(lienLocal.donnee);
			lienLocal = lienLocal.lien;
		}
		
		return chaine.toString();
	}
}
