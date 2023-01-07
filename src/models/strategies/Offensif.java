package models.strategies;
import models.*;

public class Offensif implements Strategie{

    
    /** 
     * @param etudiant
     * @param cible
     * @return int
     */
    static public int attaquer(Etudiant etudiant, Etudiant cible) {
        int x = (int) Math.random() * 100;
        int degatReference = 10;
        double coeffDegats = Math.max(0, Math.min(100, 10 * etudiant.getForce() - 5 * cible.getResistance())) / 100;
        if (x < 40 + 3 * etudiant.getDexterite()) {// attaque réussie
            int degats = (int) Math.floor(Math.random() * (1 + coeffDegats) * degatReference);
            // System.out.println("attaque reussie : " + degats);
            cible.addCredits(-degats);
            return degats;
        }
        return 0;
    }

    
    /** 
     * @param etudiant
     * @param cible
     * @return int
     */
    public int action(Etudiant etudiant, Etudiant cible){
        return Offensif.attaquer(etudiant, cible);
    }
}
