package controllers;
import models.Utils;
import views.*;

public class MenuController {
    private boolean running = true;
    MenuView menuV;
    public void display(){
        menuV = new MenuView(this);
        while(running){
            Utils.sleep(50);
        }
        menuV.fermer();
    }
    public void startGame(){
        running = false;
    }
}
