package controllers;

import views.*;
import models.*;

public class VictoireController {
    VictoireView vv;
    private boolean continuer = false;
    private String gagnant;

    public VictoireController(String gagnant) {
        this.gagnant = gagnant;
        vv = new VictoireView(this);
        while (!continuer) {
            Utils.sleep(50);
        }
    }

    /**
     * debloque laa boucle afin de liberer le thread principal pour finir la partie
     */
    public void continuer() {
        continuer = true;
    }

    /**
     * @return String le nom du gagnant
     */
    public String getGagnant() {
        return gagnant;
    }
}
