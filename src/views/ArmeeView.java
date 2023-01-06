package views;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

import controllers.*;

import models.strategies.*;
import models.*;

public class ArmeeView {

	public JFrame interfaceArmee = new JFrame();
	private JTextField textField;

	private JSpinner force;
	private JSpinner dexterite;
	private JSpinner resistance;
	private JSpinner constitution;
	private JSpinner initiative;
	// private Choice zone;
	private JComboBox<String> zone;
	private Choice strategy;
	private Choice choixEtudiant;
	private JCheckBox reserviste;
	private JLabel pointsDistribuer;
	private JLabel lblElite;
	// private TextField nom;
	// private Choice programme;

	private ArmeController controller;

	public ArmeeView(ArmeController controller) {
		this.controller = controller;
		affInterfaceArmee();
	}

	public void fermer() {
		interfaceArmee.setVisible(false);
	}

	private void affInterfaceArmee() {
		// interface armee

		System.out.println("print interface armee");
		interfaceArmee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfaceArmee.setBounds(10, 10, 1500, 800);
		interfaceArmee.getContentPane().setBackground(Utils.bgColor);
		interfaceArmee.setLayout(null);
		JLabel label11 = new JLabel("Interface Armee -- Joueur " + controller.getNumeroJoueur());
		label11.setBounds(46, 35, 400, 31);
		label11.setFont(new Font("Tahoma", Font.BOLD, 25));
		interfaceArmee.add(label11);

		// selection etudiants
		lblElite = new JLabel("Type : Etudiant");
		lblElite.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblElite.setBounds(550, 300, 300, 26);
		interfaceArmee.add(lblElite);
		// liste etudiants
		choixEtudiant = new Choice();
		choixEtudiant.setFont(new Font("Tahoma", Font.PLAIN, 20));
		choixEtudiant.setBounds(300, 300, 224, 31);
		for (int i = 0; i < 20; i++) {
			choixEtudiant.add(controller.getNomEtudiant(i));
		}
		interfaceArmee.add(choixEtudiant);
		// image joueur en cours
		JPanel panelImage = new JPanel();
		panelImage.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panelImage.setBackground(Color.CYAN);
		panelImage.setBounds(79, 102, 70, 122);
		panelImage.setLayout(new GridLayout(1, 1, 0, 0)); // politique de placement des composants dans ce panel
		JButton jb1 = new JButton(); // pour représenter un personnage, utilisation d'un JButton
		panelImage.add(jb1);
		jb1.setForeground(Utils.bgColor);
		// Image imEtudiant = new ImageIcon("ressources\\etudiant.png").getImage();
		// Image imElite = new ImageIcon("ressources\\etudiant.png").getImage();
		// Image imMaitre = new ImageIcon("ressources\\etudiant.png").getImage();

		interfaceArmee.add(panelImage);

		// Compteur des points à distribuer
		JLabel lblNewLabel_4 = new JLabel("Points a distribuer : ");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_4.setBounds(72, 300, 192, 26);
		interfaceArmee.add(lblNewLabel_4);
		pointsDistribuer = new JLabel();
		pointsDistribuer.setFont(new Font("Tahoma", Font.PLAIN, 20));
		pointsDistribuer.setText("400");
		pointsDistribuer.setBounds(250, 300, 61, 26);
		interfaceArmee.add(pointsDistribuer);

		// Force
		JLabel lblNewLabel_5 = new JLabel("Force");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_5.setBounds(193, 389, 61, 37);
		interfaceArmee.add(lblNewLabel_5);
		force = new JSpinner(new SpinnerNumberModel(0, 0, 400, 1));
		force.setFont(new Font("Tahoma", Font.PLAIN, 24));
		force.setBounds(297, 389, 61, 37);
		interfaceArmee.add(force);

		// Dextérité
		JLabel lblNewLabel_6 = new JLabel("Dexterite");
		lblNewLabel_6.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_6.setBounds(159, 432, 105, 37);
		interfaceArmee.add(lblNewLabel_6);
		dexterite = new JSpinner(new SpinnerNumberModel(0, 0, 400, 1));
		;
		dexterite.setFont(new Font("Tahoma", Font.PLAIN, 20));
		dexterite.setBounds(297, 432, 61, 37);
		interfaceArmee.add(dexterite);

		// Résistance
		JLabel lblNewLabel_7 = new JLabel("Resistance");
		lblNewLabel_7.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_7.setBounds(147, 479, 117, 37);
		interfaceArmee.add(lblNewLabel_7);
		resistance = new JSpinner(new SpinnerNumberModel(0, 0, 400, 1));
		;
		resistance.setFont(new Font("Tahoma", Font.PLAIN, 20));
		resistance.setBounds(297, 475, 61, 37);
		interfaceArmee.add(resistance);

		// Constitution
		JLabel lblNewLabel_8 = new JLabel("Constitution");
		lblNewLabel_8.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_8.setBounds(136, 522, 128, 37);
		interfaceArmee.add(lblNewLabel_8);
		constitution = new JSpinner(new SpinnerNumberModel(0, 0, 400, 1));
		;
		constitution.setFont(new Font("Tahoma", Font.PLAIN, 20));
		constitution.setBounds(297, 518, 61, 37);
		interfaceArmee.add(constitution);

		// Initiative
		JLabel lblNewLabel_9 = new JLabel("Initiative");
		lblNewLabel_9.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_9.setBounds(171, 569, 93, 29);
		interfaceArmee.add(lblNewLabel_9);
		initiative = new JSpinner(new SpinnerNumberModel(0, 0, 400, 1));
		;
		initiative.setFont(new Font("Tahoma", Font.PLAIN, 20));
		initiative.setBounds(297, 561, 61, 37);
		interfaceArmee.add(initiative);

		// Affectation
		JLabel lblNewLabel_10 = new JLabel("Affectation");
		lblNewLabel_10.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_10.setBounds(422, 389, 117, 37);
		interfaceArmee.add(lblNewLabel_10);
		// zone = new Choice();
		zone = new JComboBox<String>(new DefaultComboBoxModel<String>() {// pas selectionnable mais selectionné au début
			@Override
			public void setSelectedItem(Object item) {
				if (item == null) {
					super.setSelectedItem("");
					return;
				}
				if (item.toString().equals(""))
					return;
				super.setSelectedItem(item);
			};
		}) {
			@Override
			public Object getSelectedItem() {
				Object selected = super.getSelectedItem();
				if (selected == null)
					selected = "";
				return selected;
			}
		};
		zone.setFont(new Font("Tahoma", Font.PLAIN, 20));
		zone.setBounds(562, 392, 224, 31);
		for (int i = 0; i < 5; i++) {
			zone.addItem(Utils.zoneIndexToString(i));
		}
		zone.addItem("");
		zone.setSelectedItem(null);
		interfaceArmee.add(zone);

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
		reserviste.setBackground(Utils.bgColor);
		reserviste.setForeground(Color.BLACK);
		reserviste.setFont(new Font("Tahoma", Font.PLAIN, 20));
		reserviste.setBounds(579, 513, 187, 26);

		interfaceArmee.add(reserviste);

		JButton randomStats = new JButton("Stats Aleatoire");
		randomStats.setBounds(500, 650, 250, 80);
		randomStats.setFont(new Font("Tahoma", Font.PLAIN, 24));
		interfaceArmee.add(randomStats);

		JButton appliquer = new JButton("Appliquer");
		appliquer.setFont(new Font("Tahoma", Font.PLAIN, 24));
		appliquer.setBounds(300, 650, 150, 80);
		interfaceArmee.add(appliquer);

		JButton valider = new JButton("Valider");
		valider.setFont(new Font("Tahoma", Font.PLAIN, 24));
		valider.setBounds(800, 650, 150, 80);
		interfaceArmee.add(valider);

		JPanel panel = new JPanel();
		panel.setBackground(Utils.bgColor);
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 5));
		panel.setBounds(51, 345, 769, 261);
		interfaceArmee.add(panel);
		interfaceArmee.setVisible(true);

		// bouton random stats
		randomStats.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controller.randomStats();
				update();
			}
		});
		appliquer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("BTN appliquer ");
				String stratStr = strategy.getSelectedItem();
				Strategie strat = null;

				if (stratStr.equals("offensif"))
					strat = new Offensif();
				if (stratStr.equals("defensif"))
					strat = new Defensif();
				if (stratStr.equals("aleatoire"))
					strat = new Aleatoire();

				controller.appliquerStats(
						choixEtudiant.getSelectedIndex(),
						(int) force.getValue(),
						(int) dexterite.getValue(),
						(int) resistance.getValue(),
						(int) initiative.getValue(),
						(int) constitution.getValue(),
						reserviste.isSelected(),
						strat,
						zone.getSelectedIndex());

				pointsDistribuer.setText(Integer.toString(controller.getPointsRestants()));
			}
		});
		valider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					controller.valider();
				} catch (Exception exept) {
					JFrame f = new JFrame();
					JDialog d;
					d = new JDialog(f, "Dialog Example", true);
					d.setLayout(new FlowLayout());
					JButton b = new JButton("OK");
					b.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							d.setVisible(false);
						}
					});
					d.add(new JLabel(exept.getMessage()));
					d.add(b);
					d.setSize(300, 100);
					d.setLocationRelativeTo(null);
					d.setVisible(true);
				}
			}
		});
		choixEtudiant.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				update();
			}
		});
	}

	public void update() {
		final Etudiant selectedEtudiant = controller.getEtudiant(choixEtudiant.getSelectedIndex());
		// set de tout les stats de l'étudiant a l'affichage
		try {
			zone.setSelectedIndex(selectedEtudiant.getZone().getIndiceZone());
		} catch (NullPointerException e) {
			zone.setSelectedItem(null);
		}
		force.setValue(selectedEtudiant.getForce());
		dexterite.setValue(selectedEtudiant.getDexterite());
		constitution.setValue(selectedEtudiant.getConsitution());
		resistance.setValue(selectedEtudiant.getResistance());
		initiative.setValue(selectedEtudiant.getInitiative());
		reserviste.setSelected(selectedEtudiant.getReserviste());
		int a = 0;
		if (selectedEtudiant.getStrategie() instanceof Defensif)
			strategy.select("Défensive");
		if (selectedEtudiant.getStrategie() instanceof Offensif)
			strategy.select("Offensive");
		if (selectedEtudiant.getStrategie() instanceof Aleatoire)
			strategy.select("Aléatoire");
		// update image
		if (selectedEtudiant instanceof MaitreDuGobi)
			lblElite.setText("Type : Maitre du Gobi");
		else if (selectedEtudiant instanceof EtudiantElite)
			lblElite.setText("Type : Etudiant Elite");
		else
			lblElite.setText("Type : Etudiant");
	}
}
