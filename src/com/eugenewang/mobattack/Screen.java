package com.eugenewang.mobattack;
import javax.swing.*;
import java.awt.*;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;

/**
 * Created by eugen on 9/14/2016.
 */
public class Screen extends JPanel implements Runnable{
	private static final long serialVersionUID = 1L;
	private Thread thread = new Thread(this);
    private static boolean isFirst = true;
    private final int initial_wait = 5000;
    static int myWidth, myHeight;
    static int coinage = 10, health =100;
    
    Int current = new Int(0);
    Int time = new Int (System.currentTimeMillis()+initial_wait);

    private static Room room;
    private static Level level;
    private static Store store;
    private static Mob mobs []; 

    static Point mse = new Point(0,0);

    private static final int lengthOfFile = 10;
    static Image[] assets_ground = new Image[lengthOfFile];
    static Image[] assets_air = new Image[lengthOfFile];
    static Image[] assets_res = new Image [lengthOfFile];
    static Image[] assets_mob = new Image [lengthOfFile];


    public Screen (JFrame frame){
    	this.addMouseListener (new KeyHandel());
        this.addMouseMotionListener (new KeyHandel());
        
    	thread.start();

    }



    private void define(){
    	
        myWidth = getWidth();
        myHeight = getHeight();

        room = new Room();
        level = new Level();
        store = new Store();
        mobs = new Mob[20];
        
        
        

        for (int i =0; i <lengthOfFile;i++){
            assets_ground[i]=new ImageIcon("res/assets_ground.png").getImage();
            assets_ground[i]=createImage(
                    new FilteredImageSource(assets_ground[i].getSource(), new CropImageFilter(0, 52*i,52,52))
            );

            assets_air[i]=new ImageIcon("res/assets_air.png").getImage();
            assets_air[i]=createImage(
                    new FilteredImageSource(assets_air[i].getSource(), new CropImageFilter(0, 52*i,52,52))
            );
            
            assets_res[i]=new ImageIcon("res/assets_res.png").getImage();
            assets_res[i]=createImage(
                    new FilteredImageSource(assets_res[i].getSource(), new CropImageFilter(0, 52*i,52,52))
            );
            
            assets_mob[i]=new ImageIcon("res/assets_mob.png").getImage();
            assets_mob[i]=createImage(
                    new FilteredImageSource(assets_mob[i].getSource(), new CropImageFilter(0, 52*i,52,52))
            );

        }

        level.loadLevel(new File("Level/level_1.zip"));
        
        for (int i = 0; i < mobs.length; i++){
        	mobs[i] = new Mob(0);        	
        }

    }

    public static Room getRoom(){
        return room;
    }

    public void paintComponent (Graphics g){
        if(isFirst){
            define ();
            isFirst = false;
        }
        g.setColor(Color.pink);
        g.fillRect(0,0,getWidth(), getHeight());
        g.setColor(Color.black);
        
        room.draw(g); //drawing the room
        store.draw(g); // drawing the store
        
        
        for (int i = 0; i < mobs.length; i++){
        	if (mobs[i].inGame){
        		mobs[i].draw(g);
        	}
        }
        
        
        g.drawString(mse.getX() + ":" + mse.getY(), 1, 20);



    }

    //private static int fpsFrame = 0, fps = 10000;
	

    
    public void run() {
        while (true){
            if(!isFirst){
                room.physics();
                mobSpawnControl(time, current, mobs.length);
                for (int i =0; i < current.getI(); i++ ){
                	mobs[i].physics();
                }
                
            }
            
            
            	
            repaint();

            
        }
    }
    
    void mobSpawnControl (Int time, Int curren, int limit){
    	int current = curren.getI();
    	if (current < limit){
    		if (System.currentTimeMillis() - time.getL() > 1000) {

    	    	mobs[current].spawnMob();
    	    	System.out.println("mobSpawned" + mobs[current].thismobIndex +" : "+ current);
    	    	
    	    	curren.A();
    	    	time.setL(System.currentTimeMillis());
        	} else{}	    	
    	}    	
    }
    
    
    
    static class Int {
		int i;
		long l;
		
		//int
		
		Int (){
			i = 0;
		}
		
		Int (int i){
			this.i = i;
		}
		
		public int getI(){
			return i;
		}
		
		public void setI( int given){
			i  = given;
		}
		
		public void A (){
			i++;
		}
		
		
		//long
		
		
		
		Int (long l){
			this.l = l;
		}
		
		public long getL(){
			return l;
		}
		
		public void setL( long given){
			l  = given;
		}
		
		public void AL (){
			l++;
		}
		
		
	}
	

}



