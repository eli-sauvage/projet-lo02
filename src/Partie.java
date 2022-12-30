import java.lang.Thread.State;
import java.util.*;

public class Partie {
    private Joueur[] joueurs = new Joueur[2];
    private ChampDeBataille champ = new ChampDeBataille();

    public Partie(boolean repartition) {
        System.out.println("Init Partie");
        joueurs[0] = new Joueur(1);
        joueurs[1] = new Joueur(2);
        champ.initZones();
        System.out.println(this.joueurs[0].getNumero());
		//window.menu.setVisible(true);
        
    /* 
        joueurs[0].getArmee().statsAleatoires();
        joueurs[1].getArmee().statsAleatoires();

        joueurs[0].getArmee().reservistesAleatoires();
        joueurs[1].getArmee().reservistesAleatoires();

        champ.repartirTroupeAleatoirement(joueurs[0].getArmee().getEtudiants(), 1);
        champ.repartirTroupeAleatoirement(joueurs[1].getArmee().getEtudiants(), 2);
    */
        
       
        //System.out.println("---DEBUT DE LA PARTIE---");
        /* 
        combats();
        int gagnant = chercherGagnant();
        while (gagnant == 0) {
            treve(joueurs[0]);
            treve(joueurs[1]);
            combats();
            gagnant = chercherGagnant();
        }
        System.out.println("---------LA PARTIE EST TERMINEE------------");
        System.out.println();
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        System.out.println("@                            @");
        System.out.println("@      GAGNANT  :    J" + gagnant + "      @");
        System.out.println("@                            @");
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
        Utils.attendreEntree("retouner au menu");*/
    }

    public void combats() {
        Utils.attendreEntree("lancer le combat");
        ArrayList<Zone> zonesDeCombat = new ArrayList<>();
        zonesDeCombat.addAll(Arrays.asList(champ.getZones()));
        zonesDeCombat.removeIf(z -> (z.getControlee() != 0));// on retire si zone controllee
        for (Zone z : zonesDeCombat)// remise à zero des combats précedents
            z.resetCombat();
        ArrayList<Combat> combats = new ArrayList<>();
        for (Zone z : zonesDeCombat)
            combats.add(z.getCombat());
        for (Zone z : zonesDeCombat)// obligé de le faire en deux temps car il faut d'abord la liste de tous les
                                    // combats avant de pouvoir les lancer
            z.lancerCombat(combats);
        boolean combatsFinis = false;
        while (!combatsFinis) {
            combatsFinis = true;
            for (Zone z : zonesDeCombat)
                if (z.getCombat().getState() != State.TERMINATED) // tous les threads doivent etre termines
                    combatsFinis = false;
        }
        Utils.sleep(150);// on laisse le temps a tous les threads de bien s'arreter
        Utils.attendreEntree("avoir les status des Zones");
        System.out.println();
        for (Zone z : champ.getZones()) {
            int c = z.getControlee();
            if (c == 0)
                System.out.println("zone " + z.getNomZone() + " pas controllee");
            else
                System.out.println("zone " + z.getNomZone() + " controllee par J" + c);
        }
        Utils.attendreEntree("lancer la treve");
        Utils.clearConsole();
    }

    public void treve(Joueur joueur) {
        Utils.clearConsole();
        System.out.println("----------------------TREVE--------------------");
        System.out.println("-------------JOUEUR " + joueur.getNumero() + "-----------");
        String msg = "";
        do {
            System.out.println(
                    "souhaitez vous : \n| 1 - affecter un reserviste\n| 2 - redeployer des survivants\n| 3 - afficher votre armee\n| ENTREE - continuer");
            msg = Utils.input();
            if (msg.equals("1"))
                champ.affecterReservistes(joueur);
            if (msg.equals("2"))
                champ.redeployerSurvivants(joueur);
            ;
            if (msg.equals("3"))
                System.out.println(joueur.getArmee());
        } while (!msg.isEmpty());
    }

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

    public void setup() {
        System.out.println("ddsqsdfdsf---");
        
        
        /* 
        Utils.clearConsole();
        System.out.println("--------------JOUEUR 1--------------");
        joueurs[0].getArmee().parametrageTroupes();
        joueurs[0].getArmee().choisirReservistes();
        System.out.println("---------PLACEMENT SUR LES ZONES JOUEUR 1---------");
        champ.repartirTroupes(joueurs[0]);
        Utils.clearConsole();
        System.out.println("--------------JOUEUR 2--------------");
        joueurs[1].getArmee().parametrageTroupes();
        joueurs[1].getArmee().choisirReservistes();
        System.out.println("---------PLACEMENT SUR LES ZONES JOUEUR 2---------");
        champ.repartirTroupes(joueurs[1]);*/
    }
    
    public Joueur getJoueur(int i){
        return this.joueurs[i];
    }
    
    public ChampDeBataille getChamp(){
        return champ;
    }
}
