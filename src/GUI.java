import java.awt.*;
import java.awt.event.*;

public class GUI {

	public JFrame menu;
	public JFrame interfaceArmee;
	public int pageActive = 1;
	private Color bgColor = new Color(255, 128, 192);
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//GUI window = new GUI();
					//menu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	public GUI() {
		menu = new JFrame();
		interfaceArmee = new JFrame();
		affFrame();
	}

	
	private void affFrame() {
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

		//interface armee
		
		interfaceArmee.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		interfaceArmee.setBounds(10, 10, 1500, 800);
		interfaceArmee.getContentPane().setBackground(bgColor);
		//interfaceArmee.setContentPane(interfaceArmee);
		interfaceArmee.setLayout(null);
		JLabel label11 = new JLabel("Interface Armee");
		label11.setBounds(46, 35, 228, 31);
		label11.setFont(new Font("Tahoma", Font.BOLD, 25));
		interfaceArmee.add(label11);

		//action listeners menu
		btnStart.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					System.out.println("start");
					//afficher interface armee
					menu.setVisible(false);
					interfaceArmee.setVisible(true);
			}
		});
	    
		
	}
	

   
    



}
