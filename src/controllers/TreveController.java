package controllers;

import views.*;

import java.util.ArrayList;

import models.*;

/**
 * le controller pour la vue Treve, permet d'effectuer le lien entre la vue et les models utilises
 * pour la treve
 */
public class TreveController {
    private boolean running = true;
    private Joueur joueur;
    private ChampDeBataille champ;

    /**
     * @param joueur le joueur dont c'est la treve
     * @param champ le champ de bataille de la partie
     */
    public TreveController(Joueur joueur, ChampDeBataille champ) {
        this.joueur = joueur;
        this.champ = champ;
    }

    TreveView armeeV;

    /**
     * affiche la vue
     */
    public void display() {
        armeeV = new TreveView(this, joueur.getNumero());
        while (running) {
            Utils.sleep(50);
        }
        armeeV.fermer();
    }

    /**
     * retounr le numero du joueur actuel
     * 
     * @return int le numero du joueur
     */
    public int getNumeroJoueur() {
        return joueur.getNumero();
    }

    /**
     * deploie l'etudiant dans la zone specifiee
     * 
     * @param etudiant l'etudiant a deployer
     * @param indexZone la zone dans laquelle le deployer
     * @throws Exception si l'etudiant ne peut pas etre deploye
     */
    public void deployerEtudiant(Etudiant etudiant, int indexZone) throws Exception{
        if(etudiant.getZone().getIndiceZone() == indexZone) throw new Exception("l'etudiant est deja dans la zone !");
        if(champ.getZone(indexZone).getControlee() != 0) throw new Exception("la zone est controlee, impossible de deployer");
        etudiant.setZone(champ.getZone(indexZone));
        etudiant.setReserviste(false);

    }

    /**
     * retourne un ArrayList contenant les reservistes
     * 
     * @return ArrayList la liste des reservistes
     */
    public ArrayList<Etudiant> getReserviste() {
        ArrayList<Etudiant> reservistes = new ArrayList<>();
        for (Etudiant etu : joueur.getArmee().getEtudiants()) {
            if (etu.getReserviste())
                reservistes.add(etu);
        }
        return reservistes;
    }

    /**
     * retourne l'etudiant associe a l'index
     * 
     * @param i l'index de l'etudiant
     * @return Etudiant l'objet etudiant associe
     */
    public Etudiant getEtudiant(int i) {
        return joueur.getArmee().getEtudiants()[i];
    }

    /**
     * retourne un Arraylist contenant les survivants
     * 
     * @return ArrayList les survivants
     */
    public ArrayList<Etudiant> getSurvivants() {
        ArrayList<Etudiant> survivants = new ArrayList<>();
        for (Etudiant etu : joueur.getArmee().getEtudiants()) {
            if ((etu.isReserviste() == false) && (etu.getZone().getControlee() != 0)
                    && (etu.getCredits() != 0)
                    && !(etu.getZone().getCombatantsJ(etu.getJoueur()).size() == 1)) {
                survivants.add(etu);
            }
            // if(etu.getZone().getControlee() != 0){
            // survivants.add(etu);
            /// }
        }

        return survivants;
    }

    /**
     * retourne le nom de l'etudiant correspondant a l'index specifie
     * 
     * @param indiceEtu l'indice du joueur
     * @return String le nom du joueur
     */
    public String getNomEtudiant(int indiceEtu) {
        return joueur.getArmee().getEtudiants()[indiceEtu].getNom();
    }

    /**
     * retourne le nom du joueur actuel
     * 
     * @return String le nom du joueur
     */
    public String getNomJoueur() {
        return Integer.toString(this.joueur.getNumero());
    }

    /**
     * retounre la somme des credits restants de la zone specifiee
     * 
     * @param indiceZone indice de la zone
     * @return int le nombre de credits restants
     */
    public int getCreditRestant(int indiceZone) {
        int creditRestant = 0;
        for (Etudiant etu : joueur.getArmee().getEtudiants()) {
            if ((!etu.getReserviste()) && (etu.getZone().getIndiceZone() == indiceZone)) {
                creditRestant += etu.getCredits();
            }
        }
        return creditRestant;
    }

    /**
     * stoppe la boucle bloquant le thread principal afin de continuer vers le joueur suivant ou
     * vers le prochain combat
     */
    public void arreter() {
        this.running = false;
    }

    /**
     * retourne true si la zone specifiee est controlee
     * 
     * @param indiceZone l'indice de la zone
     * @return int 0 si pas controlee, sinon le numero du joueur qui la controle
     */
    public int getControllee(int indiceZone) {
        return champ.getZone(indiceZone).getControlee();
    }
}
