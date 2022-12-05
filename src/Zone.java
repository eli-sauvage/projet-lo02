import java.util.*;
public class Zone {
    
    private ArrayList<Etudiant>  combatantsJ1, combatantsJ2;
    private int typeZone;
    //1 = BU, 2 = BDE, 3 = QA, 4 = HI, 5 = HS

    public Zone(int type){
        this.typeZone = type;
        combatantsJ1 = new ArrayList<Etudiant>();
        combatantsJ2 = new ArrayList<Etudiant>();
    }
    public ArrayList<Etudiant> getCombatantsJ(int joueurSelect) {
        if(joueurSelect==1) return combatantsJ1;
        if(joueurSelect==2) return combatantsJ2;
        else return null;
    }

    public int getTypeZone() {
        return typeZone;
    }

    

}
