public class Partie {
    private Joueur[] joueurs = new Joueur[2];
    private ChampDeBataille champ;

    public Partie() {
        champ = new ChampDeBataille();
        joueurs[0] = new Joueur();
        joueurs[1] = new Joueur();
        System.out.println(joueurs[0]);
    }

}
