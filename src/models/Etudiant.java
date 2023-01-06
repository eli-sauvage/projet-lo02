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

    // ----------getters/setters-------------
    public int getCredits() {
        return credits;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void addCredits(int credits) {
        if (this.credits + credits > 30 + this.consitution) {
            this.credits = 30 + this.consitution;
        } else if (this.credits + credits <= 0) {
            this.credits = 0;
        } else {
            this.credits += credits;
        }
    }

    public int getDexterite() {
        return dexterite;
    }

    public void setDexterite(int dexterite) {
        this.dexterite = dexterite;
    }

    public void incrDexterite() {
        this.dexterite++;
    }

    public int getForce() {
        return force;
    }

    public void setForce(int force) {
        this.force = force;
    }

    public void incrForce() {
        this.force++;
    }

    public int getResistance() {
        return resistance;
    }

    public void setResistance(int resistance) {
        this.resistance = resistance;
    }

    public void incrResistance() {
        this.resistance++;
    }

    public int getConsitution() {
        return consitution;
    }

    public void setConsitution(int consitution) {
        this.consitution = consitution;
    }

    public void incrConstitution() {
        this.consitution++;
    }

    public int getInitiative() {
        return initiative;
    }

    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }

    public void incrInitiative() {
        this.initiative++;
    }

    public boolean isReserviste() {
        return reserviste;
    }

    public void setReserviste(boolean reserviste) {
        this.reserviste = reserviste;
    }

    public Strategie getStrategie() {
        return strategie;
    }

    public void setStrategie(Strategie strategie) {
        this.strategie = strategie;
    }

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

    public int getJoueur(){
        return this.joueur;
    }

    public Zone getZone() {
        return this.zone;
    }

    public boolean getReserviste() {
        return this.reserviste;
    }

    @Override
    public String toString() {
        return "Etudiant j" + joueur + "#" + id + ( this.zone != null ? " z:" + Utils.zoneIndexToString(this.zone.getIndiceZone()) : "")+ " [credits=" + credits + ", d=" + dexterite
                + ", f=" + force
                + ", r="
                + resistance + ", c=" + consitution + ", i=" + initiative + ", reserv=" + reserviste
                + ", strat=" + strategie.toString().charAt(0) + "]";
    }
}
