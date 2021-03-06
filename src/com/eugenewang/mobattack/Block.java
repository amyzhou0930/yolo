
package com.eugenewang.mobattack;
import java.awt.*;

/**
 * Created by eugen on 9/21/2016.
 */
public class Block extends Rectangle{
	private static final long serialVersionUID = 510178287116983116L;
	
	int groundID;
    int airID;
    int towerSquareSize = 100;
    

    static final int GROUND_GRASS = 0;
    static final int GROUND_ROAD = 1;
    static final int GROUND_END = 9;
    
    static final int AIR_AIR = 0;
    static final int MOB_AIR = -1;
    static final int MOB_GREENEY = 0;
    static final int AIR_TRASHCAN = 9;
    
    Rectangle towerSquare;

	private boolean shooting = false;
	int shotMob = -1;
    

    public Block (int x, int y, int width, int height, int groundID, int airID){
        setBounds(x,y,width,height);
        towerSquare = new Rectangle (x - towerSquareSize/2, y - towerSquareSize/2, width  + towerSquareSize, height + towerSquareSize );
        this.groundID = groundID;
        this.airID = airID;
    }
    
    public void physic(Screen screen){
    	if (shotMob >=0 && towerSquare.intersects(screen.mobs[shotMob])){
    		shooting = true; 
    	} else {
    		shooting = false;
    	}
    	
    	
    	if(!shooting){    	
	    	if (this.airID > Block.AIR_AIR){
	    		for (int y = 0; y <screen.mobs.length; y++){
	    			if (screen.mobs[y].inGame){
	    				if (towerSquare.intersects(screen.mobs[y])){
	    					shooting  = true;
	    					shotMob = y;
	    					//System.out.println("mob " + y + "shooting Range");
	    				}
	    			}
	    		}
	    	}
    	}
    	
    	if (shooting){
    		screen.mobs[shotMob].health -= 1;
    		if (screen.mobs[shotMob].veriRemove()){
    			shooting = false;
    			shotMob = -1;
    		}
    	}
    }

    public void draw(Graphics g){
        g.drawImage(Screen.assets_ground[groundID], x,y,width,height, null);

        if (airID !=AIR_AIR){
            g.drawImage(Screen.assets_air[airID], x,y,width,height, null);
        }
    }
    
    public void battle (Graphics g, Screen screen){
    	if (airID>Block.AIR_AIR){ 
    		g.setColor(Color.PINK);
    		g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
    	}
    	
    	if (shooting){
    		g.drawLine(x+ (width/2), y+(height/2), screen.mobs[shotMob].x + screen.mobs[shotMob].width/2, screen.mobs[shotMob].y + screen.mobs[shotMob].height/2);
    	}
    	
    }


}
