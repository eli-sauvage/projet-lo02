import models.Partie;
import models.Utils;

public class App {
    public static void main(String[] args) throws Exception {
        Utils.removeLogfiles();
        lancerPartie(true);
        Utils.sc.close();
    }

    public static void lancerPartie(boolean reparition) {
        System.out.println("lancement de la partie");
        new Partie(false);
    }

   
}
