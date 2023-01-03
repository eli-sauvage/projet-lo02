package views;

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

import controllers.*;

public class CombatsView {
    CombatsController controller;
    JFrame frame;
    ImageIcon map;
    private Color bgColor = new Color(255, 128, 192);

    public CombatsView(CombatsController controller) {
        this.controller = controller;
        // System.out.println("interface combats");
        // frame = new JFrame();
        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // frame.setBounds(10, 10, 1500, 800);
        // frame.getContentPane().setBackground(bgColor);
        // frame.setLayout(null);
        // ImageIcon icone = new ImageIcon("ressources/map.png");
        // JLabel image = new JLabel(icone);
        // frame.add(image);

        try {
            File file = new File("ressources/map.png");
            BufferedImage bufferedImage = ImageIO.read(file);

            ImageIcon imageIcon = new ImageIcon(bufferedImage);
            JFrame jFrame = new JFrame();

            jFrame.setLayout(new FlowLayout());

            jFrame.setSize(500, 500);
            JLabel jLabel = new JLabel();

            jLabel.setIcon(imageIcon);
            jFrame.add(jLabel);
            jFrame.setVisible(true);
        } catch (Exception e) {
            System.out.println(e);
        }

        /// frame.setVisible(true);
    }

    public void fermer() {
        frame.setVisible(false);
    }
}
