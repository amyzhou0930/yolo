
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
    int shotMob = 0;
    boolean shoting = false;

    static final int GROUND_GRASS = 0;
    static final int GROUND_ROAD = 1;
    static final int GROUND_END = 9;
    
    static final int AIR_AIR = 0;
    static final int MOB_AIR = -1;
    static final int MOB_GREENEY = 0;
    static final int AIR_TRASHCAN = 9;
    
    Rectangle towerSquare;
    
    

    public Block (int x, int y, int width, int height, int groundID, int airID){
        setBounds(x,y,width,height);
        towerSquare = new Rectangle (x - towerSquareSize/2, y - towerSquareSize/2, width  + towerSquareSize, height + towerSquareSize );
        this.groundID = groundID;
        this.airID = airID;
    }
    
    public void physic(Screen screen){
    	if (this.airID > Block.AIR_AIR){
    		for (int y = 0; y <screen.mobs.length; y++){
    			if (screen.mobs[y].inGame){
    				if (towerSquare.contains(screen.mobs[y])){
    					System.out.println("mob " + y + "shooting Range");
    				}
    			}
    		}
    	}
    }

    public void draw(Graphics g){
        g.drawImage(Screen.assets_ground[groundID], x,y,width,height, null);

        if (airID !=AIR_AIR){
            g.drawImage(Screen.assets_air[airID], x,y,width,height, null);
        }
    }
    
    public void battle (Graphics g){
    	if (airID>Block.AIR_AIR){ 
    		g.setColor(Color.PINK);
    		g.drawRect(towerSquare.x, towerSquare.y, towerSquare.width, towerSquare.height);
    	}
    }


}
