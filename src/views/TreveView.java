package views;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

import controllers.*;

import models.*;

public class TreveView {

	public JFrame treveView = new JFrame();
	private Choice choixReservistes;
	private Choice choixSurvivants;
	private Color bgColor = new Color(255, 128, 192);

	private TreveController controller;

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
		
		JLabel lblNewLabel = new JLabel("Choix des reservistes");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(196, 162, 225, 36);
		treveView.add(lblNewLabel);
		
		JLabel lblRedeploiementDesSurvivants = new JLabel("Redeploiement des survivants");
		lblRedeploiementDesSurvivants.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblRedeploiementDesSurvivants.setBounds(896, 162, 310, 36);
		treveView.add(lblRedeploiementDesSurvivants);
		
		choixReservistes = new Choice();
		choixReservistes.setFont(new Font("Tahoma", Font.BOLD, 20));
		choixReservistes.setBounds(196, 238, 200, 26);
		for(Etudiant etuReserviste:controller.getReserviste()){
			choixReservistes.add(etuReserviste.getNom());
		}
		treveView.add(choixReservistes);
		
		choixSurvivants = new Choice();
		choixSurvivants.setFont(new Font("Tahoma", Font.BOLD, 20));
		choixSurvivants.setBounds(896, 238, 200, 26);
		choixSurvivants.add("eeeeeeee");
		//sois dans une zone controllée
		for(Etudiant etuReserviste:controller.getSurvivants()){
			choixSurvivants.add(etuReserviste.getNom());
		}
		
		System.err.println("sss");
		treveView.add(choixSurvivants);
		

		
	}
		
}
