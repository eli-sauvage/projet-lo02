package views;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import controllers.*;
/**
 * la vue qui affiche le menu principal
 */
public class MenuView {
	private  JFrame menu = new JFrame();
	private Color bgColor = new Color(255, 128, 192);

    private MenuController controller;
	/**
	 * @param controller le controller associe a la vue
	 */
    public MenuView(MenuController controller){
        this.controller = controller;
        interfaceMenu();
    }

    /**
     * affiche l'interface du menu
     */
    private void interfaceMenu() {
        // menu
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
        // action listeners menu
        btnStart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("start");
                controller.startGame();
            }
        });
        btnRules.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("rules");
                controller.afficherRegles();
            }
        });
    }
    /**
     * ferme la fenetre du menu
     */
    public void fermer(){
        menu.setVisible(false);
    }
}
