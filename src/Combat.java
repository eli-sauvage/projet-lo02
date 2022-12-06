import java.io.*;
import java.lang.Thread.State;
import java.util.*;

public class Combat implements Runnable {
    private ArrayList<Etudiant> combattants;
    private int gagnant = 0;// 0 = aucun gagnant
    private Thread t;
    private int zone;
    private ArrayList<Combat> autresCombats;

    public Combat(ArrayList<Etudiant> etudiantsJ1, ArrayList<Etudiant> etudiantsJ2, int zone) {
        this.combattants = new ArrayList<>();
        this.combattants.addAll(etudiantsJ1);
        this.combattants.addAll(etudiantsJ2);
        this.zone = zone;
    }

    public void start(ArrayList<Combat> combatsEnCours) {
        this.autresCombats = new ArrayList<>();
        this.autresCombats.addAll(combatsEnCours);
        if (t != null)
            return;
        t = new Thread(this);
        t.start();
    }

    public State getState(){
        return this.t.getState();
    }

    public void run() {
        do {
            Combat fini = null;
            Iterator<Combat> c = autresCombats.iterator();
            while (c.hasNext()) {
                Combat comb = c.next();
                if (comb.finished())
                    fini = comb;
            }
            if (fini != null) {
                System.out.println("arret car le combat " + fini.zone + " est fini");
                return;
            }
            combattants.removeIf(e -> (e.credits == 0));
            Collections.sort(combattants, new Comparator<Etudiant>() {
                @Override
                public int compare(Etudiant e1, Etudiant e2) {
                    if (e1.initiative > e2.initiative)
                        return -1;
                    else
                        return 1;
                }
            });
            Iterator<Etudiant> i = combattants.iterator();
            while (i.hasNext()) {
                Etudiant etu = i.next();
                if(etu.credits == 0) continue;
                // ------------recherche de l'etudiant cible----------
                ArrayList<Etudiant> combattantsCopy = new ArrayList<>();
                combattantsCopy.addAll(combattants);
                combattantsCopy.removeIf(e -> (e.credits == 0)); // on retire les morts dans le choix de la
                                                                 // cible
                if (etu.getStrategie() == Strategies.offensif)
                    combattantsCopy.removeIf(e -> (e.joueur == etu.joueur));
                else if ( // offensif -> on retire les etudiants de la meme equipe
                etu.getStrategie() == Strategies.defensif)
                    combattantsCopy.removeIf(e -> (e.joueur != etu.joueur)); // defensif -> on retire les
                                                                             // etudiants de
                if (combattantsCopy.size() != 0) { // seulement si une cible existe
                    // l'équipe adverse
                    Etudiant cible = Collections.min(combattantsCopy, new Comparator<Etudiant>() { // dans tous les cas,
                                                                                                   // on cherche
                                                                                                   // l'etudiant avec le
                                                                                                   // moins de credits
                        @Override
                        public int compare(Etudiant e1, Etudiant e2) {
                            if (e1.credits > e2.credits)
                                return 1;
                            else
                                return -1;
                        }
                    });
                    // ------attaque/soins--------------
                    int score = 0; 
                    if (etu.getStrategie() == Strategies.offensif)
                        score = etu.attaquer(cible);
                    else if (etu.getStrategie() == Strategies.defensif)
                        score = etu.soigner(cible);
                    String msg = etu + ((etu.getStrategie() == Strategies.offensif) ? "\nattaque\n" : "\nsoigne\n")
                            + cible + ": " + score +  
                            "\n----------------------------------";
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(Integer.toString(zone) + ".log", true));
                        writer.append(msg + "\n");

                        writer.close();
                    } catch (Exception e) {

                    }

                }
            }
            // ---------determination si joueur gagnant------------
            ArrayList<Etudiant> survivantsJ1 = new ArrayList<Etudiant>();
            ArrayList<Etudiant> survivantsJ2 = new ArrayList<Etudiant>();
            i = combattants.iterator();
            while (i.hasNext()) {
                Etudiant e = i.next();
                if (e.credits != 0) {
                    if (e.joueur == 1)
                        survivantsJ1.add(e);
                    else if (e.joueur == 2)
                        survivantsJ2.add(e);
                }
            }
            if (survivantsJ1.size() == 0)
                gagnant = 2;
            else if (survivantsJ2.size() == 0)
                gagnant = 1;
            else
                gagnant = 0;
            // System.out.println("------FIN TOUR" + nbTours++ + "
            // -------------------------------");
        } while (gagnant == 0);
        System.out.println("combat de la zone " + zone + " termine");
    }

    public boolean finished() {
        return gagnant != 0;
    }
}
