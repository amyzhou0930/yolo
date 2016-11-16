package com.eugenewang.mobattack;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Init extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	private Dimension dimension = new Dimension(300, 200);
    private String title = "MobAttack - Welcome";
    private Container contentPane;

   

    public Init (){
    	super();
        this.setSize(dimension);
        this.setTitle(title);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       
        
        contentPane = this.getContentPane();
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));
        
        addButtons();

        
        this.setVisible(true);
    }
    
    void addButtons(){
    	JButton button1 = new JButton ("Window");    	
    	button1.setAlignmentX(Component.CENTER_ALIGNMENT);
    	button1.setActionCommand("win");
    	button1.addActionListener(this);
    	contentPane.add(button1);
    	
    	JButton button2 = new JButton ("Full Screen");    	
    	button2.setAlignmentX(Component.CENTER_ALIGNMENT);
    	button2.setActionCommand("ful");
    	button2.addActionListener(this);
    	contentPane.add(button2);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		this.dispose();
		new Frame ("ful" == e.getActionCommand()); 	
	}

    

}
