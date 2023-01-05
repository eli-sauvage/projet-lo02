package views;

import javax.swing.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;

import models.*;

import controllers.*;

public class CombatsView {
    CombatsController controller;
    JFrame frame;
    ImageIcon map;
    JLabel scoreBU, scoreHS, scoreQA, scoreBDE, scoreHI;
    private Color bgColor = new Color(255, 128, 192);

    public CombatsView(CombatsController controller) {
        this.controller = controller;

        frame = new JFrame("Overlay App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane panel = new JLayeredPane();

        panel.setLayout(null);

        File mapFile = new File("ressources/map.png");
        BufferedImage mapBI = null;
        try {
            mapBI = ImageIO.read(mapFile);
        } catch (Exception e) {
            System.out.println(e);
        }
        ImageIcon mapIcon = new ImageIcon(mapBI);
        JLabel map = new JLabel(mapIcon);

        map.setBounds(0, 0, mapIcon.getIconWidth(), mapIcon.getIconHeight());
        panel.add(map, 1, 0);

        frame.setBounds(0, 0, mapIcon.getIconWidth(), mapIcon.getIconHeight() + 100);

        JButton lancerCombat = new JButton("lancer le combat");
        lancerCombat.setBounds(0, mapIcon.getIconHeight(), 200, 30);
        lancerCombat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.lancerCombat();
            }
        });
        panel.add(lancerCombat, 4, 0);

        File HSF = new File("ressources/cercle.png");
        BufferedImage HSBI = null;
        try {
            HSBI = ImageIO.read(HSF);
        } catch (Exception e) {
            System.out.println(e);
        }
        ImageIcon HSImage = new ImageIcon(HSBI);
        JLabel HSCercle = new JLabel(HSImage);
        HSCercle.setBounds(250, 65, HSImage.getIconWidth(), HSImage.getIconHeight());
        panel.add(HSCercle, 2, 0);

        scoreHS = new JLabel("<html>J1:0<br/>J2:0</html>");
        scoreHS.setFont(new Font("Tahoma", Font.BOLD, 20));
        scoreHS.setBounds(285, 85, 50, 50);
        scoreHS.setVisible(true);
        panel.add(scoreHS, 3, 0);

        File BDEF = new File("ressources/cercle.png");
        BufferedImage BDEBI = null;
        try {
            BDEBI = ImageIO.read(BDEF);
        } catch (Exception e) {
            System.out.println(e);
        }
        ImageIcon BDEImage = new ImageIcon(BDEBI);
        JLabel BDECercle = new JLabel(BDEImage);
        BDECercle.setBounds(390, 400, BDEImage.getIconWidth(), BDEImage.getIconHeight());
        panel.add(BDECercle, 2, 0);

        scoreBDE = new JLabel("<html>J1:0<br/>J2:0</html>");
        scoreBDE.setFont(new Font("Tahoma", Font.BOLD, 20));
        scoreBDE.setBounds(425, 420, 50, 50);
        scoreBDE.setVisible(true);
        panel.add(scoreBDE, 3, 0);

        File BUF = new File("ressources/cercle.png");
        BufferedImage BUI = null;
        try {
            BUI = ImageIO.read(BUF);
        } catch (Exception e) {
            System.out.println(e);
        }
        ImageIcon BUImage = new ImageIcon(BUI);
        JLabel BUCercle = new JLabel(BUImage);
        BUCercle.setBounds(440, 510, BUImage.getIconWidth(), BUImage.getIconHeight());
        panel.add(BUCercle, 2, 0);

        scoreBU = new JLabel("<html>J1:0<br/>J2:0</html>");
        scoreBU.setFont(new Font("Tahoma", Font.BOLD, 20));
        scoreBU.setBounds(475, 530, 50, 50);
        scoreBU.setVisible(true);
        panel.add(scoreBU, 3, 0);

        File QAF = new File("ressources/cercle.png");
        BufferedImage QAI = null;
        try {
            QAI = ImageIO.read(QAF);
        } catch (Exception e) {
            System.out.println(e);
        }
        ImageIcon QAImage = new ImageIcon(QAI);
        JLabel QACercle = new JLabel(QAImage);
        QACercle.setBounds(475, 630, QAImage.getIconWidth(), QAImage.getIconHeight());
        panel.add(QACercle, 2, 0);

        scoreQA = new JLabel("<html>J1:0<br/>J2:0</html>");
        scoreQA.setFont(new Font("Tahoma", Font.BOLD, 20));
        scoreQA.setBounds(510, 650, 50, 50);
        scoreQA.setVisible(true);
        panel.add(scoreQA, 3, 0);

        File HIF = new File("ressources/cercle.png");
        BufferedImage HII = null;
        try {
            HII = ImageIO.read(HIF);
        } catch (Exception e) {
            System.out.println(e);
        }
        ImageIcon HIImage = new ImageIcon(HII);
        JLabel HICercle = new JLabel(HIImage);
        HICercle.setBounds(700, 600, HIImage.getIconWidth(), HIImage.getIconHeight());
        panel.add(HICercle, 2, 0);

        scoreHI = new JLabel("<html>J1:0<br/>J2:0</html>");
        scoreHI.setFont(new Font("Tahoma", Font.BOLD, 20));
        scoreHI.setBounds(735, 620, 50, 50);
        scoreHI.setVisible(true);
        panel.add(scoreHI, 3, 0);

        frame.add(panel);
        update();
        frame.setVisible(true);
    }

    public void update() {
        JLabel[] labels = {scoreBU, scoreBDE, scoreQA, scoreHI, scoreHS};
        int i = 0;
        for (JLabel label : labels) {
            int[] survivants = controller.survivantsZone(i++);
            label.setText("<html>J1:" + survivants[0] + "<br/>J2:" + survivants[1] + "</html>");
        }
    }

    public void finDuCombat(String nomZone, int gagnant) {
        JFrame f = new JFrame();
        JDialog d;
        d = new JDialog(f, "Fin du combat", true);
        d.setLayout(new FlowLayout());
        JButton b = new JButton("OK");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                d.setVisible(false);
                frame.setVisible(false);
                controller.continuer();
            }
        });
        d.add(new JLabel("Gagnant du combat : J" + gagnant + " controle la zone " + nomZone));
        d.add(b);
        d.setSize(500, 70);
        d.setLocationRelativeTo(null);
        d.setVisible(true);
    }

    public void fermer() {
        frame.setVisible(false);
    }
}
