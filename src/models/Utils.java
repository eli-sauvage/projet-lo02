package models;
import java.io.*;
import java.awt.*;
/**
 * une classe ne comportant que des membres statiques, regrouppant les fonctions et attributs utiles pour les differentes classes
 */
public class Utils {
    /**
     * la couleur de fond pour les views
     */
    public static final Color bgColor = new Color(255, 128, 192);

    /**
     * efface la console
     */
    public static void clearConsole() {
        for (int i = 0; i < 50; i++)
            System.out.println();
    }

    
    /** 
     * verifie si un element est dans un array
     * @param array la liste
     * @param elemnt l'element a chercher
     * @return boolean true si l'element se trouve dans la liste
     * @param <T> le type de l'element
     */
    public static <T> boolean containsArray(T[] array, T elemnt) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == elemnt || array[i].equals(elemnt)) {
                return true;
            }
        }
        return false;
    }

    
    /** 
     * fait le lien entre l'indice d'une zone et son nom
     * @param zone l'indice de la zone
     * @return String le nom de la zone
     */
    public static String zoneIndexToString(int zone) {
        if (zone == 0)
            return "BU            ";
        else if (zone == 1)
            return "BDE           ";
        else if (zone == 2)
            return "Quartier Admin";
        else if (zone == 3)
            return "Halle Indus   ";
        else if (zone == 4)
            return "Halle Sportive";
        else
            return "";
    }
    /**
     * supprime les fichiers logs des combats du lancement precedent
    */
    public static void removeLogfiles() {
        for (int i = 0; i < 5; i++) {
            File file = new File(Integer.toString(i) + ".log");
            file.delete();
        }
    }

    
    /** 
     * bloque le thread un nombre de millisecondes specifie
     * @param t le temps de pause en ms
     */
    public static void sleep(int t) {
        try {
            Thread.sleep(t);
        } catch (Exception e) {
            System.out.println("probleme durant thread.sleep");
        }
    }
}
