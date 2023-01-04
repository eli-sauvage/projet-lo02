package controllers;

import java.util.ArrayList;
import models.*;
import views.*;

public class CombatsController {
    ArrayList<Zone> zonesDeCombat;
    ArrayList<Combat> combats;
    CombatsView cv;
    boolean ready = false;
    public void init(ArrayList<Zone> zonesDeCombat, ArrayList<Combat> combats){
        this.zonesDeCombat = zonesDeCombat;
        this.combats = combats;
    }
    public void display(){
        cv = new CombatsView(this);
        while(!ready){
            Utils.sleep(50);
        }
    }
    public void combatsFinis(Zone zone, int gagnant){
        cv.finDuCombat(zone.getNomZone(), gagnant);
    }
    public int[] survivantsZone(int zoneIndex){
        int J1 = 0;
        for(Etudiant e:zonesDeCombat.get(zoneIndex).getCombatantsJ(1)){
            if(e.getCredits() > 0)J1++;
        }
        int J2 = 0;
        for (Etudiant e : zonesDeCombat.get(zoneIndex).getCombatantsJ(2)) {
            if (e.getCredits() > 0)
                J2++;
        }
        int[] res =  {J1, J2};
        return res;
    }
    public void combatUpdate(){
        cv.update();
    }
    public void lancerCombat(){
        ready = true;
    }
}
