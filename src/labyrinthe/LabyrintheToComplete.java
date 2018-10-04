package labyrinthe;

import ressourcesProfesseur.Pile;

import java.util.Random;

/**
 * @author Erick Timmerman  (Erick.Timmerman@univ-lille.fr)
 */
public class LabyrintheToComplete 
{

    private static final char MUR = '#',
                              VIDE = ' ',
                              MARQUE = '.',
                              DEPART = 'D',
                              ARRIVEE = 'A',
                              PASSAGE = '+';
    
    private static final int  DEFAULT_PERCENTAGE = 50,
                              DEFAULT_HEIGHT = 20,
                              DEFAULT_WIDTH = 40;

    private char [][] tab;
    private int nbLignes, nbColonnes;
    private Coord depart, arrivee;
    
    /* 
     *  Constructeurs avec initialisations et remplissage aléatoire du tableau
     */
    public LabyrintheToComplete()
    {
        this(DEFAULT_PERCENTAGE);
    }

    public LabyrintheToComplete(int pourcentage)
    {
        this(DEFAULT_HEIGHT, DEFAULT_WIDTH, pourcentage);
    }

    public LabyrintheToComplete(int hauteur, int largeur, int pourcentage)
    {
        tab = new char[hauteur + 2][largeur + 2]; // + 2 pour le mur tout autour!
        nbLignes = hauteur;
        nbColonnes = largeur;

        initialiserBords(); // placer un mur sur les bords

        for (int i = 1; i <= nbLignes; i++)
            for (int j = 1; j <= nbColonnes; j++)
                tab[i][j] = VIDE;

        remplissageAleatoire(pourcentage);

        tab[1][1] = DEPART;                     // Choix arbitraire!
        depart = new Coord(1, 1);

        tab[nbLignes][nbColonnes] = ARRIVEE;    // idem.
        arrivee = new Coord(nbLignes, nbColonnes);
    }
    
    private void initialiserBords()
    {
        for (int i = 0; i < nbColonnes + 2; i++)
        {
            tab[0][i] = MUR;
            tab[nbLignes + 1][i] = MUR;
        }
        for (int i = 1; i < nbLignes + 1; i++)
        {
            tab[i][0] = MUR;
            tab[i][nbColonnes +1] = MUR;
        }
    }


    private void remplissageAleatoire(int pourcentage)
    {
        Random rand = new Random();
        int nbMurs = (nbLignes * nbColonnes * pourcentage) / 100;
        for (int i = 0; i < nbMurs; i++)
            tab[rand.nextInt(nbLignes) + 1][rand.nextInt(nbColonnes) + 1] = MUR;
    }
    
    public void effacerMarques()
    {
        for (int i = 1; i <= nbLignes; i++)
            for (int j = 1; j <= nbColonnes; j++)
                if (tab[i][j] == MARQUE || tab[i][j] == PASSAGE) 
                    tab[i][j] = VIDE;
    }

    @Override
    public String toString()
    {
        StringBuilder result = new StringBuilder();
        for (char[] ligne : tab)
        {
            result.append(new String(ligne)).append("\n"); // cf. doc Java
        }
        result.append("Départ: ").append(depart).append("  Arrivée: ").append(arrivee).append("\n");
        return result.toString();
    }
    
    public boolean rechercheChemin()
    {
//        Création de la pile de suivie de l'exploration
        Pile<Coord> exploration = new Pile<>();
        exploration.empiler(depart);
//        les coordonées de la case sur laquelle on se trouve
        int x;
        int y;

        while (!exploration.estVide() && !exploration.sommet().equals(arrivee)) {
            x = exploration.sommet().getLigne();
            y = exploration.sommet().getColonne();

//            Regarder à gauche
            if (tab[y][x + 1] == VIDE || tab[y][x + 1] == ARRIVEE) {
                tab[y][x + 1] = MARQUE;
                exploration.empiler(new Coord(x + 1, y));
            }
//            Regarder en bas
            else if (tab[y + 1][x] == VIDE || tab[y + 1][x] == ARRIVEE) {
                tab[y + 1][x] = MARQUE;
                exploration.empiler(new Coord(x, y + 1));
            }
//            Regarder à droite
            else if (tab[y][x - 1] == VIDE || tab[y][x - 1] == ARRIVEE) {
                tab[y][x - 1] = MARQUE;
                exploration.empiler(new Coord(x - 1, y));
            }
//            Regarder en haut
            else if (tab[y - 1][x] == VIDE || tab[y - 1][x] == ARRIVEE) {
                tab[y - 1][x] = MARQUE;
                exploration.empiler(new Coord(x, y -1));
            }
//            Si aucun chemin n'est trouvé
            else {
                exploration.depiler();
            }
        }

        if (!exploration.estVide()) {
            return true;
        }
        return false;
    }
}
