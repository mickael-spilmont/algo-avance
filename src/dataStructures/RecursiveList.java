
package dataStructures;
import java.util.ArrayList;
import java.util.Collection;
/**
 * <pre>
 * Classe utilitaire "générique" pour la représentation et la manipulation de listes d'informations.
 * 
 * Ces outils reposent sur une implémentation dynamique sans utiliser de "vecteur" (array, tableau) 
 * ni de "Collection" Java.
 * <b>Cette classe est adaptée pour la définition de traitements récursifs des listes</b>.
 * 
 * N.B. dans toutes les manipulations réalisées, les éléments eux-mêmes ne sont jamais "clonés".
 * </pre>
 *  @author Erick Timmerman  (Erick.Timmerman@univ-lille.fr) 
 *  @param <E> type générique des éléments de la liste
 */
public class RecursiveList<E>
{  
    /**
     * Classe interne utilitaire pour la représentation d'une structure comportant une valeur 
     * (la "tête" de la Liste) et une liste (le "corps", le reste de la Liste).
     */
    private class CellOfList <T>
    {
        private	T tete;
        private RecursiveList<T> corps;

    	/**
     	 * Création d'une cellule "isolée".
     	 */
        CellOfList (T valeur)
        {
            tete = valeur;
            corps = new RecursiveList<> (); // liste vide
        }

    	/** 	
     	 * Création d'une cellule avec lien vers une liste donnée.
    	 */
        CellOfList (T valeur, RecursiveList<T> liste)
        {
            tete = valeur;
            corps = liste;	
        }
    }   // Fin de la classe interne "CellOfList<T>"
    
 // attribut de la classe "RecursiveList<E>"
    private CellOfList <E> firstCell;

 // constructeurs

    /**
     * 	Création d'une liste vide.
     */
    public RecursiveList() 			
    {
        firstCell = null;
    }

    /**
     * <pre> Création d'une liste non vide.
        La liste est construite de façon à y obtenir comme "tête": 
               la valeur fournie en 1er argument,
        et comme "corps": 
              la liste d'éléments fournie en 2ème argument.
       </pre>
     * @param valeur la valeur qui sera en tête
     * @param liste la liste qui sera le corps de la liste construite
     */
    public RecursiveList(E valeur, RecursiveList<E> liste) 
    {
        firstCell = new CellOfList <>(valeur, liste);
    }

    /**
     * Création d'une liste d'un seul élément ("liste singleton").
     * @param valeur  valeur à placer dans la liste créée
     */
    public RecursiveList(E valeur) 	
    {
        firstCell = new CellOfList <>(valeur);
    }
    

// méthodes utilitaires de base

    /**
     * Détermine si la liste (this) est vide ou non.
     * @return true si la liste est vide.
     */	
    public boolean estVide()
    {
        return firstCell == null;
    }

    /**
     * Retourne la tête de la liste (this).
     * <b>La liste ne doit pas être vide.</b>
     * @return  la valeur en tête de la liste
     */
    public E tete()
    { 
        return firstCell.tete;
    }

    /**
     * Retourne le reste, le corps,  de la liste.
     * <b>La liste ne doit pas être vide.</b>
     * @return le corps de la liste 
     */
    public RecursiveList<E> corps()
    {
        return  firstCell.corps;
    }

    /**
     * 	
     * Modifie la liste (this) en la "remplaçant" par celle fournie
     * @param liste une autre liste
     */
    protected void setList(RecursiveList<E> liste)
    {
        firstCell = liste.firstCell;
    }
    
    /**
     * Modifie la tête de la liste.
     * <b>La liste ne doit pas être vide.</b>
     * @param value une valeur qui remplacera l'actuelle tête de liste
     */
    public void setTete(E value)
    {
        firstCell.tete = value;
    }

    /**
     * 
     * Modifie la liste en y insérant en tête l'élément fourni en argument.
     * @param value valeur à ajouter en tête de la liste
     */
    public final void ajouterEnTete(E value)
    {
        CellOfList <E> local = new CellOfList <>(value);
        
        local.corps.firstCell = firstCell;
        firstCell = local;
    }

    /**  
     * Suppression de la tête de la liste. 
     * <b>La liste ne doit pas être vide.</b>
     */
    public void supprimerEnTete()
    {
        setList(corps());
        // ou firstCell = corps().firstCell;
    }

//  méthodes utilitaires "évoluées" (on peut maintenant oublier les "cellules").
    /**
     * Retourne le nombre d'éléments de la liste.
     * @return  la longueur (nombre d'éléments) de la liste
     */
    public int longueur()
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Détermine si la liste contient la valeur fournie en argument.
     * @param value valeur recherchée
     * @return true ssi la liste contient la valeur fournie en argument
     */
    public boolean contient(E value)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Version itérative de la recherche d'une valeur dans la liste.
     * @param value valeur recherchée
     * @return true ssi la liste contient la valeur fournie en argument
     */
    public boolean contientIt(E value)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * <pre>Fournit une chaîne de caractères selon le format:
     *    "()"  si la liste est vide, "(e1,e2, ..., en)" pour une liste de n éléments.
     * </pre>
     * @return une chaîne de caractères comportant les éléments de la liste
     */
    @Override 
    public String toString ()
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Modifie le liste en y ajoutant en fin la valeur fournie en argument.
     * @param value valeur à ajouter en fin de la liste
     */
    public void ajouterEnFin(E value)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
      	
    /**
     * Renvoie la partie de la liste (éventuellement vide) débutant à la 1ère occurrence 
     * de la valeur fournie en argument.
     * @param value valeur recherchée
     * @return la 1ère "sous-liste" débutant par la valeur fournie,
     *         une liste vide si cette valeur n'apparait pas dans la liste
     */
    
    public RecursiveList<E> sublistBeginningWith(E value)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * 
     * <pre>Supprime de la liste la 1ère occurrence de la valeur fournie en argument.
     * Rien n'est fait si cette valeur n'apparait pas dans la liste.
     * </pre>
     * @param value valeur à supprimer
     */
    public void supprimerFirst(E value)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Supprime de la liste toutes les occurrences de la valeur fournie en argument.
     * @param value valeur à supprimer
     */
    public void	supprimerAll(E value)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
            
    /**
     * Renvoie une copie ("clone") de la liste. Les éléments eux-mêmes ne sont pas clonés.
     * @return  une nouvelle liste, copie conforme de la liste (this).
     */
    public RecursiveList<E> copie()
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
	
    /**
     * Détermine si la liste (this) est identique à celle fournie en argument.
     * @param autre liste d'éléments de "type" E
     * @return true iff les 2 listes sont identiques.
     */
    public boolean equals(RecursiveList<E> autre)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Modifie la liste en y concaténant celle fournie en argument.
     * @param autre liste à concaténer à la liste
     */
    public void concatener(RecursiveList<E> autre) // modification de la liste (this)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Fournit une nouvelle liste indépendante formée des éléments de la liste (this)
     * suivis de ceux de la liste fournie en argument.
     * Remarque: les éléments eux-mêmes ne sont pas "clonés".
     * @param  autre     liste d'éléments de "type" E
     * @return      une nouvelle liste comportant les valeurs de la liste (this) 
     *              suivies de celles de l'autre liste.
     */
    public RecursiveList<E> concatenation(RecursiveList<E> autre) 
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Fournit une nouvelle liste, une liste "miroir" des éléments de la liste.
     * Remarques:   les éléments ne sont pas clonés,
     *              <b>version particulièrement inefficace!!!</b>
     * 
     * @return  une nouvelle liste, le "miroir" de la liste.
     */
    public RecursiveList<E> miroir()
    {
        if (estVide())
            return new RecursiveList<>();
        RecursiveList<E> liste = corps().miroir();
        liste.ajouterEnFin( tete());
        return liste;
    }
    
    /**
     * Fournit une nouvelle liste, une liste "miroir" des éléments de la liste.
     * Remarques:   les éléments ne sont pas clonés, 
     *              <b>version efficace!</b>
     * @return  une nouvelle liste, le "miroir" de la liste.
     */
    public RecursiveList<E> miroirIteratif()
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * <PRE>Détermine si la liste est une sous-liste de celle fournie en argument.
     * "Sous-liste" dans le sens où:
     *    si la liste est (e1,e2, ..., en),
     *    le résultat sera vrai si la liste fournie en argument est de la forme:
     *        (..., e1, ..., e2, ..., en, ...).
     *    i.e. on retrouve toutes les valeurs (dans le même ordre) de la liste.    
     * </PRE>
     * @param autre     liste d'éléments de "type" E
     * @return true 	iff la liste est une "sous-liste" de l'autre liste.
     */
    public boolean estSousListe(RecursiveList<E> autre)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * Fournit un vecteur d'objets (Object) comportant les éléments de la liste.
     * Remarque: les éléments ne sont pas clonés.
     * 
     * @return  un vecteur (tableau) d'éléments comportant les éléments de la liste.
     */    
    public Object[] toArray()
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Fournit un vecteur d'objets typés (E) comportant les éléments de la liste.
     * Remarque: les éléments ne sont pas clonés.
     * Exemple d'utisation:
     *      RecursiveList<Machin> l= new RecursiveList<>();
     *      // ... remplissage de la liste
     *      Machin[] trucs = l.toArray(new Machin[0]);
     * 
     * @param   existingArrayOfE  vecteur (d'éléments de type E) existant.
     * @return  un vecteur d'éléments comportant les éléments de la liste.
     */    
    public E[] toArray(E[] existingArrayOfE)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Constructeur pour obtenir une liste à partir d'éléments fournis dans un vecteur ("array").
     * L'ordre des éléments de la liste construite est le même que celui obtenu par un 
     * parcours séquentiel du vecteur.
     * 
     * @param array  vecteur d'éléments de "type" E
     */
    public RecursiveList(E [] array)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    /**
     * Constructeur pour obtenir une liste à partir d'éléments fournis par une "Collection" Java.
     *
     * L'ordre des éléments de la liste construite est l'ordre inverse de celui obtenu à l'aide 
     * d'un "for each statement" de la collection.
     * 
     * @param coll  une collection d'éléments de "type" E
     */
    public RecursiveList(Collection<E> coll)
    {
        // to do!
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
