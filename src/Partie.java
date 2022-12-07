import java.lang.Thread.State;
import java.util.*;

public class Partie {
    private Joueur[] joueurs = new Joueur[2];
    private ChampDeBataille champ;

    public Partie(boolean repartition) {
        joueurs[0] = new Joueur(1);
        joueurs[1] = new Joueur(2);
        champ = new ChampDeBataille();
        champ.initZones();

        if(repartition){
            setup();
        }else{
            joueurs[0].getArmee().statsAleatoires();
            joueurs[1].getArmee().statsAleatoires();

            joueurs[0].getArmee().reservistesAleatoires();
            joueurs[1].getArmee().reservistesAleatoires();

            champ.repartirTroupeAleatoirement(joueurs[0].getArmee().getEtudiants(), 1);
            champ.repartirTroupeAleatoirement(joueurs[1].getArmee().getEtudiants(), 2);

            System.out.println(joueurs[0].getArmee());
            System.out.println("-----------------------------");
            System.out.println(joueurs[1].getArmee());

        }

        Utils.clearConsole();
        System.out.println("---DEBUT DE LA PARTIE---");
        System.out.println("appuyer sur ENTREE pour lancer le combat");
        Utils.sc.nextLine();

        ArrayList<Combat> combats = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            combats.add(champ.getZone(i).getCombat());
        for (int i = 0; i < 5; i++)
            champ.getZone(i).lancerCombat(combats);

        boolean combatsFinis = false;
        while (!combatsFinis) {
            combatsFinis = true;
            for (int i = 0; i < 5; i++)
                if (champ.getZone(0).getCombat().getState() != State.TERMINATED) //tous les threads doivent etre termines
                    combatsFinis = false;
        }
        Utils.sleep(25);//on laisse le temps a tous les threads de bien s'arreter
        System.out.println("appyez sur ENTREE pour continuer");
        Utils.sc.nextLine();
        //-------------TREVE-------------------------
        new Treve(champ, joueurs);
    }

    private void setup(){
        Utils.clearConsole();
        System.out.println("--------------JOUEUR 1--------------");
        joueurs[0].getArmee().parametrageTroupes();
        //joueurs[0].getArmee().choisirReservistes();
        System.out.println("---------PLACEMENT SUR LES ZONES JOUEUR 1---------");
        champ.repartirTroupes(joueurs[0]);
        Utils.clearConsole();
        System.out.println("--------------JOUEUR 2--------------");
        joueurs[1].getArmee().parametrageTroupes();
        //joueurs[1].getArmee().choisirReservistes();
        System.out.println("---------PLACEMENT SUR LES ZONES JOUEUR 2---------");
        champ.repartirTroupes(joueurs[1]);
        //---------------------------------------------------------------------

    }
}
