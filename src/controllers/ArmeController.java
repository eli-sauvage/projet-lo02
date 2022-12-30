package controllers;

import views.*;
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


    public void appliquerStats(int indiceEtu, int f, int d, int r, int i, int c, boolean reserviste, Strategies strat){
        Etudiant e = joueur.getArmee().getEtudiants()[indiceEtu];
        e.setForce(f);
        e.setDexterite(d);
        e.setResistance(r);
        e.setInitiative(i);
        e.setConsitution(c);
        e.setReserviste(reserviste);
        e.setStrategie(strat);
        //TODO : gérer si total > 400 (msg erreur par exp)
    }
    public Etudiant getEtudiant(int i){
        return joueur.getArmee().getEtudiants()[i];
    }
    public String getNomEtudiant(int indiceEtu){
        return joueur.getArmee().getEtudiants()[indiceEtu].getNom();
    }
    public void valider(){
        running = false;
    }
    public int getPointsRestants(){
        int total = 0;
        for(Etudiant e:joueur.getArmee().getEtudiants()){
            total+= e.getConsitution();
            total+=e.getDexterite();
            total += e.getForce();
            total += e.getInitiative();
            total += e.getResistance();
        }
        return 454-total; //454 = 400 + (4*1+5) elite+ (4*2+10) gobi
    }

    public void randomStats(){
        Etudiant[] etudiants = joueur.getArmee().getEtudiants();
        for(Etudiant e:etudiants){
            e.resetStats();
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
        //zone au hasard
        for(Etudiant e:etudiants){
             if (e.isReserviste())
                continue;
            int zoneChoisie = (int) Math.floor(Math.random() * 5);
            //int zoneChoisie = (int) Math.floor(Math.random() * 2);
            champ.getZones()[zoneChoisie].getCombatantsJ(joueur.getNumero()).add(e);
            e.setZone(champ.getZones()[zoneChoisie]);
        }

    }
}
