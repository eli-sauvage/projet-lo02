public class Partie {
    private Joueur[] joueurs = new Joueur[2];
    private ChampDeBataille champ;

    public Partie() {
        
        joueurs[0] = new Joueur();
        joueurs[1] = new Joueur();
        Utils.clearConsole();
        System.out.println("--------------JOUEUR 1--------------");
        joueurs[0].getArmee().parametrageTroupes();
        joueurs[0].getArmee().choisirReservistes();
        champ = new ChampDeBataille();
        champ.initZones();
        System.out.println(champ.getZones(1).getTypeZone());
        Utils.clearConsole();
        System.out.println("--------------JOUEUR 2--------------");
        joueurs[1].getArmee().parametrageTroupes();
        joueurs[1].getArmee().choisirReservistes();
    }

}
