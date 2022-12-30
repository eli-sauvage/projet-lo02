import java.util.*;

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

    public void parametrageTroupes() {
        System.out.println("souhaitez vous :\n1 - répartir les 400pts aléatoirement\n2 - les répartir à la main");
        String reponse = Utils.input();
        if (reponse.equals("1")) {
            statsAleatoires();
            String msg = "";
            do {
                System.out.println("parametrage fini\n1 - afficher votre armee\nENTREE - continuer");
                msg = Utils.input();
                if (msg.equals("1")) {
                    System.out.println(this);
                }
            } while (!msg.isEmpty());
        } else if (reponse.equals("2")) {
            System.out.println("TODO");
            parametrageTroupes();
        } else {
            System.out.println("eureur");
            parametrageTroupes();
        }
    }

    public void choisirReservistes() {
        System.out.println(
                "choix des reservistes\n| entrez les indices des étudiants reservistes séparés par des espaces\n| entrez \"a\" pour afficher votre armée");
        String[] mots = Utils.input().split(" ");
        if (Utils.<String>containsArray(mots, "a")) {// verif si on doit afficher l'armee
            System.out.println(this);
            choisirReservistes();
            return;
        }
        if (mots.length != 5) {// verif si bon nb d'elem
            System.out.println("vous devez choisir exactement 5 reservistes");
            choisirReservistes();
            return;
        }
        int[] indices = new int[5];
        for (int i = 0; i < mots.length; i++) {// verif si les entrees sont des nb
            int nb;
            try {
                nb = Integer.parseInt(mots[i]);
            } catch (Exception e) {
                System.err.println("entrée " + mots[i] + "invalide");
                choisirReservistes();
                return;
            }
            indices[i] = nb;
        }
        for (int indice : indices) {// verif si les indices sont corrects
            if (indice > 19 || indice < 0) {
                System.out.println("indice d'etudiant " + indice + " invalide");
                choisirReservistes();
                return;
            }
        }
        for (int indice : indices) {
            etudiants[indice].setReserviste(true);
        }
        String rep = "";
        while (!rep.isEmpty()) {
            System.out.println("les reservistes ont été mis à jour\n1 - afficher l'armee\nENTREE - continuer");
            rep = Utils.input();
            if (rep.equals("1"))
                System.out.println(this);
        }
    }

    public void reservistesAleatoires() {
        ArrayList<Integer> indiceReserviste = new ArrayList<>();
        while (indiceReserviste.size() < 5) {
            int i = (int) Math.floor(Math.random() * 20);
            if (!indiceReserviste.contains(i))
                indiceReserviste.add(i);
        }
        for (int i : indiceReserviste)
            this.etudiants[i].setReserviste(true);
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
