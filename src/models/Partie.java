package models;

import java.lang.Thread.State;
import java.util.*;

import controllers.ArmeController;
import controllers.CombatsController;
import controllers.MenuController;
import controllers.TreveController;
import controllers.VictoireController;
/**
 * c'est l'objet principal qui gere les differentes etapes de la partie
 */
public class Partie {
    private Joueur[] joueurs = new Joueur[2];
    private ChampDeBataille champ = new ChampDeBataille();
    /**
     * lance une nouvelle partie
     */
    public Partie() {
        MenuController mc = new MenuController();
        mc.display();
        

        System.out.println("Init Partie");
        joueurs[0] = new Joueur(1);
        joueurs[1] = new Joueur(2);
        champ.initZones();
        ArmeController ac = new ArmeController(joueurs[0], champ);
        ac.display();
        ac = new ArmeController(joueurs[1], champ);
        ac.display(); 
        
        System.out.println("---DEBUT DE LA PARTIE---");
        combats();
        int gagnant = chercherGagnant();
        while (gagnant == 0) {
            treve(joueurs[0]);
            treve(joueurs[1]);
            combats();
            gagnant = chercherGagnant();
        }

        new VictoireController(Integer.toString(gagnant));
        
        System.out.println("---------LA PARTIE EST TERMINEE------------");
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@                            @");
        System.out.println("@      GAGNANT  :    J" + gagnant + "      @");
        System.out.println("@                            @");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }
    /**
     * lance un nouveau tour de combats
     */
    public void combats() {
        ArrayList<Zone> zonesDeCombat = new ArrayList<>();
        CombatsController cc = new CombatsController();
        zonesDeCombat.addAll(Arrays.asList(champ.getZones()));
        zonesDeCombat.removeIf(z -> (z.getControlee() != 0));// on retire si zone controllee
        for (Zone z : zonesDeCombat)// remise à zero des combats précedents
            z.resetCombat();
        ArrayList<Combat> combats = new ArrayList<>();
        for (Zone z : zonesDeCombat)
            combats.add(z.getCombat(cc));
        boolean combatsFinis = false;
        cc.init(champ, combats);
        cc.display();
        for (Zone z : zonesDeCombat)// obligé de le faire en deux temps car il faut d'abord la liste de tous les
                                    // combats avant de pouvoir les lancer
            z.lancerCombat(combats);
        while (!combatsFinis) {
            combatsFinis = true;
            for (Zone z : zonesDeCombat)
                if (z.getCombat(null).getState() != State.TERMINATED) // tous les threads doivent etre termines
                    combatsFinis = false;
        }
        //c'est le CombatController qui bloque le thread principal, en attendant que le thread du combat gagnant se finisse
        //      celui ci se termine lorsque la méthode CombatsController.combatsFini return, càd quand l'utilisateur clique OK
        System.out.println();
        for (Zone z : champ.getZones()) {
            int c = z.getControlee();
            if (c == 0)
                System.out.println("zone " + z.getNomZone() + " pas controllee");
            else
                System.out.println("zone " + z.getNomZone() + " controllee par J" + c);
        }
        Utils.clearConsole();
    }

    
    /** 
     * lance une nouvelle treve pour le joueur specifie
     * @param joueur le joueur en question
     */
    public void treve(Joueur joueur) {
        TreveController tc = new TreveController(joueur, this.champ);
        tc.display();
    }

    
    /** 
     * cherche si un joueur est gagnat
     * @return int le numero du joueur gagnant (0 si personne ne gagne encore / le numero du joueur gagnant sinon)
     */
    public int chercherGagnant() {
        ArrayList<Zone> zonesJ1 = new ArrayList<>(); // zones controllees par J1
        ArrayList<Zone> zonesJ2 = new ArrayList<>(); // zones controllees par J2
        for (Zone z : champ.getZones()) {
            if (z.getControlee() == 1)
                zonesJ1.add(z);
            if (z.getControlee() == 2)
                zonesJ2.add(z);
        }
        if (zonesJ1.size() == 3 && zonesJ2.size() == 3) {
            System.out.println("EGALITE");
            return 3;
        }
        if (zonesJ1.size() > 2)
            return 1;
        else if (zonesJ2.size() > 2)
            return 2;
        else
            return 0;

    }

    
    /** 
     * retourne l'objet du joueur correspondant a l'index
     * @param i l'indexe du joueur
     * @return Joueur le joueur
     */
    public Joueur getJoueur(int i) {
        return this.joueurs[i];
    }

    
    /** 
     * retoune le champ de bataille
     * @return ChampDeBataille le champ de bataille
     */
    public ChampDeBataille getChamp() {
        return champ;
    }
}
