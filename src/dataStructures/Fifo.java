package dataStructures;

/**
 * File d'attente fonctionnant sur le principe du first in first out
 * @param <E>
 *
 * @author mickael
 */
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

	/**
	 * Renvoie le contenue de l'élément de tête
	 * @return
	 */
	public E donneeTete() {
		return tete.donnee;
	}

	/**
	 * Permet l'ajout d'un élément à la queue de la file d'atente
	 * @param element
	 */
	public void ajouter(E element) {
		Cellule<E> cellule = new Cellule<>(element);
		
		if (estVide()) {
			tete = cellule;
			queue = cellule;
		}

		queue.lien = cellule;
        queue = cellule;
	}

	/**
	 * Retourne et supprime l'élément de tête de la file d'attente ( le plus ancient )
	 * @return
	 */
	public E retirer() {
		Cellule<E> temp = tete;
		tete = tete.lien;
		
		if (estVide()) {
			queue = null;
		}
		
		return temp.donnee;
	}

    /**
     * Recherche un element dans la file d'attente, retourne true si ce dernier est trouvé, et false dans le cas contraire
     * @param elementRecherche
     * @return boolean
     */
	public boolean contient(E elementRecherche) {
        Cellule<E> element = tete;
        while (element != null) {
            if (elementRecherche.equals(element.donnee)) {
                return true;
            }
            else {
                element = element.lien;
            }
        }
        return false;
    }


    /**
     * Méthode toString de Fifo
     * @return
     */
	public String toString() {
		StringBuilder chaine = new StringBuilder();
		
		Cellule<E> element = tete;
		while (element != null) {
			chaine.append("\n").append(element.donnee);
			element = element.lien;
		}
		
		return chaine.toString();
	}
}
