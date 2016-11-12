package com.eugenewang.mobattack;
import java.awt.*;

/**
 * Created by eugen on 9/21/2016.
 */
public class Store {
    public static int shopWidth = 12;
    public Rectangle[] button = new Rectangle[shopWidth];
    int buttonSize = (int) 1.8 *Room.blockSize;
    int iconSize = 20;
    static int coinage = 10, health =100;
    
    
    Rectangle buttonHealth, buttonCoins;
    

    public Store (){
        define();
    }

    public void define (){
        for (int i = 0; i < button.length; i++){
            button[i] = new Rectangle(Screen.myWidth/shopWidth+i*(2+buttonSize), Room.worldHeight*Room.blockSize, buttonSize, buttonSize);
        }
        
        Rectangle rectangle = button[button.length-1];
        buttonCoins = new Rectangle(rectangle.x + 20+buttonSize, rectangle.y+6, iconSize, iconSize );
        buttonHealth = new Rectangle(rectangle.x + 20+buttonSize, rectangle.y + 6 + iconSize, iconSize, iconSize );
    }
    

    public void draw (Graphics g){
        for (int i = 0; i < button.length; i++){
        	g.setColor(Color.BLACK);
        	filleRec(g, button[i]);
        	drawRec(g, Screen.assets_res[1], buttonCoins);
        	drawRec(g, Screen.assets_res[2], buttonHealth);
        	g.drawString(coinage+"", buttonCoins.x + iconSize+10, buttonCoins.y+16);
        	g.drawString(health+"", buttonHealth.x + iconSize+10, buttonHealth.y+16);
        	
        	if (button[i].contains(Screen.mse)){
        		drawRec(g, Screen.assets_res[0], button[i]);
            }        	
        }
    }
    
    void filleRec(Graphics g, Rectangle rec){
    	g.fillRect(rec.x, rec.y, rec.width, rec.height);
    }
    
    void drawRec(Graphics g, Image s, Rectangle rec){
    	g.drawImage(s, rec.x, rec.y, rec.width, rec.height, null);
    }
    
    void minusHealth(int minus){
    	this.health = health - minus;
    }
}
