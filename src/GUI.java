import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
public class GUI {
    

    public GUI () {
                // Définissez le frame
                JFrame frame = new JFrame("CDB");
    
                JLabel label = new JLabel("C'est du brutal", JLabel.CENTER);
                frame.add(label);
            
                // Définissez le panel
                JPanel panel = new JPanel();
                // Définir les boutons
                JButton demarerPartie = new JButton("Start");
                JButton afficherRegles = new JButton("Regles"); 
                demarerPartie.setBounds(90,100,100,40);
                afficherRegles.setBounds(90,200,100,40);
                demarerPartie.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        System.out.println("demarage partie");
                    }
                });
                afficherRegles.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent event) {
                        System.out.println("affichage regles");
                    }
                });
                // Ajouter les boutons au frame
                panel.add(demarerPartie); 
                panel.add(afficherRegles);
                 
                // Ajouter label et panel au frame
                //frame.setLayout(new GridLayout(2, 1));
                frame.add(label);
                frame.add(panel);
                 
                //frame.pack();
                frame.setSize(250, 250);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
    }
    public static void main(String[] args) {
        new GUI();
    }
}
