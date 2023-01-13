package models;
import models.strategies.*;
/**
 * chaque etudiant se bat dans les differentes zones
 * il sont caracterises par differentes statistiques, s'il sont reservistes ou non, une zone, ainsi qu'une strategie
 */
public class Etudiant {
    /**
     * les differentes statistiques de l'etudiant
     */
    protected int credits, dexterite, force, resistance, consitution, initiative, joueur, id;
    private Zone zone;
    private boolean reserviste;
    private String nom;
    private Strategie strategie;

    /**
     * @param joueur le joueur a qui appartient l'etudiant
     * @param id un identifiant unique au joueur
     */
    public Etudiant(int joueur, int id) {
        this.credits = 30;
        this.joueur = joueur;
        this.id = id;
        this.nom = "Etudiant " + (id+1);
        this.resetStats();
    }
    
    /** 
     * effectue l'action (soin / attaque)
     * @param cible la cible
     * @return int le nombre de degats infliges / de points de vie soignes
     */
    public int action(Etudiant cible){
        return strategie.action(this, cible);
    }
    
    /**
     * remet a 0 toutes les statistiques de l'etudiant
     */
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
     * @param nom le nom a definir
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    
    /** 
     * met a jour les credits (possiblement une quantite negative)
     * @param credits les credits a ajouter (retirer si negatif)
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
     * @param dexterite la nouvelle dexterite
     */
    public void setDexterite(int dexterite) {
        this.dexterite = dexterite;
    }
    /**
     * augmente la dexterite
     */
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
     * @param force la nouvelel force
     */
    public void setForce(int force) {
        this.force = force;
    }
    /**
     * augmente la force
     */
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
     * @param resistance la nouvelle resistance
     */
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
    /**
     * augmente la resistance
     */
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
     * @param consitution la nouvelle constitution
     */
    public void setConsitution(int consitution) {
        this.consitution = consitution;
    }
    /**
     * augmente la constitution
     */
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
     * @param initiative la nouvelle initiative
     */
    public void setInitiative(int initiative) {
        this.initiative = initiative;
    }
    /**
     * augmente l'initiative
     */
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
     * @param reserviste la nouvelle strategie
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
     * @param strategie la nouvelle strategie de l'etudiant
     */
    public void setStrategie(Strategie strategie) {
        this.strategie = strategie;
    }

    
    /** 
     * met a jour la zone de l'etudiant ainsi que la liste des etudiants de cette zone et de la precedente
     * @param zone la zone où deployer l'etudiant
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
     * @return String l'etudiant sous forme de texte
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
