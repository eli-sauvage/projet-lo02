package models;
import models.strategies.*;
public class Etudiant {
    protected int credits, dexterite, force, resistance, consitution, initiative, joueur, id;
    private Zone zone;
    private boolean reserviste;
    //private Strategies strategie;
    private String nom;
    private Strategie strategie;

    public Etudiant(int joueur, int id) {
        this.credits = 30;
        this.joueur = joueur;
        this.id = id;
        this.nom = "Etudiant " + (id+1);
        this.resetStats();
    }

    

    
    /** 
     * @param cible
     * @return int
     */
    public int action(Etudiant cible){
        return strategie.action(this, cible);
    }

    public void resetStats(){
        this.force = 0;
        this.resistance = 0;
        this.consitution = 0;
        this.dexterite = 0;
        this.initiative = 0;
        this.reserviste = false;
    }

    
    /** 
     * @return int
     */
    // ----------getters/setters-------------
    public int getCredits() {
        return credits;
    }

    
    /** 
     * @return String
     */
    public String getNom() {
        return this.nom;
    }

    
    /** 
     * @param nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    /** 
     * @param credits
     */
    public void addCredits(int credits) {
        if (this.credits + credits > 30 + this.consitution) {
            this.credits = 30 + this.consitution;
        } else if (this.credits + credits <= 0) {
            this.credits = 0;
        } else {
            this.credits += credits;
        }
    }

    
    /** 
     * @return int
     */
    public int getDexterite() {
        return dexterite;
    }

    
    /** 
     * @param dexterite
     */
    public void setDexterite(int dexterite) {
        this.dexterite = dexterite;
    }

    public void incrDexterite() {
        this.dexterite++;
    }

    
    /** 
     * @return int
     */
    public int getForce() {
        return force;
    }

    
    /** 
     * @param force
     */
    public void setForce(int force) {
        this.force = force;
    }

    public void incrForce() {
        this.force++;
    }

    
    /** 
     * @return int
     */
    public int getResistance() {
        return resistance;
    }

    
    /** 
     * @param resistance
     */
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void incrResistance() {
        this.resistance++;
    }

    
    /** 
     * @return int
     */
    public int getConsitution() {
        return consitution;
    }

    
    /** 
     * @param consitution
     */
    public void setConsitution(int consitution) {
        this.consitution = consitution;
    }

    public void incrConstitution() {
        this.consitution++;
    }

    
    /** 
     * @return int
     */
    public int getInitiative() {
        return initiative;
    }

    
    /** 
     * @param initiative
     */
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void incrInitiative() {
        this.initiative++;
    }

    
    /** 
     * @return boolean
     */
    public boolean isReserviste() {
        return reserviste;
    }

    
    /** 
     * @param reserviste
     */
    public void setReserviste(boolean reserviste) {
        this.reserviste = reserviste;
    }

    
    /** 
     * @return Strategie
     */
    public Strategie getStrategie() {
        return strategie;
    }

    
    /** 
     * @param strategie
     */
    public void setStrategie(Strategie strategie) {
        this.strategie = strategie;
    }

    
    /** 
     * @param zone
     */
    public void setZone(Zone zone) {
        if(zone == null){
            this.zone = null;
            return;
        }
        if(!(this.zone == null))
        this.zone.getCombatantsJ(this.joueur).remove(this);
        this.zone = zone;
        this.zone.getCombatantsJ(this.joueur).add(this);

    }

    
    /** 
     * @return int
     */
    public int getJoueur(){
        return this.joueur;
    }

    
    /** 
     * @return Zone
     */
    public Zone getZone() {
        return this.zone;
    }

    
    /** 
     * @return boolean
     */
    public boolean getReserviste() {
        return this.reserviste;
    }

    
    /** 
     * @return String
     */
    @Override
    public String toString() {
        return "Etudiant j" + joueur + "#" + id + ( this.zone != null ? " z:" + Utils.zoneIndexToString(this.zone.getIndiceZone()) : "")+ " [credits=" + credits + ", d=" + dexterite
                + ", f=" + force
                + ", r="
                + resistance + ", c=" + consitution + ", i=" + initiative + ", reserv=" + reserviste
                + ", strat=" + strategie.toString().charAt(0) + "]";
    }
}
