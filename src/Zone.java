import java.util.*;

public class Zone {

  private ArrayList<Etudiant> combatantsJ1, combatantsJ2;
  private int indiceZone;  // 1 = BU, 2 = BDE, 3 = QA, 4 = HI, 5 = HS
  private Combat combat;
  private int controlee = 0; //0 = personne


  public Zone(int type) {
    this.indiceZone = type;
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

  public Combat getCombat() {
    if (this.combat == null)
      this.combat = new Combat(combatantsJ1, combatantsJ2, indiceZone);
    return this.combat;
  }

  public void lancerCombat(ArrayList<Combat> combatsEnCours) {
    if(this.combat==null){
      System.err.println("combat lance sans avoir ete cree");
      return;
    }
    this.combat.start(combatsEnCours);
  }
}
