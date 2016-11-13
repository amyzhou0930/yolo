package com.eugenewang.mobattack;
import java.io.File;
import java.util.Scanner;

/**
 * Created by eugen on 9/21/2016.
 */
public class Level {
	Block [][]blocks;
	final int rows = 16, column = 10, start = 5;
	final double difficulty = 0.2;
	
	public Level(Block [][] blocks){
		this.blocks = blocks;
	}
	
	public void nexLevel(int level){
		Scanner checks;
		int totallevel = level;
		try {
			checks = new Scanner ("Level/gateway");
			totallevel = checks.nextInt();
		} catch (Exception e){
			
		}
		
		if (totallevel<=level){
			for (int i = level+1; i < level+11; i++){
				new LevelGenerator (rows, column, start, difficulty);
			}
		}
	}


    public void loadLevel (File loadPath){
        try {
            Scanner loadScanner = new Scanner(loadPath);

            while (loadScanner.hasNext()){
                for (int y = 0; y <blocks.length;y++){
                    for (int x = 0; x <blocks[y].length;x++){
                        blocks[y][x].groundID = loadScanner.nextInt();
                        //System.out.print (Screen.getRoom().blocks[y][x].groundID); 		//debug ground
                    }
                    //System.out.println("");			
                }

                //System.out.println("\n\n");

                for (int y = 0; y <blocks.length;y++){
                    for (int x = 0; x <blocks[y].length;x++){
                        blocks[y][x].airID = loadScanner.nextInt();                       
                    }
                }
            }
            loadScanner.close();
        } catch (Exception e){
            System.out.println("LoadingLevel + " + e);
        }
    }
}
