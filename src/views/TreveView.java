package views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import controllers.*;

import models.*;

public class TreveView {

	public JFrame treveView = new JFrame();
	private Choice choixReservistes;
	private Choice choixSurvivants;
	private Choice zoneDeploiementReserviste;
	private Choice zoneDeploiementSurvivant;
	private Color bgColor = new Color(255, 128, 192);
	private int totalCredit;
	private TreveController controller;
	private ArrayList<Etudiant> reservistes = new ArrayList<>();
	private ArrayList<Etudiant> survivants = new ArrayList<>();
	private Etudiant etuSelect;
	private String[] controleZoneString = new String[] {"--", "J1", "J2"};
	private JLabel[] lblCreditZone;
	private JLabel lblCreditTotal;
	private int numeroJoueur;

	public TreveView(TreveController controller, int numeroJoueur) {
		this.numeroJoueur = numeroJoueur;
		this.controller = controller;
		affTreveView();
		treveView.setVisible(true);
	}

	public void fermer() {
		treveView.setVisible(false);
	}

	private void affTreveView() {
		// interface treve
		System.out.println("print treve view");
		treveView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		treveView.getContentPane().setBackground(Utils.bgColor);
		treveView.setBounds(10, 10, 1500, 800);
		treveView.setBackground(bgColor);
		treveView.setLayout(null);
		JLabel label11 = new JLabel("Treve - Joueur " + this.numeroJoueur);
		label11.setBounds(46, 35, 500, 31);
		label11.setFont(new Font("Tahoma", Font.BOLD, 25));
		treveView.add(label11);
		
		reservistes = controller.getReserviste();
		survivants = controller.getSurvivants();

		//choix de la nouvelle zone du reserviste deploiement
		//label
		JLabel lblNewLabel = new JLabel("Deploiement reservistes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(196, 150, 600, 36);
		treveView.add(lblNewLabel);
		//liste du reserviste a selectionner
		choixReservistes = new Choice();
		choixReservistes.setFont(new Font("Tahoma", Font.BOLD, 20));
		choixReservistes.setBounds(196, 200, 200, 26);
		
		for(Etudiant etuReserviste:reservistes){
			choixReservistes.add(etuReserviste.getNom());
		}
		treveView.add(choixReservistes);
		//label
		JLabel lblDestinationReserv = new JLabel("Zone du deploiement");
		lblDestinationReserv.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDestinationReserv.setBounds(196, 250, 300, 26);
		treveView.add(lblDestinationReserv);
		//drop down pour la zone de deploiement
		zoneDeploiementReserviste = new Choice();
		zoneDeploiementReserviste.setFont(new Font("Tahoma", Font.BOLD, 20));
		zoneDeploiementReserviste.setBounds(196, 300, 200, 26);
		for (int i = 0; i < 5; i++) {
			zoneDeploiementReserviste.addItem(Utils.zoneIndexToString(i));
		}
		treveView.add(zoneDeploiementReserviste);
		//bouton valider reserviste
		JButton deployerReserviste = new JButton("Deployer ");
        deployerReserviste.setBounds(196, 350, 200, 40);
        deployerReserviste.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				if(choixReservistes.getItemCount()>0){
					//controller.deployerReserviste();
					etuSelect = reservistes.get(choixReservistes.getSelectedIndex());
					System.out.println(etuSelect.getNom());
					//demande au controller d'effectuer le deploiment
					controller.deployerReserviste(etuSelect, zoneDeploiementReserviste.getSelectedIndex());
					//re affichage de la liste update
					choixReservistes.removeAll();
					reservistes = controller.getReserviste();
					for(Etudiant etu:reservistes){
						choixReservistes.add(etu.getNom());
					}
					choixReservistes.update(null);
					update();
				}
            }
        });
        treveView.add(deployerReserviste);

		//---------------------------------------------------------------//

		//choix de la zone de deploiement pour les survivants
		//label
		JLabel lblRedeploiementDesSurvivants = new JLabel("Deploiement survivants");
		lblRedeploiementDesSurvivants.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRedeploiementDesSurvivants.setBounds(896, 150, 600, 36);
		treveView.add(lblRedeploiementDesSurvivants);
		//selection du survivant
		choixSurvivants = new Choice();
		choixSurvivants.setFont(new Font("Tahoma", Font.BOLD, 20));
		choixSurvivants.setBounds(896, 200, 300, 26);
		for(Etudiant etuReserviste:survivants){
			choixSurvivants.add(etuReserviste.getNom());
		}
		treveView.add(choixSurvivants);
		//label
		JLabel lblZoneActuel = new JLabel("Zone : ");
		lblZoneActuel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblZoneActuel.setBounds(1200, 190, 600, 36);
		treveView.add(lblZoneActuel);
		//condition si plus de survivant dans la liste
		if(survivants.size()>0){
			lblZoneActuel.setText("Zone : " + Utils.zoneIndexToString(survivants.get(0).getZone().getIndiceZone()));
		}
		else{
			lblZoneActuel.setText("Zone : ");
		}

		//choix de la nouvelle zone d'un survivant
		//label 
		JLabel lblDestinationSurviv = new JLabel("Zone du deploiement");
		lblDestinationSurviv.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblDestinationSurviv.setBounds(896, 250, 300, 26);
		treveView.add(lblDestinationSurviv);
		//drop down pour la zone de deploiement
		zoneDeploiementSurvivant = new Choice();
		zoneDeploiementSurvivant.setFont(new Font("Tahoma", Font.BOLD, 20));
		zoneDeploiementSurvivant.setBounds(896, 300, 200, 26);
		for (int i = 0; i < 5; i++) {
			zoneDeploiementSurvivant.addItem(Utils.zoneIndexToString(i));
		}
		treveView.add(zoneDeploiementSurvivant);

		//bouton valider reserviste
		JButton deployerSurvivant = new JButton("Deployer");
        deployerSurvivant.setBounds(896, 350, 200, 40);
        deployerSurvivant.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				if(choixSurvivants.getItemCount()>0){
					//deployer survivant sur une zone
					etuSelect = survivants.get(choixSurvivants.getSelectedIndex());
					System.out.println(etuSelect.getNom());
					//demande au controller d'effectuer le deploiment
					if(etuSelect.getZone().getIndiceZone() == zoneDeploiementSurvivant.getSelectedIndex()){
						System.out.println("erreur");
					}
					else{
						System.out.println("OK");
						System.out.println(etuSelect.getNom());
						controller.deployerSurvivant(etuSelect, zoneDeploiementSurvivant.getSelectedIndex());
						//update de la liste des survivants
						choixSurvivants.removeAll();
						survivants = controller.getSurvivants();
						for(Etudiant etu:survivants){
							choixSurvivants.add(etu.getNom());
						}
						choixSurvivants.update(null);
						
					}
					update();	
				}
			}
        });
        treveView.add(deployerSurvivant);

		//bouton valider 

		JButton btnValider = new JButton("Valider");
		btnValider.setFont(new Font("Tahoma", Font.BOLD, 20));
        btnValider.setBounds(896, 525, 200, 50);
		btnValider.updateUI();
		treveView.add(btnValider);
        btnValider.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
				controller.arreter();
            }
        });

		affCreditZone();
		
		treveView.validate();
	}

	private void affCreditZone(){
		//affichage des credits restants par zone 
		JLabel lblCreditRestant = new JLabel("Credit Restant par zone");
		lblCreditRestant.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCreditRestant.setBounds(196, 460, 500, 26);
		treveView.add(lblCreditRestant);
		lblCreditZone = new JLabel[5];
		this.totalCredit=0;
		for(byte i = 0;i<5;i++){
			int credit = controller.getCreditRestant(i);
			this.totalCredit += credit;
			lblCreditZone[i] = new JLabel();
			lblCreditZone[i].setFont(new Font("Tahoma", Font.BOLD, 20));
			lblCreditZone[i].setBounds(210, 500+i*25, 500, 26);
			lblCreditZone[i].setText(controleZoneString[controller.getControllee(i)] + "  " + Utils.zoneIndexToString(i) + " = " + credit);
			treveView.add(lblCreditZone[i]);
		}
		//affichage total credit
		lblCreditTotal = new JLabel("Total = " + this.totalCredit);
		lblCreditTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCreditTotal.setBounds(210, 650, 500, 26);
		treveView.add(lblCreditTotal);
	}

	public void update(){
		for(byte i = 0;i<5;i++){
			int credit = controller.getCreditRestant(i);
			this.totalCredit += credit;
			lblCreditZone[i].setText(controleZoneString[controller.getControllee(i)] + "  " + Utils.zoneIndexToString(i) + " = " + credit);
		}
	}
}
