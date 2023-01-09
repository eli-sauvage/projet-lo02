package models;


public class MaitreDuGobi extends Etudiant{

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
