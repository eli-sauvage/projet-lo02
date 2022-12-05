import java.util.ArrayList;

public class Zone {
    private Etudiant[] combatantsJ1, combatantsJ2;
    private int typeZone;
    //1 = BU, 2 = BDE, 3 = QA, 4 = HI, 5 = HS

    public Zone(int type){
        this.typeZone = type;
    }
    public Etudiant[] getCombatantsJ1() {
        return combatantsJ1;
    }

    public void setCombatantsJ1(Etudiant[] combatantsJ1) {
        this.combatantsJ1 = combatantsJ1;
    }

    public Etudiant[] getCombatantsJ2() {
        return combatantsJ2;
    }

    public int getTypeZone() {
        return typeZone;
    }

    public void setCombatantsJ2(Etudiant[] combatantsJ2) {
        this.combatantsJ2 = combatantsJ2;
    }

    public void calculCombat(){
        //on ordonne les etudiants par initiative
        ArrayList<Etudiant> combattants = new ArrayList<>();
        

    }
}
