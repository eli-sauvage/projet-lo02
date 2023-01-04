package controllers;
import views.*;
import models.*;

public class VictoireController {
    VictoireView vv;
    private boolean continuer = false;
    public VictoireController(){
        vv = new VictoireView(this);
        while(!continuer){
            Utils.sleep(50);
        }
    }
    public void continuer(){
        continuer = true;
    }
}
