package models;

import java.io.*;
import java.lang.Thread.State;
import java.util.*;
import controllers.*;
import models.strategies.*;
/**
 * une instance de Combats est cree a pour chaque zone, a chaque tour de jeu
 * elle permet de calculer l'issu d'un combat, les etudiants morts, les points de vie restant aux autres etc
 * u ncombat s'arrete si tous les etudiants d'un joueur sont morts, ou si un autre combat s'est termine avant
 */
public class Combat implements Runnable {
    private ArrayList<Etudiant> combattants;
    private int gagnant = 0;// 0 = aucun gagnant
    private Thread t;
    private Zone zone;
    private ArrayList<Combat> autresCombats;
    private CombatsController combatsController;
    /**
     * @param etudiantsJ1 les etudiants du J1 dans la zone du combat
     * @param etudiantsJ2 les etudiants du J2 dans la zone du combat
     * @param zone la zone de combat
     * @param combatsController le controller de la view Combats, pour le notifier des differents evenements lors du combat
     */
    public Combat(ArrayList<Etudiant> etudiantsJ1, ArrayList<Etudiant> etudiantsJ2, Zone zone,
            CombatsController combatsController) {
        this.combatsController = combatsController;
        this.combattants = new ArrayList<>();
        this.combattants.addAll(etudiantsJ1);
        this.combattants.addAll(etudiantsJ2);
        this.zone = zone;
    }

    /**
     * demarre les combats
     * 
     * @param combatsEnCours la liste des combats a demarrer
     */
    public void start(ArrayList<Combat> combatsEnCours) {
        this.autresCombats = new ArrayList<>();
        this.autresCombats.addAll(combatsEnCours);
        if (t != null)
            return;
        t = new Thread(this);
        t.start();
    }

    /**
     * retoune l'etat du thread actuel
     * 
     * @return State
     */
    public State getState() {
        return this.t.getState();
    }

    /**
     * cherche si un des combats est termine, auquel cas on arrete egalement
     * celui-ci
     * 
     * @return Combat le combat fini, null si aucun combat n'est termine
     */
    public Combat chercherCombatFini() {
        Combat fini = null;
        Iterator<Combat> c = autresCombats.iterator();
        while (c.hasNext()) {
            Combat comb = c.next();
            if (comb.finished())
                fini = comb;
        }
        return fini;
    }

    /**
     * demarre le thread et lance la boucle qui s'arretera si ce combat ou un autre se termine
     */
    public void run() {
        if (combattants.isEmpty()) {
            System.out.println(
                    "egalité dans la zone " + zone.getIndiceZone() + " car pas de combattants");
            while (chercherCombatFini() != null) {
            }
            return; // pas de gagnant si pas de combattants
        }
        ArrayList<Etudiant> offensifs = new ArrayList<>();
        offensifs.addAll(combattants);
        offensifs.removeIf(e -> (e.getStrategie() instanceof Defensif));
        if (offensifs.isEmpty()) {
            System.out.println("egalité dans la zone " + zone.getIndiceZone()
                    + " car tous les etudiants sont defensifs");
            while (chercherCombatFini() != null) {
            }
            return;// si il n'y a que du soin -> egalité
        }
        do { // tant que pas de gagnant (ou qu'un autre combat soit fini)
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
                Combat fini = chercherCombatFini();
                if (fini != null) {
                    // System.out.println("arret car le combat " + fini.zone.getIndiceZone() + " est
                    // fini");
                    return;
                }
                Etudiant etu = i.next();
                if (etu.credits == 0)
                    continue;
                // ------------recherche de l'etudiant cible----------
                ArrayList<Etudiant> combattantsCopy = new ArrayList<>();
                combattantsCopy.addAll(combattants);
                combattantsCopy.removeIf(e -> (e.credits == 0)); // on retire les morts dans le choix de la
                                                                 // cible
                if (etu.getStrategie() instanceof Offensif)
                    combattantsCopy.removeIf(e -> (e.joueur == etu.joueur));
                else if ( // offensif -> on retire les etudiants de la meme equipe
                etu.getStrategie() instanceof Defensif)
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
                    etu.action(cible);
                    String msg = etu
                            + ((etu.getStrategie() instanceof Offensif) ? "\nattaque\n"
                                    : "\nsoigne\n")
                            + cible + ": " + score + "\n----------------------------------";
                    try {
                        BufferedWriter writer = new BufferedWriter(new FileWriter(
                                Integer.toString(zone.getIndiceZone()) + ".log", true));
                        writer.append(msg + "\n");
                        writer.close();
                    } catch (Exception e) {
                    }
                    if (cible.getCredits() == 0) {
                        System.out.println(etu + "\n| a tue \n| " + cible);
                        combatsController.combatUpdate();
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
            Utils.sleep(50 + (int) Math.floor(50 * Math.random()));// pour eviter que deux combats se finissent en meme
                                                                   // temps
        } while (gagnant == 0);
        zone.setControlee(gagnant);
        combatsController.combatsFinis(zone, gagnant);
    }

    /**
     * retourne true si le combat est fini
     * @return boolean true si le combat est fini
     */
    public boolean finished() {
        return gagnant != 0;
    }
}
