//part 2, 44:10
import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Date;

/**
 * Created by eugen on 9/14/2016.
 */
public class Main extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static Dimension dimension = new Dimension(806, 600);
    private String title = "Haha";

    public static void main (String args []){

        //System.out.println ("hello");
        new Main();



    }

    public Main (){


        super();
        this.setSize(dimension);
        //this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        //this.setUndecorated(true);

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

        Screen screen = new Screen(this);
        add(screen);


        this.setVisible(true);
    }

}
