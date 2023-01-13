package controllers;

import views.*;

import java.util.ArrayList;

import models.*;
import models.strategies.*;

/**
 * le controller pour la vue Armee, permet d'effectuer le lien entre la vue et le model de l'armee du joueur
 */
public class ArmeController {
    private boolean running = true;
    private Joueur joueur;
    private ChampDeBataille champ;

    /**
     * @param joueur le joueur actuel
     * @param champ  le champ de bataille
     */
    public ArmeController(Joueur joueur, ChampDeBataille champ) {
        this.joueur = joueur;
        this.champ = champ;
    }

    ArmeeView armeeV;

    /**
     * affiche la vue
     */
    public void display() {
        armeeV = new ArmeeView(this);
        while (running) {
            Utils.sleep(50);
        }
        armeeV.fermer();
    }

    /**
     * retourne le numéro du joueur
     * 
     * @return String le numero du joueur actuel
     */
    public String getNumeroJoueur() {
        return Integer.toString(joueur.getNumero());
    }

    /**
     * applique les stats a l'etudiant selectionne par l'indice
     * 
     * @param indiceEtu  l'indice de l'etudiant
     * @param f          la force a appliquer
     * @param d          la dexterite a appliquer
     * @param r          la resistance a appliquer
     * @param i          l'initiative a appliquer
     * @param c          la constitution a appliquer
     * @param reserviste l'attibut boolen reserviste a appliquer
     * @param strat      la strategie a appliquer
     * @param zoneIndex  la zone ou l'etudiant sera deploye
     */
    public void appliquerStats(int indiceEtu, int f, int d, int r, int i, int c, boolean reserviste, Strategie strat,
            int zoneIndex) {
        Etudiant e = joueur.getArmee().getEtudiants()[indiceEtu];
        e.setForce(f);
        e.setDexterite(d);
        e.setResistance(r);
        e.setInitiative(i);
        e.setConsitution(c);
        e.setReserviste(reserviste);
        e.setStrategie(strat);
        if (!e.isReserviste()) {// pas de zone si reserviste
            try {// possibilité d'un champ null
                e.setZone(champ.getZone(zoneIndex));
            } catch (Exception exept) {
            }
        }
    }

    /**
     * retourne l'etudiant a l'indice specifie
     * 
     * @param i l'index de l'etudiant
     * @return Etudiant l'objet Etudiant correspondant
     */
    public Etudiant getEtudiant(int i) {
        return joueur.getArmee().getEtudiants()[i];
    }

    /**
     * retourne le nom de l'etudiant a l'indice specifie
     * 
     * @param indiceEtu l'indice de l'etudiant
     * @return String le nom de l'etudiant
     */
    public String getNomEtudiant(int indiceEtu) {
        return joueur.getArmee().getEtudiants()[indiceEtu].getNom();
    }

    /**
     * retourne les points possibles restants a repartir
     * 
     * @return int les points restants a repartir
     */
    public int getPointsRestants() {
        int total = 0;
        for (Etudiant e : joueur.getArmee().getEtudiants()) {
            total += e.getConsitution();
            total += e.getDexterite();
            total += e.getForce();
            total += e.getInitiative();
            total += e.getResistance();
        }
        return 454 - total; // 454 = 400 + (4*1+5) elite+ (4*2+10) gobi
    }

    /**
     * applique des statistiques aleatoires a tous les etudiants du joueur
     */
    public void randomStats() {
        Etudiant[] etudiants = joueur.getArmee().getEtudiants();
        for (Etudiant e : etudiants) {
            e.resetStats();
            e.setZone(null);
        }
        for (Zone z : champ.getZones()) {
            z.getCombatantsJ(joueur.getNumero()).clear();
        }

        for (int i = 0; i < 400; i++) {// répartit les 400 points aléatoirement dans l'armée
            int etu = (int) Math.floor(Math.random() * 20);
            int stat = (int) Math.floor(Math.random() * 5);
            if (stat == 0)
                etudiants[etu].incrForce();
            else if (stat == 1)
                etudiants[etu].incrDexterite();
            else if (stat == 2)
                etudiants[etu].incrResistance();
            else if (stat == 3)
                etudiants[etu].incrConstitution();
            else if (stat == 4)
                etudiants[etu].incrInitiative();
        }
        for (int i = 0; i < 20; i++) {// choisit une strategie aleatoire
            double rand = Math.random();
            if (rand < .33)
                etudiants[i].setStrategie(new Defensif());
            else if (rand > .5)
                etudiants[i].setStrategie(new Offensif());
            else
                etudiants[i].setStrategie(new Aleatoire());
        }
        // reservistes aléatoires
        ArrayList<Integer> indiceReserviste = new ArrayList<>();
        while (indiceReserviste.size() < 5) {
            int i = (int) Math.floor(Math.random() * 20);
            if (!indiceReserviste.contains(i))
                indiceReserviste.add(i);
        }
        for (int i : indiceReserviste)
            etudiants[i].setReserviste(true);
        System.out.println(etudiants[0].getReserviste());
        // zone au hasard
        for (Etudiant e : etudiants) {
            if (e.isReserviste())
                continue;
            int zoneChoisie = (int) Math.floor(Math.random() * 5);
            // int zoneChoisie = (int) Math.floor(Math.random() * 2);
            e.setZone(champ.getZones()[zoneChoisie]);
        }

    }

    /**
     * valide l'armee et passe au joueur suivant ou au combat
     * 
     * @throws Exception si un parametre n'est pas valide
     */
    public void valider() throws Exception {
        int nbReservistes = 0, i = 0;
        for (Etudiant e : joueur.getArmee().getEtudiants()) {
            i++;
            if (e.getReserviste())
                nbReservistes++;
            if (e.getZone() == null && !e.getReserviste())
                throw new Exception("l'etudiant " + i + "n'a pas été affecté à une zone");
        }
        if (nbReservistes != 5)
            throw new Exception(
                    "vous devez selectionner exactement 5 réservistes (actuellement : " + nbReservistes + ")");
        if (getPointsRestants() != 0)
            throw new Exception("vous devez répartir les 400 points");
        System.out.println("validation armée J" + joueur.getNumero());
        running = false;
    }
}
