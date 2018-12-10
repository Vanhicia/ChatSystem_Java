import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Window implements ActionListener {
	public String pseudo;
	public JFrame f; 
	
	public Window() {
		
		 this.f = new JFrame(); //Creating instance of JFrame
		
		//Add filed for pseudo
		JTextField fieldPseudo = new JTextField();
		fieldPseudo.setBounds(128, 28, 150, 20);
		f.getContentPane().add(fieldPseudo);
		fieldPseudo.setColumns(50);
		
		JLabel lblPseudo = new JLabel("Pseudo");
		lblPseudo.setBounds(65, 31, 70, 14);
		f.getContentPane().add(lblPseudo);
		
		this.pseudo = fieldPseudo.getText(); 
		
		// Add button "create Account"
		JButton b = new JButton("Create account");
		b.setBounds(130,100,300,40); // x y width height
		b.addActionListener(this);
		f.add(b);
		
		
		f.setSize(500,500);//width x height  
		f.setLayout(null);
		f.setVisible(true);
	}

	public void launchConnection() {
		Controller c = new Controller(this);
		c.createAgent(this.pseudo);
		
		// Add button "Launch Session"
		JButton b = new JButton("Launch Session");
		b.setBounds(130,100,300,40); // x y width height
		b.addActionListener(this);
		f.add(b);
		f.setLayout(null);
		f.setContentPane(b);

		f.repaint();

		f.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		launchConnection();
	}
	

}
