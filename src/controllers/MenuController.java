package controllers;

import models.Utils;
import views.*;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class MenuController {
    private boolean running = true;
    MenuView menuV;
    /*
     * affiche la vue
     */
    public void display() {
        menuV = new MenuView(this);
        while (running) {
            Utils.sleep(50);
        }
        menuV.fermer();
    }

    /*
     * sort de la boucle afin de debloquer le thread principal lorsque le joueur souhaite lancer la partie
     */
    public void startGame() {
        running = false;
    }

    /*
     * ouvre les regles en format pdf dans le navigateur de la machine
     */
    public void afficherRegles() {

        // Définissez le chemin d'accès au fichier PDF
        String filePath = "ressources/regles.pdf";

        // Créez un objet File à partir du chemin d'accès
        File file = new File(filePath);

        // Vérifiez que le fichier existe avant de l'ouvrir
        if (file.exists()) {
            try {
                // Ouvrez le fichier PDF dans le navigateur par défaut de l'utilisateur
                Desktop.getDesktop().browse(file.toURI());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Le fichier n'existe pas.");
        }

    }
}
