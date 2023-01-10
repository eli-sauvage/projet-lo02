package models.strategies;

import models.*;
/**
 * la strategie defensive permet a l'etudiant de soigner un autre etudiant
 */
public class Defensif implements Strategie {

    /**
     * l'etudiant soigne l'etudiant cible
     * @param etudiant l'etudiant qui soignera
     * @param cible    la cible
     * @return int le nombre de points de vie soignes
     */
    static public int soigner(Etudiant etudiant, Etudiant cible) {
        int x = (int) Math.random() * 100;
        if (x < 20 + etudiant.getDexterite() * 6) {// soin réussi
            int soin = (int) Math.floor(Math.random() * 0.6 * (10 + cible.getConsitution()));
            cible.addCredits(soin);
            return soin;
        }
        return 0;
    }

    /**
     * effectue l'action (ici, soin)
     * @param etudiant l'etudiant qui soignera
     * @param cible    la cible
     * @return int le nombre de points de vie soignes
     */
    public int action(Etudiant etudiant, Etudiant cible) {
        return Defensif.soigner(etudiant, cible);
    }
}
