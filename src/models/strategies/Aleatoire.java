package models.strategies;

import models.*;
/**
 * la strategie aleatoire permet de choisir aleatoireement a chaque coup entre une strategie offensive et defensive
 */
public class Aleatoire implements Strategie {

    /**
     * effectue l'action aleatoire
     * @param etudiant l'etudiant qui effectuera l'action
     * @param cible    la cible
     * @return int le nombre de degats inflige / de points de vie soignes
     */
    public int action(Etudiant etudiant, Etudiant cible) {
        if (Math.random() > .5)
            return Offensif.attaquer(etudiant, cible);
        else
            return Defensif.soigner(etudiant, cible);
    }
}
