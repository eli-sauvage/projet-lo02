package controllers;

import views.*;

import java.util.ArrayList;
import java.util.Arrays;

import models.*;

public class ArmeController {
    private boolean running = true;
    private Joueur joueur;
    private ChampDeBataille champ;

    public ArmeController(Joueur joueur, ChampDeBataille champ) {
        this.joueur = joueur;
        this.champ = champ;
    }

    ArmeeView armeeV;

    public void display() {
        armeeV = new ArmeeView(this);
        while (running) {
            Utils.sleep(50);
        }
        armeeV.fermer();
    }

    public String getNumeroJoueur() {
        return Integer.toString(joueur.getNumero());
    }

    public void appliquerStats(int indiceEtu, int f, int d, int r, int i, int c, boolean reserviste, Strategies strat,
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
                champ.getZone(zoneIndex).getCombatantsJ(joueur.getNumero()).add(e);
            } catch (Exception exept) {
            }
        }

        // TODO : gérer si total > 400 (msg erreur par exp)
    }

    public Etudiant getEtudiant(int i) {
        return joueur.getArmee().getEtudiants()[i];
    }

    public String getNomEtudiant(int indiceEtu) {
        return joueur.getArmee().getEtudiants()[indiceEtu].getNom();
    }

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

    public void randomStats() {
        Etudiant[] etudiants = joueur.getArmee().getEtudiants();
        for (Etudiant e : etudiants) {
            e.resetStats();
            e.setZone(null);
        }
        for(Zone z:champ.getZones()){
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
        for (int i = 0; i < 20; i++) {// choisit une stratedie aleatoire
            Strategies strat = (Math.random() > .7) ? Strategies.defensif : Strategies.offensif;
            etudiants[i].setStrategie(strat);
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
            champ.getZones()[zoneChoisie].getCombatantsJ(joueur.getNumero()).add(e);
            e.setZone(champ.getZones()[zoneChoisie]);
        }

    }

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
            throw new Exception("vous devez selectionner exactement 5 réservistes (actuellement : " + nbReservistes + ")");
        if (getPointsRestants() != 0)
            throw new Exception("vous devez répartir les 400 points");
        System.out.println("validation armée J" + joueur.getNumero());
        running = false;
    }
}
