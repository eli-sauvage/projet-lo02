import java.awt.*;
import java.awt.event.*;
import java.util.Map;

import javax.swing.border.LineBorder;
import javax.xml.crypto.URIReference;
import javax.xml.transform.stream.StreamSource;
import javax.swing.*;

public class GUI {

	public Joueur currentJoueur;

	public  JFrame menu = new JFrame();
	public  JFrame interfaceArmee = new JFrame();
	private  JTextField textField;
	private  Choice selectionBranche;

	private  TextField force;
	private  TextField dexterite;
	private  TextField resistance;
	private  TextField constitution;
	private  TextField initiative;
	private  Choice choice;
	private  Choice strategy;
	private  Choice choixEtudiant;
	private  JCheckBox reserviste;
	private  TextField pointsDistribuer;
	private  TextField nom;
	private  Choice programme;
	private Color bgColor = new Color(255, 128, 192);
	public Partie game = new Partie(true);
	
	public GUI() {
		System.out.println("Start GUI");
		interfaceMenu();
	}

	
	private void interfaceMenu() {
		//menu 
		menu.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 30));
		menu.getContentPane().setBackground(bgColor);
		menu.setBounds(10, 10, 1500, 800);
		menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JLabel label1 = new JLabel("C'est du brutal !");
		label1.setBounds(583, 43, 284, 37);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		label1.setFont(new Font("Tahoma", Font.BOLD, 30));
		JButton btnStart = new JButton("Demarrer une partie");
		btnStart.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnStart.setBounds(614, 228, 212, 44);
		menu.getContentPane().setLayout(null);
		menu.getContentPane().add(label1);
		menu.getContentPane().add(btnStart);
		JButton btnRules = new JButton("Afficher les règles");
		btnRules.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRules.setBounds(614, 402, 212, 44);
		menu.getContentPane().add(btnRules);
		JLabel label2 = new JLabel("Eli Sauvage - Esteban Mercier");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label2.setBounds(1191, 688, 219, 37);
		menu.getContentPane().add(label2);	
		menu.setVisible(true);
		//action listeners menu
		btnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					System.out.println("start");	
					configPartie();
					game.setup();	
			}
		});
		btnRules.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					System.out.println("rules");
			}
		});	
	}
		
	private void affInterfaceArmee(Joueur joueur,int indiceJoueur){
		//interface armee
		
		System.out.println("print interface armee");
		interfaceArmee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfaceArmee.setBounds(10, 10, 1500, 800);
		interfaceArmee.getContentPane().setBackground(bgColor);
		interfaceArmee.setLayout(null);
		JLabel label11 = new JLabel("Interface Armee");
		label11.setBounds(46, 35, 228, 31);
		label11.setFont(new Font("Tahoma", Font.BOLD, 25));
		interfaceArmee.add(label11);
		JLabel label12 = new JLabel("Nom Joueur ");
		label12.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label12.setBounds(460, 48, 102, 13);
		interfaceArmee.add(label12);
		textField = new JTextField();
		textField.setBounds(572, 47, 96, 19);
		interfaceArmee.add(textField);
		textField.setColumns(10);
		//choix branche du joueur
		JLabel label13 = new JLabel("Branche");
		label13.setFont(new Font("Tahoma", Font.PLAIN, 15));
		label13.setBounds(745, 53, 66, 13);
		interfaceArmee.add(label13);
		selectionBranche = new Choice();
		selectionBranche.setFont(new Font("Tahoma", Font.PLAIN, 24));
		selectionBranche.setBounds(810, 42, 72, 34);
		selectionBranche.add("ISI");
		selectionBranche.add("GM");
		selectionBranche.add("A2I");
		selectionBranche.add("RT");
		selectionBranche.add("MTE");
		selectionBranche.add("GI");
		selectionBranche.add("MM");
		interfaceArmee.add(selectionBranche);

		//selection elites
		JLabel lblElite = new JLabel("Etudiants");
		lblElite.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblElite.setBounds(200, 200, 117, 37);
		interfaceArmee.add(lblElite);
		choixEtudiant = new Choice();
		choixEtudiant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		choixEtudiant.setBounds(400, 299, 224, 31);
		for (int i = 0; i < 20; i++) {
			choixEtudiant.add(joueur.getArmee().getEtudiants()[i].getNom());
		}
		interfaceArmee.add(choixEtudiant);

		// Compteur des points à distribuer
		JLabel lblNewLabel_4 = new JLabel("Points a distribuer");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(72, 300, 192, 26);
		interfaceArmee.add(lblNewLabel_4);
		pointsDistribuer = new TextField();
		pointsDistribuer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pointsDistribuer.setText("400");
		pointsDistribuer.setBounds(297, 300, 61, 37);
		interfaceArmee.add(pointsDistribuer);

		// Force
		JLabel lblNewLabel_5 = new JLabel("Force");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(193, 389, 61, 37);
		interfaceArmee.add(lblNewLabel_5);
		force = new TextField();
		force.setFont(new Font("Tahoma", Font.PLAIN, 24));
		force.setText("0");
		force.setBounds(297, 389, 61, 37);
		interfaceArmee.add(force);

		// Dextérité
		JLabel lblNewLabel_6 = new JLabel("Dexterite");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(159, 432, 105, 37);
		interfaceArmee.add(lblNewLabel_6);
		dexterite = new TextField();
		dexterite.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dexterite.setText("0");
		dexterite.setBounds(297, 432, 61, 37);
		interfaceArmee.add(dexterite);

		// Résistance
		JLabel lblNewLabel_7 = new JLabel("Resistance");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(147, 479, 117, 37);
		interfaceArmee.add(lblNewLabel_7);
		resistance = new TextField();
		resistance.setFont(new Font("Tahoma", Font.PLAIN, 20));
		resistance.setText("0");
		resistance.setBounds(297, 475, 61, 37);
		interfaceArmee.add(resistance);

		// Constitution
		JLabel lblNewLabel_8 = new JLabel("Constitution");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(136, 522, 128, 37);
		interfaceArmee.add(lblNewLabel_8);
		constitution = new TextField();
		constitution.setFont(new Font("Tahoma", Font.PLAIN, 20));
		constitution.setText("0");
		constitution.setBounds(297, 518, 61, 37);
		interfaceArmee.add(constitution);

		// Initiative
		JLabel lblNewLabel_9 = new JLabel("Initiative");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_9.setBounds(171, 569, 93, 29);
		interfaceArmee.add(lblNewLabel_9);
		initiative = new TextField();
		initiative.setFont(new Font("Tahoma", Font.PLAIN, 20));
		initiative.setText("0");
		initiative.setBounds(297, 561, 61, 37);
		interfaceArmee.add(initiative);

		// Affectation
		JLabel lblNewLabel_10 = new JLabel("Affectation");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_10.setBounds(422, 389, 117, 37);
		interfaceArmee.add(lblNewLabel_10);
		choice = new Choice();
		choice.setFont(new Font("Tahoma", Font.PLAIN, 20));
		choice.setBounds(562, 392, 224, 31);
		choice.add("BDE");
		choice.add("Bibliothéque");
		choice.add("Quartier administratif");
		choice.add("Halle industrielle");
		choice.add("Halle sportive");
		interfaceArmee.add(choice);

		// type de stratégie
		JLabel lblNewLabel_11 = new JLabel("Strategie");
		lblNewLabel_11.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblNewLabel_11.setBounds(443, 452, 96, 37);
		interfaceArmee.add(lblNewLabel_11);
		strategy = new Choice();
		strategy.setFont(new Font("Tahoma", Font.PLAIN, 20));
		strategy.setBounds(562, 455, 224, 31);
		strategy.add("Offensive");
		strategy.add("Défensive");
		strategy.add("Aléatoire");
		interfaceArmee.add(strategy);

		// Réserviste ?
		reserviste = new JCheckBox("Reserviste");
		reserviste.setBackground(bgColor);
		reserviste.setForeground(Color.BLACK);
		reserviste.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reserviste.setBounds(579, 513, 187, 26);
		
		interfaceArmee.add(reserviste);

		JButton ok = new JButton("OK");
		ok.setBounds(711, 545, 40, 30);
		ok.setFont(new Font("Tahoma", Font.PLAIN, 18));
		interfaceArmee.add(ok);

		JButton randomStats = new JButton("Stats Aleatoire");
		randomStats.setBounds(600, 650, 250, 80);
		randomStats.setFont(new Font("Tahoma", Font.PLAIN, 18));
		interfaceArmee.add(randomStats);

		JButton validation = new JButton("Appliquer");
		validation.setFont(new Font("Tahoma", Font.PLAIN, 24));
		validation.setBounds(300, 650, 150, 80);
		interfaceArmee.add(validation);
	
		JPanel panel = new JPanel();
		panel.setBackground(bgColor);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setBounds(51, 345, 769, 261);
		interfaceArmee.add(panel);
		interfaceArmee.setVisible(true);


		//bouton random stats
		randomStats.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("random ");
			    joueur.getArmee().statsAleatoires();
				joueur.getArmee().reservistesAleatoires();
				//game.getChamp().repartirTroupeAleatoirement(joueur.getArmee().getEtudiants(), indiceJoueur);
				//refresh
				final Etudiant selectedEtudiant = joueur.getArmee().getEtudiants()[choixEtudiant.getSelectedIndex()];
				force.setText(Integer.toString(selectedEtudiant.getForce()));
				dexterite.setText(Integer.toString(selectedEtudiant.getDexterite()));
				constitution.setText(Integer.toString(selectedEtudiant.getConsitution()));
				resistance.setText(Integer.toString(selectedEtudiant.getResistance()));
				initiative.setText(Integer.toString(selectedEtudiant.getInitiative()));
				reserviste.setSelected(selectedEtudiant.getReserviste());
				randomStats.setEnabled(false);	
			}
		});
		validation.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.out.println("BTN appliquer ");
				final Etudiant selectedEtudiant = joueur.getArmee().getEtudiants()[choixEtudiant.getSelectedIndex()];
				//set de tout les stats de l'étudiant a l'affichage
				selectedEtudiant.setForce(Integer.parseInt(force.getText()));
				selectedEtudiant.setDexterite(Integer.parseInt(dexterite.getText()));
				selectedEtudiant.setResistance(Integer.parseInt(resistance.getText()));
				selectedEtudiant.setInitiative(Integer.parseInt(initiative.getText()));
				selectedEtudiant.setConsitution(Integer.parseInt(constitution.getText()));
				selectedEtudiant.setReserviste(reserviste.isSelected());		
			}
		});
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("OKKKK");
			}
		});
		selectionBranche.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				String str = selectionBranche.getItem(selectionBranche.getSelectedIndex());
				joueur.setBranche(str);
				System.out.println(str);
			}
		});
		choixEtudiant.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				System.out.println(choixEtudiant.getSelectedIndex());
				final Etudiant selectedEtudiant = joueur.getArmee().getEtudiants()[choixEtudiant.getSelectedIndex()];
				//set de tout les stats de l'étudiant a l'affichage
				force.setText(Integer.toString(selectedEtudiant.getForce()));
				dexterite.setText(Integer.toString(selectedEtudiant.getDexterite()));
				constitution.setText(Integer.toString(selectedEtudiant.getConsitution()));
				resistance.setText(Integer.toString(selectedEtudiant.getResistance()));
				initiative.setText(Integer.toString(selectedEtudiant.getInitiative()));
				reserviste.setSelected(selectedEtudiant.getReserviste());
				
			}
		});
	}
    
	private void configPartie(){
		//joueur 1 choisi son armée
		menu.setVisible(false);
		affInterfaceArmee(game.getJoueur(0),0);
	}

	

}
	

   
    




