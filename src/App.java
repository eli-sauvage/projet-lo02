import models.Partie;
import models.Utils;
/**
 * main class
 */
public class App {
    public static void main(String[] args) throws Exception {
        Utils.removeLogfiles();
        lancerPartie();
        Utils.sc.close();
    }

    
    /**
     * lance la partie
     */
    public static void lancerPartie() {
        System.out.println("lancement de la partie");
        new Partie();
    }

   
}
