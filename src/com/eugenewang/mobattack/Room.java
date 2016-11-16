package com.eugenewang.mobattack;
import java.awt.*;

/**
 * Created by eugen on 9/21/2016.
 */
public class Room {
    public static int worldWidth = 10;
    public static int worldHeight = 10;
    public static int blockSize;

    Screen screen;

    public Block[][] blocks;

    public Room(Screen screen){
        define();
        
        this.screen = screen;
    }


    void define (){
    	blockSize = 50;
        worldWidth = Screen.myWidth/blockSize;
        worldHeight = (Screen.myHeight-52)/blockSize;
        


        //System.out.println("myWidth "+worldWidth);
        //System.out.println("myHeigh "+worldHeight);

        blocks = new Block[worldHeight][worldWidth];

        for (int y =0; y < blocks.length;y++){
            for (int x = 0; x<blocks[y].length;x++){
                blocks[y][x] = new Block(
                        Screen.myWidth/2 - worldWidth*blockSize/2 + (x*blockSize),
                        y*blockSize,
                        blockSize,
                        blockSize,
                        Block.GROUND_GRASS, 
                        Block.AIR_AIR
                );
            }
        }
    }

    public void draw (Graphics g){
        for (int y =0; y < blocks.length;y++){
            for (int x = 0; x<blocks[y].length;x++){
                blocks[y][x].draw(g);
                //blocks[y][x].battle(g);
               
            }
        }
        
        for (int y =0; y < blocks.length;y++){
            for (int x = 0; x<blocks[y].length;x++){
                blocks[y][x].battle(g, screen);
               
            }
        }
        
        
    }

    void physics(){
    	for (int y = 0; y < blocks.length ; y++){
    		for (int x = 0; x < blocks[y].length ; x++){
    			blocks[y][x].physic(screen);
    		}
    	}
    }



}
