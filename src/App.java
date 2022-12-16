import java.awt.*;
import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        Utils.removeLogfiles();
        menu();
        Utils.sc.close();
    }

    private static void menu() {
        String msg = "";
        while (!msg.equals("4")) {
            System.out.println("Bienvenue dans C\'est du brutal!");
            System.out.println(
                    "1 - lancer une partie\n2 - lancer une partie avec les armées aléatoires\n3 - voir les règles\n4 - quitter");
            msg = Utils.input();
            if (msg.equals("1"))
                lancerPartie(true);
            else if (msg.equals("2"))
                lancerPartie(false);
            else if (msg.equals("3"))
                printRegles(false);
            else if(!msg.equals("4"))
                System.out.println("commande non reconnue");
        }
    }

    private static void lancerPartie(boolean reparition) {
        System.out.println("lancement de la partie");
        new Partie(reparition);
    }

    private static void printRegles(boolean inSrc) {
        System.out.println("ouverture des regles");
        File file =
                new File(System.getProperty("user.dir") + (inSrc ? "/src" : "") + "/regles.pdf");
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.open(file);
            } catch (Exception e) {
                e.printStackTrace();
                if (!inSrc)
                    printRegles(true);
            }
        }
        menu();
    }
}
