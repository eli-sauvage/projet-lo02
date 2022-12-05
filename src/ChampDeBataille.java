public class ChampDeBataille {
    private Zone[] zones = new Zone[5];

    public void initZones() {
        //initialisation du champ de bataille avec les 5 zones 
        for (int i = 0; i < 5; i++){
            zones[i] = new Zone(i);
        }
        return;
    }
    public Zone getZones(int i) {
        return zones[i];
    }

    public void repartirTroupes(Etudiant[] armeeJoueur,int joueurSelect){
        System.out.println("souhaitez vous :\n1 - repartir les 20 etudiants aleatoirement\n2 - les repartir à la main");
        String reponse = Utils.sc.nextLine();
        if (reponse.equals("1")) {
            //repartition aléatoire des etudiants sur les zones
            repartirTroupeAleatoirement(armeeJoueur,joueurSelect);
            String msg = "";
            do {
                System.out.println("reparition terminee\n1 - afficher la repartition\n2 - continuer");
                msg = Utils.sc.nextLine();
                if (msg.equals("1")) {
                    //afficher repartition
                    System.out.println("23");
                    afficherCombattants(joueurSelect);
                    
                }
            } while (!msg.equals("2"));
        } 
        else if (reponse.equals("2")) {
            System.out.println("TODO");
            
        } 
        else {
            System.out.println("eureur");
        }
    }
    public void repartirTroupeAleatoirement(Etudiant[] armeeJoueur,int joueurSelect){
        for (int i = 0; i < 20; i++) {
            // répartit les 20 étudiants aléatoirement sur les 5 zones 
            int zoneChoisie = (int) Math.floor(Math.random() * 5);
            zones[zoneChoisie].getCombatantsJ(joueurSelect).add(armeeJoueur[i]);
        }
    }
    public void afficherCombattants(int joueurSelect){
        for (int i = 0; i < 5; i++) {
            System.out.println(zones[i].getCombatantsJ(joueurSelect).size());
        }
    }
}
