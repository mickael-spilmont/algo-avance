
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
    
 // attribut de la classe "Liste<E>"
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
        if (estVide())
            return 0;
        return 1 + corps().longueur();
    }

    /**
     * Détermine si la liste contient la valeur fournie en argument.
     * @param value valeur recherchée
     * @return true ssi la liste contient la valeur fournie en argument
     */
    public boolean contient(E value)
    {
        if (estVide())
            return false;
        if (value.equals(tete()))
            return true;
        return corps().contient(value);
    }
    
    /**
     * Version itérative de la recherche d'une valeur dans la liste.
     * @param value valeur recherchée
     * @return true ssi la liste contient la valeur fournie en argument
     */
    public boolean contientIt(E value)
    {
        RecursiveList<E> l = this;
        while (!l.estVide())
            if (value.equals(l.tete()))
                return true;
            else
                l = l.corps();
        return false;
    }
    
    /**
     * <pre>Fournit une chaîne de caractères selon le format:
     *    "()"    si la liste est vide,  "(e1,e2, ..., en)" pour une liste de n éléments.
     * </pre>
     * @return une chaîne de caractères comportant les éléments de la liste
     */
    @Override 
    public String toString ()
    {
        if (estVide())
            return "()";
        return  "(" + contenu() + ")";
    }
    
    private String contenu()
    {
        // hypothèse, précondition: la liste n'est pas vide.
        if (corps().estVide())
            return tete().toString();
        return tete() + "," + corps().contenu();
    }

    /**
     * Modifie le liste en y ajoutant en fin la valeur fournie en argument.
     * @param value valeur à ajouter en fin de la liste
     */
    public void ajouterEnFin(E value)
    {
        if (estVide())
            ajouterEnTete(value);
        else
            corps().ajouterEnFin(value);
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
        if (estVide())
            return this;
        if (tete().equals(value))
            return this;
        return corps().sublistBeginningWith(value);
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
        if (estVide())
            return;
        if (value.equals (tete()))
            supprimerEnTete();
        else
            corps().supprimerFirst(value); 
        
        /*  ou:
         {    Liste l = this.sublistBeginningWith(value);
              if (!l.estVide ())
                    l.supprimerEnTete ();
         } */
    }

    /**
     * 
     * Supprime de la liste toutes les occurrences de la valeur fournie en argument.
     * @param value valeur à supprimer
     */
    public void	supprimerAll(E value)
    {
        if (!this.estVide())
            if (value.equals(tete()))	
            {
                this.supprimerEnTete();
                this.supprimerAll(value);
            }
            else	
                this.corps().supprimerAll(value);
    }
            
    /**
     * Renvoie une copie ("clone") de la liste. Les éléments eux-mêmes ne sont pas clonés.
     * @return  une nouvelle liste, copie conforme de la liste (this).
     */

    public RecursiveList<E> copie()
    {
        if (estVide())
            return new RecursiveList<>();
        return new RecursiveList<>(tete(), corps().copie());
    }
	
    /**
     * Détermine si la liste (this) est identique à celle fournie en argument.
     * 
     * @param autre liste d'éléments de "type" E
     * @return true iff les 2 listes sont identiques.
     */
    public boolean equals(RecursiveList<E> autre)
    {
        if (estVide())
            return autre.estVide();
        if (autre.estVide())
            return false;
        if (!tete().equals(autre.tete()))
            return false;
        return corps().equals(autre.corps());
    }

    /**
     * Modifie la liste en y concaténant celle fournie en argument.
     * @param autre liste à concaténer à la liste
     */
    public void concatener(RecursiveList<E> autre) // modification de la liste (this)
    {
        if (estVide())
            setList(autre);
        else
            corps().concatener(autre);
    }

    /**
     * Fournit une nouvelle liste indépendante formée des éléments de la liste (this)
     * suivis de ceux de la liste fournie en argument.
     * Remarque: les éléments eux-mêmes ne sont pas "clonés".
     * 
     * @param  autre     liste d'éléments de "type" E
     * @return      une nouvelle liste comportant les valeurs de la liste (this) 
     *              suivies de celles de l'autre liste.
     */
    public RecursiveList<E> concatenation(RecursiveList<E> autre) 
    {
        if (estVide()) return autre.copie();
        return new RecursiveList<>(tete(), corps().concatenation(autre));
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
        RecursiveList<E> local = this;
        RecursiveList<E> result = new RecursiveList<>();
        while (!local.estVide())
        {
            result.ajouterEnTete(local.tete());
            local = local.corps();
        }
        return result;
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
     * @return      true iff la liste est une "sous-liste" de l'autre liste.
     */
    public boolean estSousListe(RecursiveList<E> autre)
    {
        if (estVide())  return true;
        if (autre.estVide())    return false;
        if (tete().equals(autre.tete()))
            return corps().estSousListe(autre.corps());
        return estSousListe(autre.corps());
    }

    /**
     * Fournit un vecteur d'objets (Object) comportant les éléments de la liste.
     * Remarque: les éléments ne sont pas clonés.
     * 
     * @return  un vecteur (tableau) d'éléments comportant les éléments de la liste.
     */    
    public Object[] toArray()
    {
        ArrayList<E> aL = new ArrayList<>(); // par simplicité
        RecursiveList<E> l = this;
        while (!l.estVide())
        {
            aL.add(l.tete());
            l = l.corps();
        }
        return aL.toArray(); // invocation de la méthode toArray() de la classe ArrayList!
    }
    
    /**
     * Fournit un vecteur d'objets typés (E) comportant les éléments de la liste.
     * Remarque: les éléments ne sont pas clonés.
     * 
     * Exemple d'utisation:
     *      RecursiveList<String> l= new RecursiveList<>();
     *      // ... remplissage de la liste
     *      String[] mots = l.toArray(new String[0]);
     * 
     * @param   existingArrayOfE  vecteur (d'éléments de type E) existant.
     * @return  un vecteur d'éléments comportant les éléments de la liste.
     */    
    public E[] toArray(E[] existingArrayOfE)
    {
        ArrayList<E> aL = new ArrayList<>();
        RecursiveList<E> l = this;
        while (!l.estVide())
        {
            aL.add(l.tete());
            l = l.corps();
        }
        return aL.toArray(existingArrayOfE);
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
            this(); // invocation du 1er constructeur
            for (int index = array.length - 1; index >= 0; index --)
                ajouterEnTete(array[index]);
    }
    
    /**
     * Constructeur pour obtenir une liste à partir d'éléments fournis par une "Collection" Java.
     * L'ordre des éléments de la liste construite est l'ordre inverse de celui obtenu à l'aide 
     * d'un "for each statement" de la collection.
     * 
     * @param coll  une collection d'éléments de "type" E
     */
    public RecursiveList(Collection<E> coll)
    {
            this(); // invocation du 1er constructeur
            for (E elt: coll)
                ajouterEnTete(elt);
    }  
    
    public boolean isASet()       
    {
        if (this.estVide())
            return true;
        if (this.corps().contient(this.tete()))
                return false;
        return this.corps().isASet();
    }
    
    public void makeSet()
    {
        if (this.estVide())
            return;
        if (this.corps().contient(this.tete()))
        {
            this.supprimerEnTete();
            this.makeSet();
        }
        else
            this.corps().makeSet();
    }
    
    public RecursiveList<E> toSet()
    {
        if (this.estVide())
            return new RecursiveList<>();
        if (this.corps().contient(this.tete()))
            return this.corps().toSet();
        return new RecursiveList<>(this.tete(), this.corps().toSet());
    }
}
