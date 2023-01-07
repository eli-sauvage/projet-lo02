import models.Partie;
import models.Utils;

public class App {
    
    /** 
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        Utils.removeLogfiles();
        lancerPartie(true);
        Utils.sc.close();
    }

    
    /** 
     * @param reparition
     */
    public static void lancerPartie(boolean reparition) {
        System.out.println("lancement de la partie");
        new Partie(false);
    }

   
}
