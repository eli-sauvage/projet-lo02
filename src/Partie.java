public class Partie {
    private Joueur[] joueurs = new Joueur[2];
    private ChampDeBataille champ;

    public Partie() {
        
        joueurs[0] = new Joueur();
        joueurs[1] = new Joueur();
        champ = new ChampDeBataille();
        Utils.clearConsole();
        System.out.println("--------------JOUEUR 1--------------");
        joueurs[0].getArmee().parametrageTroupes();
        //joueurs[0].getArmee().choisirReservistes();
        System.out.println("---------PLACEMENT SUR LES ZONES JOUEUR 1---------");

        Utils.clearConsole();
        System.out.println("--------------JOUEUR 2--------------");
        joueurs[1].getArmee().parametrageTroupes();
        //joueurs[1].getArmee().choisirReservistes();

        champ.initZones();

        for (Etudiant e : joueurs[0].getArmee().getEtudiants())
            champ.addToZone(0, 1, e);
        for (Etudiant e : joueurs[1].getArmee().getEtudiants())
            champ.addToZone(0, 2, e);
        champ.getZones(0).calculCombat();
    }

}
