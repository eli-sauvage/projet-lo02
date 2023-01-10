package views;

import java.io.*;
import javax.swing.*;

import controllers.VictoireController;

import java.awt.*;
import java.awt.event.*;

/**
 * la vue qui affiche le popup de victoire
 */
public class VictoireView {
    private JFrame frame;
    private VictoireController controller;
	/**
	 * @param controller le controller associe a la vue
	 */
    public VictoireView(VictoireController controller) {
        this.controller = controller;
        try {
            showView(controller.getGagnant());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /** 
     * affiche la fenetre de victoire
     * @param gagnant le nom du gagant
     * @throws IOException si le fichier gif n'est pas trouvé
     */
    public void showView(String gagnant) throws IOException {

        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = (JPanel) frame.getContentPane();
        panel.setLayout(new java.awt.BorderLayout());
        frame.setTitle("VICTOIRE");

        JLabel label = new JLabel("Gagnant : J" + gagnant);
        label.setFont(new Font("Tahoma", Font.PLAIN, 36));
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setForeground(new Color(255, 0, 0));

        frame.add(label, BorderLayout.NORTH);

        ImageIcon ii = new ImageIcon("ressources/feu_artifice.gif");
        JLabel imageLabel = new JLabel();
        imageLabel.setIcon(ii);
        panel.add(imageLabel, BorderLayout.CENTER);
        // show it
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        frame.setSize(ii.getIconWidth(), ii.getIconHeight() + 150);

        JButton quitter = new JButton("quitter");
        quitter.setSize(100, 50);
        quitter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                controller.continuer();
            }
        });
        panel.add(quitter, BorderLayout.SOUTH);
        frame.setVisible(true);
    }

}
