package views;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import controllers.*;

import models.*;
import models.strategies.Aleatoire;
import models.strategies.Defensif;
import models.strategies.Offensif;
import models.strategies.Strategie;

/**
 * la vue qui affiche les treves
 */
public class TreveView {

	public JFrame treveView = new JFrame();
	private Choice choixReservistes;
	private Choice choixSurvivants;
	private Choice choixStrategie;
	private Choice zoneDeploiementReserviste;
	private Choice zoneDeploiementSurvivant;
	private Color bgColor = new Color(255, 128, 192);
	private int totalCredit;
	private TreveController controller;
	private ArrayList<Etudiant> reservistes = new ArrayList<>();
	private ArrayList<Etudiant> survivants = new ArrayList<>();
	// private Etudiant etuSelect = new Etudiant(1,1);
	private Etudiant survivantSelect = null;
	private Etudiant reservisteSelect = null;
	private String[] controleZoneString = new String[] {"--", "J1", "J2"};
	private JLabel[] lblCreditZone;
	private JLabel lblCreditTotal;
	private int numeroJoueur;
	private int nbCreditEtuSelect;

	JLabel lblNbCredit;
	/**
	 * @param controller le controller associe a la vue
	 * @param numeroJoueur le numero du joueur actuel
	 */
	public TreveView(TreveController controller, int numeroJoueur) {
		this.numeroJoueur = numeroJoueur;
		this.controller = controller;
		affTreveView();
		treveView.setVisible(true);
	}
	/**
	 * ferme la fenetre de la treve
	 */
	public void fermer() {
		treveView.setVisible(false);
	}
	/**
	 * affiche la vuex de la treve
	 */
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
		choixReservistes.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//update de l'affichage du nb de credit
				update();
			}
		});
		for (Etudiant etuReserviste : reservistes) {
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
				if (choixReservistes.getItemCount() > 0 && reservisteSelect != null) {
					//controller.deployerReserviste();
					System.out.println(reservisteSelect.getNom());
					//demande au controller d'effectuer le deploiment
					controller.deployerReserviste(reservisteSelect,
							zoneDeploiementReserviste.getSelectedIndex());
					//re affichage de la liste update
					choixReservistes.removeAll();
					reservistes = controller.getReserviste();
					for (Etudiant etu : reservistes) {
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
		choixSurvivants.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//update de l'affichage du nb de credit
				update();
			}
		});
		for (Etudiant etuReserviste : survivants) {
			choixSurvivants.add(etuReserviste.getNom());
		}
		treveView.add(choixSurvivants);
		//label
		JLabel lblZoneActuel = new JLabel("Zone : ");
		lblZoneActuel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblZoneActuel.setBounds(1200, 190, 600, 36);
		treveView.add(lblZoneActuel);
		//condition si plus de survivant dans la liste
		if (survivants.size() > 0) {
			lblZoneActuel.setText("Zone : "
					+ Utils.zoneIndexToString(survivants.get(0).getZone().getIndiceZone()));
		} else {
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
		zoneDeploiementSurvivant.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				//update de l'affichage du nb de credit
				nbCreditEtuSelect = survivants.get(choixSurvivants.getSelectedIndex()).getCredits();
				update();
			}
		});
		treveView.add(zoneDeploiementSurvivant);

		//bouton valider reserviste
		JButton deployerSurvivant = new JButton("Deployer");
		deployerSurvivant.setBounds(896, 520, 200, 40);
		deployerSurvivant.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (choixSurvivants.getItemCount() > 0 && survivantSelect != null) {
					//deployer survivant sur une zone
					System.out.println(survivantSelect.getNom());
					//demande au controller d'effectuer le deploiment
					if (survivantSelect.getZone().getIndiceZone() == zoneDeploiementSurvivant
							.getSelectedIndex()) {
								JFrame f = new JFrame();
								JDialog d;
								d = new JDialog(f, "Erreur", true);
								d.setLayout(new FlowLayout());
								JButton b = new JButton("OK");
								b.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										d.setVisible(false);
									}
								});
								d.add(new JLabel("l'étudiant est déjà dans cette zone !"));
								d.add(b);
								d.setSize(300, 100);
								d.setLocationRelativeTo(null);
								d.setVisible(true);
					} else {
						System.out.println("OK");
						System.out.println(survivantSelect.getNom());
						String stratStr = choixStrategie.getSelectedItem();
						Strategie strat = null;
						if (stratStr.equals("Offensive"))
							strat = new Offensif();
						if (stratStr.equals("Defensive"))
							strat = new Defensif();
						if (stratStr.equals("Aleatoire"))
							strat = new Aleatoire();
						survivantSelect.setStrategie(strat);
						controller.deployerSurvivant(survivantSelect,
								zoneDeploiementSurvivant.getSelectedIndex());
						//update de la liste des survivants
						choixSurvivants.removeAll();
						survivants = controller.getSurvivants();
						for (Etudiant etu : survivants) {
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
		btnValider.setBounds(896, 650, 200, 50);
		treveView.add(btnValider);
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.arreter();
			}
		});
		affCreditZone();

		//-------------------------------------------------------//

		//affichage profil etu selectionner

		//label 
		lblNbCredit = new JLabel("Credits : " + nbCreditEtuSelect);
		lblNbCredit.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNbCredit.setBounds(896, 350, 300, 26);
		treveView.add(lblNbCredit);
		//label 
		JLabel lnlStrategie = new JLabel("Stategie : ");
		lnlStrategie.setFont(new Font("Tahoma", Font.BOLD, 20));
		lnlStrategie.setBounds(896, 400, 300, 26);
		treveView.add(lnlStrategie);
		//choix de sa strategie
		choixStrategie = new Choice();
		choixStrategie.setFont(new Font("Tahoma", Font.BOLD, 20));
		choixStrategie.setBounds(896, 450, 250, 30);
		choixStrategie.add("         ");
		choixStrategie.add("Defensive");
		choixStrategie.add("Offensive");
		choixStrategie.add("Aleatoire");
		choixStrategie.select(0);
		treveView.add(choixStrategie);
		treveView.validate();
		update();
	}

	/**
	 * affiche les credits restants par zone
	 */
	private void affCreditZone() {
		//affichage des credits restants par zone 
		JLabel lblCreditRestant = new JLabel("Credit Restant par zone");
		lblCreditRestant.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCreditRestant.setBounds(196, 460, 500, 26);
		treveView.add(lblCreditRestant);
		lblCreditZone = new JLabel[5];
		this.totalCredit = 0;
		for (byte i = 0; i < 5; i++) {
			int credit = controller.getCreditRestant(i);
			this.totalCredit += credit;
			lblCreditZone[i] = new JLabel();
			lblCreditZone[i].setFont(new Font("Tahoma", Font.BOLD, 20));
			lblCreditZone[i].setBounds(210, 500 + i * 25, 500, 26);
			lblCreditZone[i].setText(controleZoneString[controller.getControllee(i)] + "  "
					+ Utils.zoneIndexToString(i) + " = " + credit);
			treveView.add(lblCreditZone[i]);
		}
		//affichage total credit
		lblCreditTotal = new JLabel("Total = " + this.totalCredit);
		lblCreditTotal.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblCreditTotal.setBounds(210, 650, 500, 26);
		treveView.add(lblCreditTotal);
	}
	/**
	 * met a jour la vue lorque l'utilisateur effectue une action
	 */
	public void update() {
		affCreditZone();
		int survivantIndex = choixSurvivants.getSelectedIndex();
		if (survivantIndex != -1) {
			survivantSelect = survivants.get(survivantIndex);
			nbCreditEtuSelect = survivants.get(survivantIndex).getCredits();
		} else {
			survivantSelect = null;
			nbCreditEtuSelect = 0;
		}
		int reservisteIndex = choixReservistes.getSelectedIndex();
		if (reservisteIndex != -1)
			reservisteSelect = reservistes.get(reservisteIndex);
		else
			reservisteSelect = null;


		for (byte i = 0; i < 5; i++) {
			int credit = controller.getCreditRestant(i);
			this.totalCredit += credit;
			lblCreditZone[i].setText(controleZoneString[controller.getControllee(i)] + "  "
					+ Utils.zoneIndexToString(i) + " = " + credit);
		}
		//update nb credit 
		lblNbCredit.setText("Credits : " + nbCreditEtuSelect);
		//update strategie
		if (survivantSelect != null) {
			if (survivantSelect.getStrategie() instanceof Defensif)
				choixStrategie.select("Defensive");
			if (survivantSelect.getStrategie() instanceof Offensif)
				choixStrategie.select("Offensive");
			if (survivantSelect.getStrategie() instanceof Aleatoire)
				choixStrategie.select("Aleatoire");
		}
		treveView.validate();
	}
}
