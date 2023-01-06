package models;

public class Armee {
    private Etudiant[] etudiants;
    private int joueur;

    public Armee(int joueur) {
        this.joueur = joueur;
    }

    public void initDefaut() {
        int id = 0;
        etudiants = new Etudiant[20];
        for (int i = 0; i < 15; i++)
            etudiants[i] = new Etudiant(joueur, id++);
        for (int i = 15; i < 19; i++)
            etudiants[i] = new EtudiantElite(joueur, id++);
        etudiants[19] = new MaitreDuGobi(joueur, id++);
    }

    public void statsAleatoires() {
        System.out.println("stat aleatoire");
        for (int i = 0; i < 400; i++) {// répartit les 400 points aléatoirement dans l'armée
            int etu = (int) Math.floor(Math.random() * 20);
            int stat = (int) Math.floor(Math.random() * 5);
            if (stat == 0)
                this.etudiants[etu].incrForce();
            else if (stat == 1)
                this.etudiants[etu].incrDexterite();
            else if (stat == 2)
                this.etudiants[etu].incrResistance();
            else if (stat == 3)
                this.etudiants[etu].incrConstitution();
            else if (stat == 4)
                this.etudiants[etu].incrInitiative();
        }
        for (int i = 0; i < 20; i++) {// choisit une stratedie aleatoire
            Strategies strat = (Math.random() > .7) ? Strategies.defensif : Strategies.offensif;
            this.etudiants[i].setStrategie(strat);
        }
    }

    public Etudiant[] getEtudiants() {
        return etudiants;
    }

    @Override
    public String toString() {
        String ret = "\nARMEE : ----------------------\n";
        for (int i = 0; i < etudiants.length; i++)
            ret += "|  #" + i + " " + etudiants[i].toString() + "\n";
        return ret;
    }
}
