package controllers;

import views.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import models.*;

public class TreveController {
    private boolean running = true;
    private Joueur joueur;
    private ChampDeBataille champ;

    public TreveController(Joueur joueur, ChampDeBataille champ) {
        this.joueur = joueur;
        this.champ = champ;
    }

    TreveView armeeV;

    public void display() {
        armeeV = new TreveView(this);
        while (running) {
            Utils.sleep(50);
        }
        armeeV.fermer();
    }

    public int getNumeroJoueur() {
        return joueur.getNumero();
    }
    public ArrayList<Etudiant> getReserviste(){
        ArrayList<Etudiant> reservistes = new ArrayList<>();
        for(Etudiant etu:joueur.getArmee().getEtudiants()){
            if(etu.getReserviste())reservistes.add(etu);
        }
        return reservistes; 
    }

    public Etudiant getEtudiant(int i) {
        return joueur.getArmee().getEtudiants()[i];
    }

    public ArrayList<Etudiant> getSurvivants(){
        ArrayList<Etudiant> survivants = new ArrayList<>();
        for(Etudiant etu:joueur.getArmee().getEtudiants()){
            if((!etu.isReserviste())&&(etu.getZone().getControlee() != 0)&&(etu.getCredits()!=0)){
                survivants.add(etu);
            }



        }

        return survivants;
    }

    public String getNomEtudiant(int indiceEtu) {
        return joueur.getArmee().getEtudiants()[indiceEtu].getNom();
    }

}
