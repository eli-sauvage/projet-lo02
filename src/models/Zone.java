package models;
import java.util.*;
import controllers.*;

public class Zone {

  private ArrayList<Etudiant> combatantsJ1, combatantsJ2;
  private int indiceZone;  // 1 = BU, 2 = BDE, 3 = QA, 4 = HI, 5 = HS
  private Combat combat;
  private int controlee = 0; //0 = personne
  private String nomZone;


  public String getNomZone() {
    return nomZone;
  }

  public int getControlee() {
    return controlee;
  }

  public void setControlee(int controlee) {
    this.controlee = controlee;
  }
  /*
  public void calculerControlee(){
    int survivantJ1 = 0, survivantJ2 = 0;
    for(Etudiant e:combatantsJ1){
      if(e.getCredits()>0)survivantJ1++;
    }
    for(Etudiant e:combatantsJ2){
      if(e.getCredits()>0)survivantJ2++;
    }
    if(survivantJ1 > 0 && survivantJ2 == 0)controlee = 1;
    if(survivantJ2 > 0 && survivantJ1 == 0)controlee = 2;
    System.out.println("ZONE " + indiceZone + " CONTROLEE");
  }
 */
  public Zone(int indice) {
    this.indiceZone = indice;
    this.nomZone = Utils.zoneIndexToString(indice);
    combatantsJ1 = new ArrayList<Etudiant>();
    combatantsJ2 = new ArrayList<Etudiant>();
  }

  public ArrayList<Etudiant> getCombatantsJ(int joueurSelect) {
    if (joueurSelect == 1)
      return combatantsJ1;
    if (joueurSelect == 2)
      return combatantsJ2;
    else
      return null;
  }

  public int getIndiceZone() {
    return indiceZone;
  }

  public Combat getCombat(CombatsController cc) {
    if (this.combat == null)
      this.combat = new Combat(combatantsJ1, combatantsJ2, this, cc);
    return this.combat;
  }
  public void resetCombat(){
    this.combat = null;
  }

  public void lancerCombat(ArrayList<Combat> combatsEnCours) {
    if(this.combat==null){
      System.err.println("combat lance sans avoir ete cree");
      return;
    }
    this.combat.start(combatsEnCours);
  }
}
