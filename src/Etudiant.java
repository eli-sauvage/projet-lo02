
public class Etudiant {
    protected int credits, dexterite, force, resistance, consitution, initiative;
    private boolean reserviste;
    private Strategies strategie;

    public Etudiant() {
        this.credits = 30;
    }
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
    public int getDexterite() {
        return dexterite;
    }
    public void setDexterite(int dexterite) {
        this.dexterite = dexterite;
    }
    public void incrDexterite(){
        this.dexterite++;
    }
    public int getForce() {
        return force;
    }
    public void setForce(int force) {
        this.force = force;
    }
    public void incrForce(){
        this.force++;
    }
    public int getResistance() {
        return resistance;
    }
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
    public void incrResistance(){
        this.resistance++;
    }
    public int getConsitution() {
        return consitution;
    }
    public void setConsitution(int consitution) {
        this.consitution = consitution;
    }
    public void incrConstitution(){
        this.consitution++;
    }
    public int getInitiative() {
        return initiative;
    }
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
    public void incrInitiative(){
        this.initiative++;
    }
    public boolean isReserviste() {
        return reserviste;
    }
    public void setReserviste(boolean auCombat) {
        this.reserviste = auCombat;
    }
    public Strategies getStrategie() {
        return strategie;
    }
    public void setStrategie(Strategies strategie) {
        this.strategie = strategie;
    }
    @Override
    public String toString() {
        return "Etudiant [credits=" + credits + ", dex=" + dexterite + ", for=" + force + ", res="
                + resistance + ", con=" + consitution + ", ini=" + initiative + ", reserviste=" + reserviste
                + ", strat=" + strategie + "]";
    }
}
