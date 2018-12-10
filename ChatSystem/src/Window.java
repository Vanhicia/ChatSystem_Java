import javax.swing.*;

public class Window {
	
	public Window() {
		
		JFrame f = new JFrame(); //Creating instance of JFrame
		
		// Add button "create Account"
		JButton b = new JButton("Create account");
		b.setBounds(130,100,300,40); // x y width height
		
		f.add(b);
		
		//Add filed for pseudo
		JTextField fieldPseudo = new JTextField();
		fieldPseudo.setBounds(128, 28, 150, 20);
		f.getContentPane().add(fieldPseudo);
		fieldPseudo.setColumns(50);
		
		JLabel lblPseudo = new JLabel("Pseudo");
		lblPseudo.setBounds(65, 31, 70, 14);
		f.getContentPane().add(lblPseudo);
		
		
		f.setSize(500,500);//width x height  
		f.setLayout(null);
		f.setVisible(true);
	}
	
	public void refreshWindow() {
		
	}
}
