public class Treve {
    ChampDeBataille champ;
    public Treve(ChampDeBataille champ, Joueur[] joueurs){
        this.champ = champ;
        deroulementTreve(joueurs[0]);
        deroulementTreve(joueurs[1]);
    }
    public void deroulementTreve(Joueur joueur){
        Utils.clearConsole();
        System.out.println("----------------------TREVE--------------------");
        System.out.println("-------------JOUEUR " + joueur.getNumero() + "-----------");
        String msg = "";
        do{
            System.out.println("souhaitez vous : \n| 1 - affecter un reserviste\n| 2 - redeployer des survivants\n| 3 - continuer");
            msg = Utils.sc.nextLine();
            if(msg.equals("1"))joueur.getArmee().affecterReservistes();
            if(msg.equals("2"))joueur.getArmee().redeployerSurvivants();
        }while(!msg.equals("3"));
    }
}
