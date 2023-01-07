package models.strategies;
import models.*;

public class Aleatoire implements Strategie{
    
    /** 
     * @param etudiant
     * @param cible
     * @return int
     */
    public int action(Etudiant etudiant, Etudiant cible){
        if(Math.random() > .5)
            return Offensif.attaquer(etudiant, cible);
        else
            return Defensif.soigner(etudiant, cible);
    }
}
