package models;

public class EtudiantElite extends Etudiant {

    public EtudiantElite(int joueur, int id) {
        super(joueur, id);
        this.resetStats();
        setNom("Elite " + id);
    }

    
    /** 
     * @return String l'etudiant sous forme de texte
     */
    @Override
    public String toString() {
        return "EtudiantElite :" + super.toString();
    }
    /**
     * remet a 0 les stats de l'etudiant
     */
    @Override
    public void resetStats() {
        this.force = 1;
        this.dexterite = 1;
        this.resistance = 1;
        this.consitution = 5;
        this.initiative = 1;
        this.setReserviste(false);
    }

}
