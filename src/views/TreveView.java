package views;

import javax.swing.*;
import javax.swing.border.LineBorder;

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

	private TreveController controller;
	private ArrayList<Etudiant> reservistes = new ArrayList<>();
	private ArrayList<Etudiant> survivants = new ArrayList<>();
	private Etudiant etuSelect;;
	public TreveView(TreveController controller) {
		this.controller = controller;
		affTreveView();
	}

	public void fermer() {
		treveView.setVisible(false);
	}

	private void affTreveView() {
		// interface treve
		treveView.setVisible(true);
		System.out.println("print treve view");
		treveView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		treveView.getContentPane().setBackground(Utils.bgColor);
		treveView.setBounds(10, 10, 1500, 800);
		treveView.setBackground(bgColor);
		treveView.setLayout(null);
		JLabel label11 = new JLabel("Treve - Joueur ");
		label11.setBounds(46, 35, 400, 31);
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
		choixSurvivants.setBounds(896, 300, 200, 26);
		for(Etudiant etuReserviste:survivants){
			choixSurvivants.add(etuReserviste.getNom());
		}
		treveView.add(choixSurvivants);
		//label
		JLabel lblZoneActuel = new JLabel("Zone : ");
		lblZoneActuel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblZoneActuel.setBounds(1200, 200, 600, 36);
		treveView.add(lblZoneActuel);
		//condition si plus de survivant dans la liste
		if(survivants.size()>0){
			//lblZoneActuel.setText("Zone : " + Utils.zoneIndexToString(survivants.get(choixSurvivants.getSelectedIndex()).getZone().getIndiceZone()));
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
               //deployer survivant sur une zone
			   
            }
        });
        treveView.add(deployerSurvivant);
		
	}
		
}
