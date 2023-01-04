package views;

import java.io.*;
import java.util.*;
import javax.swing.Timer;
import javax.imageio.*;
import javax.swing.*;

import controllers.VictoireController;

import java.awt.event.*;
import java.awt.image.*;


public class VictoireView {
    private JFrame frame;
    private VictoireController controller;
    private Timer timer;
    private JLabel feu;
    List<BufferedImage> frames;

    public VictoireView(VictoireController controller) {
        this.controller = controller;
        try {
            showView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showView() throws IOException {
        frame = new JFrame("Overlay App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane panel = new JLayeredPane();

        panel.setLayout(null);

        File file = new File("ressources/feu_artifice.gif");

        List<BufferedImage> frames = new ArrayList<>();

        // Création du reader pour lire les frames du gif
        ImageReader reader = ImageIO.getImageReadersByFormatName("gif").next();
        reader.setInput(ImageIO.createImageInputStream(file));

        // Récupération du nombre de frames du gif
        int frameCount = reader.getNumImages(true);

        // Ajout des frames à la liste
        for (int i = 0; i < frameCount; i++) {
            BufferedImage frame = reader.read(i);
            frames.add(frame);
        }

        // Fermeture du reader
        reader.dispose();

        timer = new Timer(100, new ActionListener() {
            private int index = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Mise à jour de l'image à afficher
                feu.setIcon(new ImageIcon(frames.get(index)));
                // Incrémentation de l'index (modulo la taille de la liste de frames pour revenir au début de l'animation)
                index = (index + 1) % frames.size();
            }
        });
        timer.start();
        System.out.println(frames.size());
        feu = new JLabel(new ImageIcon(frames.get(0)));

        feu.setBounds(0, 0, frames.get(0).getWidth(), frames.get(0).getHeight());
        panel.add(feu, 1, 0);

        frame.setBounds(0, 0, frames.get(0).getWidth(), frames.get(0).getHeight() + 50);

        JButton quitter = new JButton("quitter");
        quitter.setBounds(0, frames.get(0).getHeight(), 100, 30);
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                controller.continuer();
            }
        });
        panel.add(quitter);
        frame.add(panel);
        frame.setVisible(true);
    }

}
