package controllers;

import java.util.ArrayList;
import java.util.Arrays;
import models.*;
import views.*;

public class CombatsController {
    ArrayList<Zone> zones;
    ArrayList<Combat> combats;
    ChampDeBataille champ;
    CombatsView cv;
    boolean ready = false, continuer = false;
    public void init(ChampDeBataille champ, ArrayList<Combat> combats){
        this.zones = new ArrayList<Zone>(Arrays.asList(champ.getZones()));
        this.champ = champ;
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
        while(!continuer){
            Utils.sleep(50);
        }
    }
    public int[] survivantsZone(int zoneIndex){
        int J1 = 0;
        Zone zone = null;
        for(Zone z:zones){
            if(z.getIndiceZone() == zoneIndex) zone = z;
        }
        for(Etudiant e:zone.getCombatantsJ(1)){
            if(e.getCredits() > 0)J1++;
        }
        int J2 = 0;
        for (Etudiant e : zone.getCombatantsJ(2)) {
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
    public void continuer(){
        continuer = true;
    }
    public boolean isZoneControlee(int indiceZone){
        return champ.getZone(indiceZone).getControlee() != 0;
    }
}
