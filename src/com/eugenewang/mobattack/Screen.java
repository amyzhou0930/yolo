package com.eugenewang.mobattack;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;

/**
 * Created by eugen on 9/14/2016.
 */
public class Screen extends JPanel implements Runnable, MouseMotionListener, MouseListener{
	private static final long serialVersionUID = 1L;
	private Thread thread = new Thread(this);
    private static boolean isFirst = true;
    private final int initial_wait = 0;
    private int timeToSpawn = 1000;
    private Int current = new Int(0);
    private Int time = new Int (System.currentTimeMillis()+initial_wait);
    private boolean is_alive = true;
    
    private static final int lengthOfFile = 10;

    private Room room;
    private Level level;
    private  Store store;
   
    static int myWidth, myHeight;
    static Point mse = new Point(0,0);
    Mob mobs [];    
    
    static Image[] assets_ground = new Image[lengthOfFile];
    static Image[] assets_air = new Image[lengthOfFile];
    static Image[] assets_res = new Image [lengthOfFile];
    static Image[] assets_mob = new Image [lengthOfFile];


    public Screen (JFrame frame){    	
        this.addMouseMotionListener (this);
        this.addMouseListener (this);
    	thread.start();
    }



    private void define(){    	
        myWidth = getWidth();
        myHeight = getHeight();

        room = new Room(this);
        level = new Level(room.blocks);
        store = new Store(room.blocks);
        mobs = new Mob[2000];        

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

        level.loadLevel(new File("Level/level_3.zip"));
        
        //define mobs here
        for (int i = 0; i < mobs.length; i++){
        	mobs[i] = new Mob(this, 0);        	
        }
    }

    public  Room getRoom(){
        return room;
    }
    
    public  Store getStore(){
    	return store;
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
        if (!is_alive){
        	g.setColor(new Color (255, 255, 255, 160));
        	g.fillRect(0, 0, 1000, 1000);
        	g.setColor(Color.RED);
        	g.setFont(new Font("Verdana", Font.BOLD, 120));
        	g.drawString("YOU", 40, 230);
        	g.drawString("  SUCK!!!", 40, 380);
        }
    }

    //private static int fpsFrame = 0, fps = 10000;
	

    
    public void run() {
        while (true){
            if(!isFirst && is_alive){
                room.physics();
                mobSpawnControl(time, current, mobs.length);
                
                for (int i =0; i < current.getI(); i++ ){
                	mobs[i].physics();
                }
                if (store.health <=0 )
                	is_alive = false;
                
            }            
            repaint();
        }
    }
    
    void mobSpawnControl (Int time, Int curren, int limit){
    	int current = curren.getI();
    	if (current < limit){
    		if (System.currentTimeMillis() - time.getL() > timeToSpawn) {

    	    	mobs[current].spawnMob();
    	    	//System.out.println("mobSpawned" + mobs[current].thismobIndex +" : "+ current);
    	    	
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





	@Override
	public void mouseDragged(MouseEvent e) {
		mse = new Point(e.getX() + (Frame.dimension.width - Screen.myWidth)/2 , e.getY() + (Frame.dimension.height -  Screen.myHeight -Frame.dimension.width+Screen.myWidth)/2);
		
	}



	@Override
	public void mouseMoved(MouseEvent e) {
        mse = new Point(e.getX() + (Frame.dimension.width - Screen.myWidth)/2 , e.getY() + (Frame.dimension.height -  Screen.myHeight -Frame.dimension.width+Screen.myWidth)/2);
    }



	@Override
	public void mouseClicked(MouseEvent e) {
		store.click(e.getButton());
		
	}



	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void mousePressed(MouseEvent e) {
		store.click(e.getButton());		
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	

}



