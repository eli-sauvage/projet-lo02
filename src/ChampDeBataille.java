import java.util.*;
import java.util.concurrent.ExecutionException;

public class ChampDeBataille {
    private Zone[] zones = new Zone[5];

    public void initZones() {
        // initialisation du champ de bataille avec les 5 zones
        for (int i = 0; i < 5; i++) {
            zones[i] = new Zone(i);
        }
        return;
    }

    public Zone getZone(int i) {
        return zones[i];
    }

    public void repartirTroupes(Joueur joueur) {
        String reponse;
        do {
            System.out.println(
                    "souhaitez vous :\n1 - repartir les 20 etudiants aleatoirement\n2 - les repartir à la main");
            reponse = Utils.sc.nextLine();
            if (reponse.equals("1")) {
                // repartition aléatoire des etudiants sur les zones
                repartirTroupeAleatoirement(joueur.getArmee().getEtudiants(), joueur.getNumero());
                String msg = "";
                do {
                    System.out.println(
                            "reparition terminee\n1 - afficher la repartition\n2 - continuer");
                    msg = Utils.sc.nextLine();
                    if (msg.equals("1")) {
                        System.out.println(joueur.getArmee());
                    }
                } while (!msg.equals("2"));
            } else if (reponse.equals("2")) {
                repartirTroupesALaMain(joueur);
            } else {
                System.out.println("erreur");
            }
        } while (!reponse.equals("1") && ! reponse.equals("2"));
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

    public void repartirTroupesALaMain(Joueur joueur) {
        System.out.println("pour chaque combattant, entrez la zone");
        for (int i = 0; i < 5; i++)
            System.out.println(i + " = " + Utils.zoneIndexToString(i));
        ArrayList<Etudiant> combattants = new ArrayList<>(Arrays.asList(joueur.getArmee().getEtudiants()));
        combattants.removeIf(e -> (e.isReserviste()));// on retire les reservites
        Iterator<Etudiant> i = combattants.iterator();
        while (i.hasNext()) {
            Etudiant e = i.next();
            Boolean ok = false;
            while (!ok) {
                System.out.println("à quelle zone voulez vous assigner");
                System.out.println(e);
                String msg = Utils.input();
                try {
                    int zoneIndex = Integer.parseInt(msg);
                    if (zoneIndex >= 0 && zoneIndex <= 5) {
                        e.setZone(zones[zoneIndex]);
                        zones[zoneIndex].getCombatantsJ(joueur.getNumero()).add(e);
                        ok = true;
                    }
                } catch (Exception err) {
                }
            }

        }

    }
}
