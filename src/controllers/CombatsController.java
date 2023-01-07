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
    
    /** 
     * initialise le controller
     * @param champ
     * @param combats
     */
    public void init(ChampDeBataille champ, ArrayList<Combat> combats){
        this.zones = new ArrayList<Zone>(Arrays.asList(champ.getZones()));
        this.champ = champ;
        this.combats = combats;
    }
    /*
     * affiche la vue
     */
    public void display(){
        cv = new CombatsView(this);
        while(!ready){
            Utils.sleep(50);
        }
    }
    
    /** 
     * notifie la vue de la fin du combat
     * @param zone
     * @param gagnant
     */
    public void combatsFinis(Zone zone, int gagnant){
        cv.finDuCombat(zone.getNomZone(), gagnant);
        while(!continuer){
            Utils.sleep(50);
        }
    }
    
    /** 
     * retourne les survivants de la zone (combat en cours)
     * @param zoneIndex
     * @return int[]
     */
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
    /*
     * notifie la vue d'un changement dans l'etat du combat
     */
    public void combatUpdate(){
        cv.update();
    }
    /*
     * sort de la boucle afin de debloquer le thread principal lorsque le joueur lance le combat
     */
    public void lancerCombat(){
        ready = true;
    }
    /*
     * sort de la boucle afin de debloquer le thread principal lorsque le joueur est pret a passer a la treve
     */
    public void continuer(){
        continuer = true;
    }
    
    /** 
     * retourne true si la zone specifiee est controlee
     * @param indiceZone
     * @return boolean
     */
    public boolean isZoneControlee(int indiceZone){
        return champ.getZone(indiceZone).getControlee() != 0;
    }
}
