package models.strategies;
import models.*;
/**
 * cette interface est le coeur du patron de conception Strategy, qui permet aux strategie d'aparraitre sous le meme type (Strategie)
 */
public interface Strategie {
    /**
     * l'action que l'etudiant effectuera en fct de sa strategie
     * @param etudiant l'etudiant
     * @param cible la cible
     * @return les degats subits ou les points de vies soignes
     */
    public int action(Etudiant etudiant, Etudiant cible);
}
