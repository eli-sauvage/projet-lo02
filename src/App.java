
import java.awt.*;
import java.io.*;

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
    }
}
