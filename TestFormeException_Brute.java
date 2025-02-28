import java.io.InputStreamReader; //pour la saisie d'une touche quelconque
import java.io.IOException;
import java.util.*;
// Création des trois exceptions LARG_NEGATIF, LONG_NEGATIF et RAYON_NEGATIF
class LARG_NEGATIF extends Exception{
    LARG_NEGATIF(){super();}
}
class LONG_NEGATIF extends Exception{
    LONG_NEGATIF(){super();}
}
class RAYON_NEGATIF extends Exception{
    RAYON_NEGATIF(){super();}
}
interface DbServices{
    void addToDb();
    void delFromDb();
}
abstract class Forme{
    int color;
    Forme(int color){this.color = color;}
    abstract float surf();
    abstract void show();
    abstract void drow();
}

class Rect extends Forme implements  DbServices{
    float l, L;
    public Rect(float l, float L, int color) throws LARG_NEGATIF, LONG_NEGATIF {
        super(color);
        if (l<=0) throw new LARG_NEGATIF();
        if (l<=0) throw new LONG_NEGATIF();

        this.l=l;  this.L=L;

    }
    //Implémentation de drow() dans Rect
    public void drow(){
        System.out.println();
        System.out.println("\t\t\t\t "+L+" / "+l);
        System.out.println("\t\t\t\t * * * * * * * * * * * ");
        System.out.println("\t\t\t\t *     RECTANGLE     * ");
        System.out.println("\t\t\t\t *                   * ");
        System.out.println("\t\t\t\t * * * * * * * * * * * ");
    }
    //Implémentation de show() dans Rect
    public void show(){
        System.out.println("\t-Rectangle [couleur:"+color+"], long. ["+ L +"] " +
                "et largeur ["+l+"]");
    };
    //Implémentation de addToDb() dans Rect
    public void addToDb(){
        System.out.println("\tAjout à la BD " +
                "du Rectangle [couleur:"+color+"], long. ["+ L +"] " +
                "et largeur ["+l+"]");
    };

    //Implémentation de delFromDb() dans Rect
    public void delFromDb(){
        System.out.println("\tSupp dans la BD " +
                "du Rectangle [couleur:"+color+"], long. ["+ L +"] " +
                "et largeur ["+l+"]");
    };

    //Implémentation de surf() dans Rect
    float surf(){ return L*l;}
}
class Cercle extends Forme  implements DbServices  {
    float r;
    public Cercle(float r, int color) throws RAYON_NEGATIF {
         super(color);
         if (r<=0) throw new RAYON_NEGATIF();
    }

    //Implémentation de show() dans Cercle
    public void show(){
        System.out.println("\t-Cercle [couleur:"+color+"], Ray. ["+ r +"] " );
    };
    //Implémentation de drow() dans Cercle
    public void drow(){
        System.out.println();
        System.out.println("\t Rayon = "+r);
        System.out.println("\t\t     *   *      ");
        System.out.println("\t\t  *         *   ");
        System.out.println("\t\t *  CERCLE   *  ");
        System.out.println("\t\t *           *  ");
        System.out.println("\t\t *           *  ");
        System.out.println("\t\t  *         *   ");
        System.out.println("\t\t     *   *      ");
    };


    //Implémentation de addToDb() dans Cercle
    public void addToDb(){
        System.out.println("\tAjout à la BD " +
                "du Cercle [couleur:"+color+"], Ray. ["+ r +"] " );
    };
    //Implémentation de delFromDb() dans Cercle
    public void delFromDb(){
        System.out.println("\tsupp dans la BD " +
                "du Cercle [couleur:"+color+"], Ray. ["+ r +"] " );
    };

    //Implémentation de surf() dans Cercle
    float surf(){ return (float)(3.14*r*r);}
}
public class TestFormeException_Brute{
    public static void main(String []args) throws IOException {

        // Afficher le logo de l'application
        System.out.println();
        System.out.println("\t\t**********      *********      ********     ****      ****   ");
        System.out.println("\t\t**********    **         **    **    **     *****    *****   ");
        System.out.println("\t\t**           **           **   **   **      ***  ****  ***   ");
        System.out.println("\t\t******       **           **   *****        ***   **   ***   ");
        System.out.println("\t\t******       **           **   *** ***      ***        ***   ");
        System.out.println("\t\t**            **         **    ***   ***    ***        ***   ");
        System.out.println("\t\t**              *********      ***     ***  ***        ***   ");
        System.out.println("\n\t\t\t\t\t\t\t\t\t\tPowred By | D-EEA ISSATMH 2025");

        //Créer une collection pour stocker des rectangles et des cercles
        Vector<Forme> dessin = new Vector<Forme>();

        //Charger la collection dessin avec 3 rectangles et 2 cercles
        System.out.println("\n1. CHARGEMENT DES FORMES : 3Rectangles + 2Cercles...\n");
        try {
            dessin.add(new Rect(10, 50, 1));
            dessin.add(new Rect(70, 20, 4));
            dessin.add(new Rect(100, 50, 1));
        }
        catch (LARG_NEGATIF e){
            System.out.println("ATT LARG.NEGATIF");
        }
        catch (LONG_NEGATIF e){
            System.out.println("ATT LONG_NEGATIF");
        }
        try {
            dessin.add(new Cercle(10, 7));
            dessin.add(new Cercle(10, 3));
        }
        catch (RAYON_NEGATIF e){
            System.out.println("ATT RAYON_NEGATIF");
        }

        // Lister les dimensions des formes de la collection <dessin>
        System.out.println("3. LISTE DES DIMENSIONS DES FORMES DU DESSIN");
        if (dessin.size()==0){System.out.println("\t\t (Liste vide.)"); return;}
        for (Forme F : dessin) F.show();

        // Enregistrer les formes dans la BD (simulation)
        System.out.println("2. ENREGISTREMENT DES FORMES DANS LA BD");
        for (Forme F : dessin)((DbServices)F).addToDb();

        // Dessiner(drow) les formes sur l'écran
        System.out.println("4. TRAÇAGE DES FORMES DU DESSIN\n\tTapper une touche!!...");
        new InputStreamReader(System.in).read();
        for (Forme F : dessin)F.drow();
    }
}