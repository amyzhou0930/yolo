package com.eugenewang.mobattack;
import java.awt.*;

public class Mob extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	Screen screen;
	
	Block [][] blocks;
	int mobSize = Room.blockSize ;
	int mobID = Block.MOB_AIR;
	static int mobIndex = 0;
	int thismobIndex = 0;
	int walkPause = 1;
	int health = 10000;
	
	
	long startTime = System.currentTimeMillis();
	final int upw = 8, dow = 2, fow = 6, baw =4;
	int direction = 6, xdr =  0;
	
	int mobwalk = 0;
	
	int xC, yC;
	
	boolean inGame = false;
	
	
	public Mob(Screen screen){
		
		
	}
	
	public Mob(Screen screen, int mobID){
		this.screen = screen;
		blocks = screen.getRoom().blocks;
		this.thismobIndex= ++mobIndex;
		this.mobID = mobID; 
	}
	
	public void spawnMob(){
		for (int y =0; y < blocks.length; y++){
			if (blocks[y][0].groundID != Block.GROUND_GRASS){
				setBounds(blocks[y][0].x, blocks[y][0].y, mobSize, mobSize);
				xC = 0;
				yC = y;
			}
		}		
		inGame = true;		
	}
	
	public void draw (Graphics g){
		if (inGame){
			g.drawImage(Screen.assets_mob[mobID], x, y, width, height, null);
			g.setColor(Color.YELLOW);
			g.drawString("h: "+ health/1000, x, y);
		}
	}

	public void physics() {
		if (inGame){
			if (System.currentTimeMillis()- startTime > walkPause){
				move1block();
				if (mobwalk >= Room.blockSize){					
					move2cross();
					move3decide();					
					mobwalk = 0;				
				}
				startTime = System.currentTimeMillis();
			} 		} 	}
	
	public void move1block (){
		mobwalk++;
		switch (direction){
		case 4: 
			x--;
			return;
		case 8:
			y--;
			return;
		case 6:
			x++;
			return;
		case 2:
			y++;
			return;
		default:
			return;		
		}
		
		
	}
	
	private void move2cross(){
		if (direction == fow){
			xC ++;
		} else if (direction == upw){
			yC --;
		} else if (direction == dow){
			yC ++;
		} else{
			xC --; 			//assume backward motion
		}
		
	}
	
	private void move3decide(){
		
		
		if ((xC+1)<blocks[yC].length ){
			//System.out.println(yC +":"+ xC);
			if (screen.getRoom().blocks[yC][xC+1].groundID == Block.GROUND_END){
				inGame = remove(true);			
			}
		}
				
		
	    if ((yC+1) < blocks.length){	    	
	    	if (screen.getRoom().blocks[yC+1][xC].groundID ==Block.GROUND_END){
	    		inGame = remove(true);
	    		
	    	}
	    }
	    
		if (yC-1>=0){ 
			if (screen.getRoom().blocks[yC-1][xC].groundID ==Block.GROUND_END){
				inGame = remove(true);
			}
		}
		
		if ( xC-1>=0 ){
			if (screen.getRoom().blocks[yC][xC-1].groundID == Block.GROUND_END){
				inGame = remove(true);
			}
		}
		
		
		
		if (direction != baw && (xC+1)<blocks[yC].length && screen.getRoom().blocks[yC][xC+1].groundID == Block.GROUND_ROAD){
			direction = fow;
		} else if (direction != upw && (yC+1) < blocks.length && screen.getRoom().blocks[yC+1][xC].groundID ==Block.GROUND_ROAD){
			direction = dow;
		} else if (direction != dow && yC-1>=0 && screen.getRoom().blocks[yC-1][xC].groundID ==Block.GROUND_ROAD){
			direction = upw;
		} else if (direction != fow && xC-1>=0 && screen.getRoom().blocks[yC][xC-1].groundID == Block.GROUND_ROAD){
			direction = baw;
		} 
	}
	
	private boolean remove (boolean goIn){
		if (goIn)
			screen.getStore().minusHealth(10);
		return false;
	}
	
	public boolean veriRemove(){		
		if (health <= 0){
			this.inGame = remove(false);
			return true;
		}
		return false;
	}
	
	
	
	
}
