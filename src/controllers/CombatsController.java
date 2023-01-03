package controllers;

import java.util.ArrayList;
import models.*;
import views.*;

public class CombatsController {
    ArrayList<Zone> zonesDeCombat;
    ArrayList<Combat> combats;
    CombatsView cv;
    public CombatsController(ArrayList<Zone> zonesDeCombat, ArrayList<Combat> combats) {
        this.zonesDeCombat = zonesDeCombat;
        this.combats = combats;
    }
    public void display(){
        cv = new CombatsView(this);
    }
    public void combatsFinis(){
        
    }
    
}
