import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class SessionWindow extends Window implements ActionListener {
	public SessionWindow(String pseudo, JFrame f){
	//	this.pseudo = pseudo;
	//	this.f = f;
		
		// Add button "Launch Session"
		JButton b = new JButton("Launch Session");
		b.setBounds(130,100,300,40); // x y width height
		b.addActionListener(this);
		f.add(b);
		
		f.setSize(500,500);//width x height  
		
		f.revalidate();
		f.setLayout(null);
		f.setVisible(true);

	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
/*import javax.swing.*;

public class Window {
	public JFrame f; 
	public String pseudo; 

	public Window() {
		 this.f = new JFrame(); //Creating instance of JFrame
	}

	public JFrame getF() {
		return this.f;
	}
	

}
*/