package models.strategies;
import models.*;

public class Defensif implements Strategie{
    static public int soigner(Etudiant etudiant, Etudiant cible) {
        int x = (int) Math.random() * 100;
        if (x < 20 + etudiant.getDexterite() * 6) {// soin réussi
            int soin = (int) Math.floor(Math.random() * 0.6 * (10 + cible.getConsitution()));
            cible.addCredits(soin);
            return soin;
        }
        return 0;
    }


    public int action(Etudiant etudiant, Etudiant cible){
        return Defensif.soigner(etudiant, cible);
    }
}
