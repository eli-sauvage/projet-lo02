package views;

import javax.swing.*;

import javax.imageio.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import models.*;

import controllers.*;

public class CombatsView {
    CombatsController controller;
    JFrame frame;
    ImageIcon map;
    JLabel scoreBU, scoreHS, scoreQA, scoreBDE, scoreHI;
    JLabel BUCercle, HSCercle, QACercle, BDECercle, HICercle;
    private int offsetMap;

    public CombatsView(CombatsController controller) {
        this.controller = controller;

        frame = new JFrame("Overlay App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 1500, 800);
        frame.getContentPane().setBackground(Utils.bgColor);


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

        offsetMap = (frame.getWidth()-mapIcon.getIconWidth())/2;
        map.setBounds(offsetMap, 0, mapIcon.getIconWidth(), mapIcon.getIconHeight());
        panel.add(map, 1, 0);


        JButton lancerCombat = new JButton("lancer le combat");
        lancerCombat.setFont(new Font("Tahoma", Font.BOLD, 20));
        lancerCombat.setBounds(offsetMap+mapIcon.getIconWidth(), frame.getHeight()/2-50, offsetMap-50, 100);
        lancerCombat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.lancerCombat();
            }
        });
        panel.add(lancerCombat, 4, 0);

        File cercleFile = new File("ressources/cercle_rouge.png");
        BufferedImage cercleBI = null;
        try {
            cercleBI = ImageIO.read(cercleFile);
        } catch (Exception e) {
            System.out.println(e);
        }
        ImageIcon cercle = new ImageIcon(cercleBI);
        HSCercle = new JLabel(cercle);
        HSCercle.setBounds(250+offsetMap, 65, cercle.getIconWidth(), cercle.getIconHeight());
        panel.add(HSCercle, 2, 0);

        scoreHS = new JLabel("<html>J1:0<br/>J2:0</html>");
        scoreHS.setFont(new Font("Tahoma", Font.BOLD, 20));
        scoreHS.setBounds(285+offsetMap, 85, 50, 50);
        scoreHS.setVisible(true);
        panel.add(scoreHS, 3, 0);

        BDECercle = new JLabel(cercle);
        BDECercle.setBounds(390+offsetMap, 400, cercle.getIconWidth(), cercle.getIconHeight());
        panel.add(BDECercle, 2, 0);

        scoreBDE = new JLabel("<html>J1:0<br/>J2:0</html>");
        scoreBDE.setFont(new Font("Tahoma", Font.BOLD, 20));
        scoreBDE.setBounds(425+offsetMap, 420, 50, 50);
        scoreBDE.setVisible(true);
        panel.add(scoreBDE, 3, 0);

        BUCercle = new JLabel(cercle);
        BUCercle.setBounds(440+offsetMap, 510, cercle.getIconWidth(), cercle.getIconHeight());
        panel.add(BUCercle, 2, 0);

        scoreBU = new JLabel("<html>J1:0<br/>J2:0</html>");
        scoreBU.setFont(new Font("Tahoma", Font.BOLD, 20));
        scoreBU.setBounds(475+offsetMap, 530, 50, 50);
        scoreBU.setVisible(true);
        panel.add(scoreBU, 3, 0);

        QACercle = new JLabel(cercle);
        QACercle.setBounds(475+offsetMap, 630, cercle.getIconWidth(), cercle.getIconHeight());
        panel.add(QACercle, 2, 0);

        scoreQA = new JLabel("<html>J1:0<br/>J2:0</html>");
        scoreQA.setFont(new Font("Tahoma", Font.BOLD, 20));
        scoreQA.setBounds(510+offsetMap, 650, 50, 50);
        scoreQA.setVisible(true);
        panel.add(scoreQA, 3, 0);

        HICercle = new JLabel(cercle);
        HICercle.setBounds(700+offsetMap, 600, cercle.getIconWidth(), cercle.getIconHeight());
        panel.add(HICercle, 2, 0);

        scoreHI = new JLabel("<html>J1:0<br/>J2:0</html>");
        scoreHI.setFont(new Font("Tahoma", Font.BOLD, 20));
        scoreHI.setBounds(735+offsetMap, 620, 50, 50);
        scoreHI.setVisible(true);
        panel.add(scoreHI, 3, 0);

        frame.add(panel);
        update();
        frame.setVisible(true);
    }

    public void update() {
        JLabel[] labels = {scoreBU, scoreBDE, scoreQA, scoreHI, scoreHS};
        for (int i=0; i<labels.length; i++) {
            int[] survivants = controller.survivantsZone(i);
            labels[i].setText("<html>J1:" + survivants[0] + "<br/>J2:" + survivants[1] + "</html>");
        }
        JLabel[] zones = {BUCercle, BDECercle, QACercle, HICercle, HSCercle};
        for(int i = 0; i<zones.length; i++){
            if(controller.isZoneControlee(i)){
                File cercleFile = new File("ressources/cercle_noir.png");
                BufferedImage cercleBI = null;
                try {
                    cercleBI = ImageIO.read(cercleFile);
                } catch (Exception e) {
                    System.out.println(e);
                }
                ImageIcon cercleNoir = new ImageIcon(cercleBI);
                zones[i].setIcon(cercleNoir);
                labels[i].setForeground(Color.red);
            }
        }
    }

    
    /** 
     * @param nomZone
     * @param gagnant
     */
    public void finDuCombat(String nomZone, int gagnant) {
        update();
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
