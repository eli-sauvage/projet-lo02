package models;
import java.util.*;
import controllers.*;

public class Zone {

  private ArrayList<Etudiant> combatantsJ1, combatantsJ2;
  private int indiceZone;  // 1 = BU, 2 = BDE, 3 = QA, 4 = HI, 5 = HS
  private Combat combat;
  private int controlee = 0; //0 = personne
  private String nomZone;


  public Zone(int indice) {
    this.indiceZone = indice;
    this.nomZone = Utils.zoneIndexToString(indice);
    combatantsJ1 = new ArrayList<Etudiant>();
    combatantsJ2 = new ArrayList<Etudiant>();
  }

  /** 
   * retourne le nom de la zone
   * @return String le nom de la zone
   */
  public String getNomZone() {
    return nomZone;
  }

  
  /** 
   * pour savoir si la zone est controlee
   * @return int 0 si personne ne controlle la zone / le numero du joueur qui controle sinon
   */
  public int getControlee() {
    return controlee;
  }

  
  /** 
   * @param controlee
   */
  public void setControlee(int controlee) {
    this.controlee = controlee;
  }
  
  /** 
   * retourne la liste des combattants de la zone pour un joueur donne
   * @param joueurSelect l'indice du joueur
   * @return ArrayList<Etudiant> la liste des combattants
   */
  public ArrayList<Etudiant> getCombatantsJ(int joueurSelect) {
    if (joueurSelect == 1)
      return combatantsJ1;
    if (joueurSelect == 2)
      return combatantsJ2;
    else
      return null;
  }

  
  /** 
   * retoune l'indice de la zone
   * @return int
   */
  public int getIndiceZone() {
    return indiceZone;
  }

  
  /** 
   * retourne un combat specifique (pattern singleton)
   * @param cc le controller (necessaire pour l'initialisation, pour les notifications)
   * @return Combat le combat en question
   */
  public Combat getCombat(CombatsController cc) {
    if (this.combat == null)
      this.combat = new Combat(combatantsJ1, combatantsJ2, this, cc);
    return this.combat;
  }
  public void resetCombat(){
    this.combat = null;
  }

  
  /** 
   * @param combatsEnCours
   */
  public void lancerCombat(ArrayList<Combat> combatsEnCours) {
    if(this.combat==null){
      System.err.println("combat lance sans avoir ete cree");
      return;
    }
    this.combat.start(combatsEnCours);
  }
}
