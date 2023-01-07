package controllers;
import views.*;
import models.*;

public class VictoireController {
    VictoireView vv;
    private boolean continuer = false;
    private String gagnant;

    public VictoireController(String gagnant){
        this.gagnant = gagnant;
        vv = new VictoireView(this);
        while(!continuer){
            Utils.sleep(50);
        }
    }
    public void continuer(){
        continuer = true;
    }
    
    /** 
     * @return String
     */
    public String getGagnant(){
        return gagnant;
    }
}
