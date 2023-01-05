package models;
import java.util.*;

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
    public Zone[] getZones(){
        return zones;
    }



    public void redeployerParmi(Joueur joueur, ArrayList<Etudiant> etudiants) throws Exception {
        System.out.println("entrez l'indice de l'étudiant à déployer");
        String indiceMsg = Utils.input();
        int indice;
        try {
            indice = Integer.parseInt(indiceMsg);
        } catch (Exception e) {
            throw new Exception("entrée non reconnue : " + e.toString());
        }
        Etudiant etu = null;
        for (Etudiant e : etudiants) {
            if (e.id == indice)
                etu = e;
        }
        if (etu == null) {
            throw new Exception("l'étudiant n'est pas dans la liste");
        }
        System.out.println("entrez la zone où déployer l'étudiant");
        for (int i = 0; i < 5; i++)
            System.out.println(i + " = " + Utils.zoneIndexToString(i));
        indiceMsg = Utils.input();
        try {
            indice = Integer.parseInt(indiceMsg);
        } catch (Exception e) {
            throw new Exception("entrée non reconnue : " + e.toString());
        }
        if (indice < 0 || indice > 4) {
            throw new Exception("la zone n'existe pas");
        }
        if(zones[indice].getControlee() != 0){
            throw new Exception("impossible de redéployer ici, zone controlee par J" + zones[indice].getControlee());
        }
        // -------MAJ DES ZONES ---------------
        etu.setZone(zones[indice]);
        etu.setReserviste(false);
        System.out.println("l'étudiant a bien été déployé");
        Utils.attendreEntree();

    }

    public void affecterReservistes(Joueur joueur) {
        String msg = "qsdfsqdfqsdfqsd";
        while (!msg.equals("")) {
            System.out.println("-----");
            ArrayList<Etudiant> reservistes = new ArrayList<>(Arrays.asList(joueur.getArmee().getEtudiants()));
            reservistes.removeIf(e -> (!e.isReserviste()));
            if (reservistes.isEmpty()) {
                System.out.println("vous n'avez plus de réservistes");
                break;
            }
            System.out.println("voici vos étudiants reservistes");
            for (Etudiant e : reservistes)
                System.out.println("| " + e);
            System.out.println("souhaitez-vous affecter un réserviste ?");
            System.out.println("| 1 - oui\n| ENTREE - non, continuer");
            msg = Utils.input();
            if (msg.equals("1")) {
                try {
                    redeployerParmi(joueur, reservistes);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    Utils.attendreEntree();
                    continue;
                }
            }
            if (!msg.equals("1") && !msg.isEmpty())
                System.out.println("commande non reconnue");
        }
    }
    public void redeployerSurvivants(Joueur joueur) {
        String msg = "sdfsdfsqfsdfqsdfqsdf";
        while (!msg.equals("")) {
            System.out.println("-----");
            ArrayList<Etudiant> survivants = new ArrayList<>(Arrays.asList(joueur.getArmee().getEtudiants()));
            survivants.removeIf(e -> (e.isReserviste()));//on retire les reservistes
            survivants.removeIf(e -> (e.getCredits() == 0));//on retire les morts
            survivants.removeIf(e -> (e.getZone().getControlee() == 0));//on retire si zone pas controlee
            survivants.removeIf(e-> (e.getZone().getCombatantsJ(e.joueur).size() == 1));//on retire si c'est le dernier joueur de la zone
            if (survivants.isEmpty()) {
                System.out.println("vous n'avez pas/plus de survivants qui peuvent etre affectes");
                break;
            }
            
            System.out.println("voici vos étudiants survivants");
            for (Etudiant e : survivants)
                System.out.println("| " + e);
            System.out.println("souhaitez-vous affecter un survivant ?");
            System.out.println("| 1 - oui\n| ENTREE - non, continuer");
            msg = Utils.input();
            if (msg.equals("1")) {
                try {
                    redeployerParmi(joueur, survivants);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                    Utils.attendreEntree();
                    continue;
                }
            }
            if (!msg.equals("1") && !msg.isEmpty())
                System.out.println("commande non reconnue");
        }
    }
}
