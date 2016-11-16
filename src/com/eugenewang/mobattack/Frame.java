package com.eugenewang.mobattack;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

public class Frame extends JFrame{
	private static final long serialVersionUID = 1L;
	public static Dimension dimension = new Dimension(806, 600);
    private String title = "Haha";
    public Screen screen;
    static Frame frame;

   

    public Frame (boolean fullScreen){
        this.setSize(dimension);
        
        if (fullScreen){
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setUndecorated(true);
        }
        

        this.setTitle(title);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        initialization();
        try{
            BufferedWriter r = new BufferedWriter(new FileWriter("Hha.txt",true));
            r.write("Built on " + new Date());
            r.newLine();
            r.close();
        } catch (Exception e){}

    }

    private void initialization (){
        this.setLayout(new GridLayout(1,1,0,0));
        screen = new Screen(this);
        add(screen);
        this.setVisible(true);
    }

}
