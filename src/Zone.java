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

    

    public void calculCombat(){
        //on ordonne les etudiants par initiative
        ArrayList<Etudiant> combattants = new ArrayList<>();
        combattants.addAll(combatantsJ1);
        combattants.addAll(combatantsJ2);
        Collections.sort(combattants, new Comparator<Etudiant>() {
            @Override
            public int compare(Etudiant e1, Etudiant e2){
                if(e1.initiative > e2.initiative) return -1;
                else return 1;
            }
        });
        System.out.println(combattants);

    }

    
}
