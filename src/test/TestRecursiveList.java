package test;

import dataStructures.RecursiveList;

public class TestRecursiveList {

	public static void main(String[] args) {
		RecursiveList<Integer> liste1 = new RecursiveList<>();
		
//		Remplissage de la liste avec des integer
		for (int i = 0 ; i < 10 ; i++) {
			liste1.ajouterEnTete(i);
		}
		
//		Test de longueur
		System.out.println(liste1.longueur());
		
//		Test de contiens
		System.out.println(liste1.contient(5));
		
//		Test de ajouter en fin
		liste1.ajouterEnFin(10);
		
//		Test du toString
		System.out.println(liste1);

		System.out.println("Fin de l'execution");
	}

}
