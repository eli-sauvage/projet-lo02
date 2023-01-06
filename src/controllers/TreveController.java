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
        armeeV = new TreveView(this, joueur.getNumero());
        while (running) {
            Utils.sleep(50);
        }
        armeeV.fermer();
    }

    public int getNumeroJoueur() {
        return joueur.getNumero();
    }
    
    public void deployerReserviste(Etudiant etudiant, int indexZone){
         etudiant.setZone(champ.getZone(indexZone));
         etudiant.setReserviste(false);
         //System.out.println(Utils.zoneIndexToString(etudiant.getZone().getIndiceZone()));
    }

    public void deployerSurvivant(Etudiant etudiant, int indexZone){
        etudiant.setZone(champ.getZone(indexZone));
        etudiant.setReserviste(false);
       
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
            if((etu.isReserviste()==false)&&(etu.getZone().getControlee() != 0)&&(etu.getCredits()!=0)&&!(etu.getZone().getCombatantsJ(etu.getJoueur()).size() == 1)){
                survivants.add(etu);
            }
            //if(etu.getZone().getControlee() != 0){
            //    survivants.add(etu);
            ///}
        }
       
       
        return survivants;
    }

    public String getNomEtudiant(int indiceEtu) {
        return joueur.getArmee().getEtudiants()[indiceEtu].getNom();
    }

    public String getNomJoueur(){
        return Integer.toString(this.joueur.getNumero());
    }

    public int getCreditRestant(int indiceZone){
        int creditRestant=0;
        for(Etudiant etu:joueur.getArmee().getEtudiants()){
            if((!etu.getReserviste())&&(etu.getZone().getIndiceZone() == indiceZone)){
                creditRestant+=etu.getCredits();
            }
		}
        return creditRestant;
    }
   
    public void arreter(){
        this.running = false;
    }

    public int getControllee(int indiceZone){
        return champ.getZone(indiceZone).getControlee();
    }
}
