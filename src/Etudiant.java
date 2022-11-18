
public class Etudiant {
    private int credits, dexterité, force, resistance, consitution, initiative;
    private boolean auCombat;
    private Strategies strategie;


    
    public void soigner(Etudiant etudiant){

    }
    public void attaquer(Etudiant etudiant){
        
    }


    //----------getters/setters-------------
    public int getCredits() {
        return credits;
    }
    public void setCredits(int credits) {
        this.credits = credits;
    }
    public int getDexterité() {
        return dexterité;
    }
    public void setDexterité(int dexterité) {
        this.dexterité = dexterité;
    }
    public int getForce() {
        return force;
    }
    public void setForce(int force) {
        this.force = force;
    }
    public int getResistance() {
        return resistance;
    }
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
    public int getConsitution() {
        return consitution;
    }
    public void setConsitution(int consitution) {
        this.consitution = consitution;
    }
    public int getInitiative() {
        return initiative;
    }
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
    public boolean isAuCombat() {
        return auCombat;
    }
    public void setAuCombat(boolean auCombat) {
        this.auCombat = auCombat;
    }
    public Strategies getStrategie() {
        return strategie;
    }
    public void setStrategie(Strategies strategie) {
        this.strategie = strategie;
    }
}
