public class Armee {
    private Etudiant[] etudiants;

    public void initDefaut(){
        etudiants = new Etudiant[20];
        for(int i=0; i<15; i++)etudiants[i] = new Etudiant();
        for(int i=15; i<19; i++)etudiants[i] = new EtudiantElite();
        etudiants[19] = new MaitreDuGobi();
    }
    public void statsAleatoires(){
        for(int i=0; i<400; i++){
            double etu = Math.round(Math.random() * 20);

        }
    }

    public Etudiant[] getEtudiants() {
        return etudiants;
    }

    public void setEtudiants(Etudiant[] etudiants) {
        this.etudiants = etudiants;
    }

    @Override
    public String toString() {
        String ret = "\nARMEE : ----------------------\n";
        for(int i=0; i<etudiants.length; i++)ret+="|  #" + i + " " + etudiants[i].toString() + "\n";
        return ret;
    }
}
