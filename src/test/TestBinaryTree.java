package test;

import dataStructures.BinaryTree;

public class TestBinaryTree {

	public static void main(String[] args) {
//		Création d'un arbre binaire
		BinaryTree<Integer> e = new BinaryTree<>(5);
		BinaryTree<Integer> d = new BinaryTree<>(4);
		BinaryTree<Integer> c = new BinaryTree<>(3);
		BinaryTree<Integer> f = new BinaryTree<>(6);
		
		BinaryTree<Integer> b = new BinaryTree<>(2, f, e);
		BinaryTree<Integer> a = new BinaryTree<>(1, c, d);
		
		BinaryTree<Integer> arbre = new BinaryTree<>(0, a, b);
		
//		Est vide
		System.out.println(arbre.estVide());
		
//		Affichage racine
		System.out.println(arbre.getRacine() + "\n");
		
//		Affichage prefixe
		System.out.println(arbre.affichagePrefixe() + "\n");
		
//		Affichage infixe
		System.out.println(arbre.affichageInfixe() + "\n");
		
//		Affichage suffixe
		System.out.println(arbre.affichageSuffixe() + "\n");
		
//		To string
		System.out.println(arbre + "\n");
		
//		Est binaire pur
		System.out.println(arbre.estBinairePur() + "\n");
		
//		Get feuillage
		System.out.println(arbre.getFeuillage() + "\n");
		
		System.out.println("Fin de l'execution");
	}
}
