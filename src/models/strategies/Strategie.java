package models.strategies;
import models.*;
/**
 * cette interface est le coeur du patron de conception Strategy, qui permet aux strategie d'aparraitre sous le meme type (Strategie)
 */
public interface Strategie {
    public int action(Etudiant etudiant, Etudiant cible);
}
