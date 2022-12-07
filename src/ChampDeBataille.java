public class ChampDeBataille {
    private Zone[] zones = new Zone[5];

    public void initZones() {
        //initialisation du champ de bataille avec les 5 zones 
        for (int i = 0; i < 5; i++) {
            zones[i] = new Zone(i);
        }
        return;
    }

    public Zone getZone(int i) {
        return zones[i];
    }

    public void repartirTroupes(Joueur joueur) {
        Armee armeeJoueur = joueur.getArmee();

        String reponse;
        do {
            System.out.println(
                    "souhaitez vous :\n1 - repartir les 20 etudiants aleatoirement\n2 - les repartir à la main");
            reponse = Utils.sc.nextLine();
            if (reponse.equals("1")) {
                //repartition aléatoire des etudiants sur les zones
                repartirTroupeAleatoirement(armeeJoueur.getEtudiants(), joueur.getNumero());
                String msg = "";
                do {
                    System.out.println(
                            "reparition terminee\n1 - afficher la repartition\n2 - continuer");
                    msg = Utils.sc.nextLine();
                    if (msg.equals("1")) {
                        System.out.println(armeeJoueur);
                    }
                } while (!msg.equals("2"));
            } else if (reponse.equals("2")) {
                System.out.println("TODO");
            } else {
                System.out.println("erreur");
            }
        } while (!reponse.equals("1")); //ajouter && ! reponse.equals("2") pour lorsque la reponse sera supportée
    }

    public void repartirTroupeAleatoirement(Etudiant[] etudiants, int indiceJoueur) {
        for (int i = 0; i < 20; i++) {
            // répartit les 20 étudiants aléatoirement sur les 5 zones 
            if (etudiants[i].isReserviste())
                continue;
            int zoneChoisie = (int) Math.floor(Math.random() * 5);
            zones[zoneChoisie].getCombatantsJ(indiceJoueur).add(etudiants[i]);
            etudiants[i].setZone(zones[zoneChoisie]);
        }
    }
}
