package views;

import javax.swing.*;
import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import controllers.*;

public class CombatsView {
    CombatsController controller;
    JFrame frame;
    ImageIcon map;
    private Color bgColor = new Color(255, 128, 192);

    public CombatsView(CombatsController controller) {
        this.controller = controller;
        System.out.println("interface combats");
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(10, 10, 1500, 800);
        frame.getContentPane().setBackground(bgColor);
        frame.setLayout(null);
        // JLabel label11 = new JLabel("COMBATS");
        // label11.setBounds(46, 35, 400, 31);
        // label11.setFont(new Font("Tahoma", Font.BOLD, 25));
		// frame.add(label11);
        // map = new ImageIcon("ressources\\map.png");
        // frame.add(new JLabel(map));

        Container c = frame.getContentPane(); // Gets the content layer
        JLabel label = new JLabel(); // JLabel Creation
        BufferedImage image = null;
        try{
            System.out.println(getClass().getResource("/src/ressources/map.png"));
            //image = ImageIO.read();
        }catch(Exception e){
            System.out.println(e.toString());
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
        }
        label.setIcon(new ImageIcon(image)); // Sets the image to be displayed as an icon
        Dimension size = label.getPreferredSize(); // Gets the size of the image
        label.setBounds(50, 30, size.width, size.height); // Sets the location of the image

        c.add(label); // Adds objects to the container
        frame.setVisible(true); // Exhibit the frame




        frame.setVisible(true);
    }

    public void fermer() {
        frame.setVisible(false);
    }
}
