package models;

/**
 * un maitre du gobi est un etudiant avec des statistiques plus eleves de base
 */
public class MaitreDuGobi extends Etudiant{
    /**
     * @param joueur le joueur a qui appartient le maitre du gobi
     * @param id un identifiant unique pour cet etudiant
     */
    public MaitreDuGobi(int joueur, int id) {
        super(joueur, id);
        this.resetStats();
        setNom("Maitre " + id);
    }

    
    /** 
     * @return String l'etudiant sous forme de texte
     */
    @Override
    public String toString() {
        return "MaitreDuGobi :" + super.toString();
    }

    /**
     * remet a 0 les stats de l'etudiant
     */
    @Override
    public void resetStats(){
        this.force = 2;
        this.dexterite = 2;
        this.resistance = 2;
        this.consitution = 10;
        this.initiative = 2;
        this.setReserviste(false);
    }
    
}
