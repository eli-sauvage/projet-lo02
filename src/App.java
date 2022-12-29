import java.awt.*;
import java.io.*;

public class App {
    public static void main(String[] args) throws Exception {
        Utils.removeLogfiles();
        menu();
        Utils.sc.close();
    }

    private static void menu() {
        GUI window = new GUI();
		window.menu.setVisible(true);
        System.out.println("1 - lancer une partie\n2 - lancer une partie avec les armées aléatoires\n3 - voir les règles\n4 - quitter");
       
        switch (Utils.sc.nextLine()) {
            case "1":
                lancerPartie(true);
            
            case "2":
                lancerPartie(false);
            
            case "3":
                printRegles(false);
            
        }
    }

    public static void lancerPartie(boolean reparition) {
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
