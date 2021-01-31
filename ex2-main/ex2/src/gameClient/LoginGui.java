package gameClient;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class LoginGui extends JFrame{

	
	void start()
	{
		this.getContentPane().setLayout(new FlowLayout());
		
		JLabel id_label=new JLabel("Enter your id ");
		JLabel level_label=new JLabel("Enter Level 0-23 ");
		
		JTextField text_id=new JTextField("",15);
		JTextField text_level=new JTextField("",15);
		JButton but =new JButton("Run");
		
		
		this.add(id_label);
		this.add(text_id);
		this.add(level_label);
		this.add(text_level);
		this.add(but);
		
		this.pack();
		this.setVisible(true);
		
			but.addActionListener(new ActionListener() { 
				public void actionPerformed(ActionEvent e) {

					Ex2 ex=new Ex2(Integer.parseInt(text_id.getText()),Integer.parseInt(text_level.getText()));
					Thread client = new Thread(ex);
					client.start();
				}
			});
			
		
		
	}
	


}
