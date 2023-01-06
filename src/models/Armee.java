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
