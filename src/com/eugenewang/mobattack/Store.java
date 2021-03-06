package com.eugenewang.mobattack;
import java.awt.*;

/**
 * Created by eugen on 9/21/2016.
 */
public class Store  {
	
	
	Block blocks [][];
	
    int shopWidth = 10;
    Rectangle[] button = new Rectangle[shopWidth];
    int []price = new int [] {100, 100, 100, 100, 100, 100, 100, 100, 100, 100};
    int buttonSize = (int) 1.8 *Room.blockSize;
    int iconSize = 20;
    int heldID = -1;
    boolean holds = false;
    int coinage = 10000000, health =100;
    int buttonID[] = new int[shopWidth];
    
    final int RIGHT_CLICK = 3, LEFT_CLICK = 1;
    
    
    
    Rectangle buttonHealth, buttonCoins;
    

    public Store (Block[][] blocks ){
    	this.blocks = blocks;
        define();
        
    }
    
    public void click (int mouseButtonClick){
    	if (mouseButtonClick == RIGHT_CLICK){
    		for (int i = 0; i < button.length; i++){
    			if (button[i].contains(Screen.mse)){
    					heldID = buttonID[i];
        				holds = true; 				    				
    			}
    		}
    		
    		if (holds){
    			if (coinage>= price[heldID]){
    				for (int y = 0; y < blocks.length; y++){
    					for (int x = 0; x < blocks[y].length; x++){
    						if (blocks[y][x].contains(Screen.mse)){    							
	    						if (blocks[y][x].groundID <= Block.GROUND_GRASS && blocks[y][x].airID <= Block.AIR_AIR){
	    							blocks[y][x].airID = heldID;
	    							coinage -=price[heldID];
	    						}
    						}
    					}
    				}
    				
    			}
    		}
    		
    	} else if (mouseButtonClick == LEFT_CLICK ){
    		holds = false;
    	}
    }

    public void define (){
    	
        for (int i = 0; i < button.length; i++){
            button[i] = new Rectangle(Screen.myWidth/shopWidth+i*(2+buttonSize), Room.worldHeight*Room.blockSize, buttonSize, buttonSize);
            buttonID[i] = i;
        }
        
        Rectangle rectangle = button[button.length-1];
        buttonCoins = new Rectangle(rectangle.x + 20+buttonSize, rectangle.y+6, iconSize, iconSize );
        buttonHealth = new Rectangle(rectangle.x + 20+buttonSize, rectangle.y + 6 + iconSize, iconSize, iconSize );
    }
    

    public void draw (Graphics g){
        for (int i = 0; i < button.length; i++){
        	g.setColor(Color.BLACK);
        	filleRec(g, button[i]);
        	drawRec(g, Screen.assets_air[i], button[i]);
        	g.drawString(""+price[i], button[i].x, button[i].y+10);
        	
        	if (button[i].contains(Screen.mse)){
        		drawRec(g, Screen.assets_res[0], button[i]);
            }
        }
        
        drawRec(g, Screen.assets_res[1], buttonCoins);
    	drawRec(g, Screen.assets_res[2], buttonHealth);
    	g.drawString(coinage+"", buttonCoins.x + iconSize+10, buttonCoins.y+16);
    	g.drawString(health+"", buttonHealth.x + iconSize+10, buttonHealth.y+16);
    	
    	if (holds) {
    		drawRec(g, Screen.assets_air[heldID], new Rectangle(Screen.mse.x-25, Screen.mse.y-25, 50, 50));
    	}
    	    	
    }
    
    void filleRec(Graphics g, Rectangle rec){
    	g.fillRect(rec.x, rec.y, rec.width, rec.height);
    }
    
    void drawRec(Graphics g, Image s, Rectangle rec){
    	g.drawImage(s, rec.x, rec.y, rec.width, rec.height, null);
    }
    
    void minusHealth(int minus){
    	health = health - minus;
    }

	

	
}
