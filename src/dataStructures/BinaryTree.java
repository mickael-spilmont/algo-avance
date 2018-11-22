package dataStructures;

public class BinaryTree<E> {

	private class Node<F> {
		private F data;
		private BinaryTree<F> gauche;
		private BinaryTree<F> droit;

		Node(F valeur) {
			this.data = valeur;
			this.gauche = new BinaryTree<> ();
			this.droit = new BinaryTree<> ();
		}
		
		Node(F valeur, BinaryTree<F> gauche, BinaryTree<F> droit) {
			this.data = valeur;
			this.gauche = gauche;
			this.droit = droit;
		}
	}
	
	private Node<E> racine;
	
	/**
	 * Création d'un arbre vide
	 */
	public BinaryTree() {
		racine = null;
	}
	
	/**
	 * Création d'un arbre binaire sans sous-abre
	 * @param valeur
	 */
	public BinaryTree(E valeur) {
		racine = new Node<>(valeur);
	}
	
	/**
	 * Création d'un arbre avec valeur et sous arbres
	 * @param valeur
	 * @param gauche
	 * @param droit
	 */
	public BinaryTree(E valeur, BinaryTree<E> gauche, BinaryTree<E> droit) {
		racine = new Node<>(valeur, gauche, droit);
	}
	
//	Méthodes utilitaires de base
	
	/**
	 * Determine si l'arbre est vide
	 * @return true si l'arbre est vide
	 */
	public boolean estVide() {
		return racine == null;
	}
	
	/**
	 * Determine si l'arbre est une feuille (data sans sous-arbre)
	 * @return true si l'arbre est une feuille
	 */
	public boolean estUneFeuille() {
		return this.getGauche().estVide() && this.getDroit().estVide();
	}
	
	/**
	 * retourne la racine de la racine de l'arbre
	 * @return la valeur contenue dans la racine
	 */
	public E getRacine() {
		return racine.data;
	}
	
	/**
	 * Retourne le sous-arbre gauche
	 * @return le sous-arbre gauche
	 */
	public BinaryTree<E> getGauche() {
		return racine.gauche;
	}
	
	/**
	 * Retourne le sous-arbre droit
	 * @return le sous-arbre droit
	 */
	public BinaryTree<E> getDroit() {
		return racine.droit;
	}
	
	/**
	 * Modifie l'arbre en le modifiant par celui fourni
	 * @param tree
	 */
	public void setTree(BinaryTree<E> tree) {
		racine = tree.racine;
	}
	
	/**
	 * Modifie la valeur de la racine de l'arbre
	 * @param valeur
	 */
	public void setRacine(E valeur) {
		racine.data = valeur;
	}
	
//	/**
//	 * Modifie l'arbre de gauche
//	 * @param tree
//	 */
//	public void setGauche(BinaryTree<E> tree) {
//		this.racine.gauche = tree;
//	}
//	
//	/**
//	 * Modifie l'arbre de droite
//	 * @param tree
//	 */
//	public void setDroit(BinaryTree<E> tree) {
//		this.racine.droit = tree;
//	}
	
	public String toString() {
		if (this.estVide()) {
			return "";
		}
		if (this.estUneFeuille()) {
			return this.getRacine().toString();
		}
		return this.getRacine() + "(" + this.getGauche() + ", " + this.getDroit() + ")";
	}
	
	/**
	 * Affichage de l'arbre dans l'ordre prefixe (racine, gauche, droit)
	 * @return
	 */
	public String affichagePrefixe() {
		if (this.estVide()) {
			return "";
		}
		return this.getRacine() + " " + this.getGauche().affichagePrefixe() + this.getDroit().affichagePrefixe();
	}
	
	/**
	 * Affichage de l'arbre dans l'ordre infixe (gauche, racine, droit)
	 * @return
	 */
	public String affichageInfixe() {
		if (this.estVide()) {
			return "";
		}
		return this.getGauche().affichageInfixe() + this.getRacine() + " " + this.getDroit().affichageInfixe();
	}
	
	/**
	 * Affichage de l'arbre dans l'ordre suffixe (gauche, droit, racine)
	 * @return
	 */
	public String affichageSuffixe() {
		if (this.estVide()) {
			return "";
		}
		return this.getGauche().affichageSuffixe() + this.getDroit().affichageSuffixe() + this.getRacine() + " ";
	}
	
	/**
	 * Permet de savoir si l'arbre est binaire pur.
	 * @return true si il l'est, et false dans la cas contraire
	 */
	public boolean estBinairePur() {
		if (this.estVide() || this.estUneFeuille()) {
			return true;
		}
		if (this.getGauche().estVide() || this.getDroit().estVide()) {
			return false;
		}
		return this.getGauche().estBinairePur() && this.getDroit().estBinairePur();
	}
	
	public RecursiveList<E> getFeuillage() {
		 if (this.estVide()) {
			 return new RecursiveList<>();
		 }
		 if (this.estUneFeuille()) {
			 return new RecursiveList<>(this.getRacine());
		 }
		 return this.getGauche().getFeuillage().concatenation(this.getDroit().getFeuillage());
	}
}
