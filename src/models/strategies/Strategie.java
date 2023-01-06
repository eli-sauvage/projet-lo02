package models.strategies;
import models.*;

public interface Strategie {
    public int action(Etudiant etudiant, Etudiant cible);
}
