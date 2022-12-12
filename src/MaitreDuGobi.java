

public class MaitreDuGobi extends Etudiant{

    public MaitreDuGobi(int joueur, int id) {
        super(joueur, id);
        this.force = 2;
        this.dexterite = 2;
        this.resistance = 2;
        this.consitution = 10;
        this.initiative = 2;
    }

    @Override
    public String toString() {
        return "MaitreDuGobi :" + super.toString();
    }
    
}
