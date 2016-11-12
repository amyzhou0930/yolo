
package com.eugenewang.mobattack;
import java.awt.*;

/**
 * Created by eugen on 9/21/2016.
 */
public class Block extends Rectangle{
	private static final long serialVersionUID = 510178287116983116L;
	
	public int groundID;
    public int airID;

    public static final int GROUND_GRASS = 0;
    public static final int GROUND_ROAD = 1;
    public static final int GROUND_END = 9;
    
    public static final int AIR_AIR = 0;
    public static final int MOB_AIR = -1;
    public static final int MOB_GREENEY = 0;


    public Block (int x, int y, int width, int height, int groundID, int airID){
        setBounds(x,y,width,height);
        this.groundID = groundID;
        this.airID = airID;
    }

    public void draw(Graphics g){
        g.drawImage(Screen.assets_ground[groundID], x,y,width,height, null);

        if (airID !=AIR_AIR){
            g.drawImage(Screen.assets_air[airID], x,y,width,height, null);

        }


    }


}
