import java.lang.Thread.State;
import java.util.*;

public class Partie {
    private Joueur[] joueurs = new Joueur[2];
    private ChampDeBataille champ;

    public Partie() {


        joueurs[0] = new Joueur(1);
        joueurs[1] = new Joueur(2);
        champ = new ChampDeBataille();
        champ.initZones();
        Utils.clearConsole();
        System.out.println("--------------JOUEUR 1--------------");
        joueurs[0].getArmee().parametrageTroupes();
        //joueurs[0].getArmee().choisirReservistes();
        System.out.println("---------PLACEMENT SUR LES ZONES JOUEUR 1---------");
        champ.repartirTroupes(joueurs[0].getArmee().getEtudiants(), 1);
        Utils.clearConsole();
        System.out.println("--------------JOUEUR 2--------------");
        joueurs[1].getArmee().parametrageTroupes();
        //joueurs[1].getArmee().choisirReservistes();
        System.out.println("---------PLACEMENT SUR LES ZONES JOUEUR 2---------");
        champ.repartirTroupes(joueurs[1].getArmee().getEtudiants(), 2);
        //---------------------------------------------------------------------

        Utils.clearConsole();
        System.out.println("---DEBUT DE LA PARTIE---");

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
        Utils.sleep(5);//on laisse le temps a tous les threads de bien s'arreter
        System.out.println("FINI");
    }

}
