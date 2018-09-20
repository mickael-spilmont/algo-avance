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
		cellule.lien = queue;
		queue = cellule;
		
		if (tete == null) {
			tete = cellule;
		}
	}
	
	public E retirer() {
		Cellule<E> temp = tete;
		tete = tete.lien;
		
		if (tete.equals(queue)) {
			queue = null;
		}
		
		return temp.donnee;
	}
	
	public String toString() {
		StringBuilder chaine = new StringBuilder();
		
		Cellule<E> lien = tete;
		while (lien != null) {
			chaine.append("\n").append(lien.donnee);
			lien = lien.lien;
		}
		
		return chaine.toString();
	}
}
