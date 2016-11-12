package com.eugenewang.mobattack;
import java.awt.*;

public class Mob extends Rectangle{
	private static final long serialVersionUID = 1L;
	
	Block [][] blocks = Screen.getRoom().blocks;
	int mobSize = Room.blockSize ;
	int mobID = Block.MOB_AIR;
	static int mobIndex = 0;
	int thismobIndex = 0;
	
	long startTime = System.currentTimeMillis();
	final int upw = 8, dow = 2, fow = 6, baw =4;
	int direction = 6, xdr =  0;
	
	int mobwalk = 0;
	
	int xC, yC;
	
	boolean inGame = false;
	
	
	public Mob(){
		
		
	}
	
	public Mob(int mobID){
		this.thismobIndex= ++mobIndex;
		this.mobID = mobID; 
	}
	
	public void spawnMob(){
		for (int y =0; y < blocks.length; y++){
			if (blocks[y][0].groundID != Block.GROUND_GRASS){
				setBounds(blocks[y][0].x, blocks[y][0].y, mobSize, mobSize);
				xC = x;
				yC = y;
			}
		}
		
		
		inGame = true;
		//System.out.println("new Mob Spawned: " + thismobIndex);
	}
	
	public void draw (Graphics g){
		if (inGame){
			g.drawImage(Screen.assets_mob[mobID], x, y, width, height, null);
		}
	}

	public void physics() {
		if (System.currentTimeMillis()- startTime >1){
			
			move1block();
			
			System.out.print(mobwalk + " ");
			//Block walk finished
			if (mobwalk >= Screen.getRoom().blockSize){
				
				move2cross();
				
				System.out.println("mob" +thismobIndex + "xC: " + xC + "yC: "+ yC);
				//Next block detect
				
				
				
				move3decide();
				
				mobwalk = 0;
				
			}
			
			startTime = System.currentTimeMillis();
		} 
	}
	
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
		if ((xC+1)<blocks[yC].length && Screen.getRoom().blocks[yC][xC+1].groundID == Block.GROUND_END){
			inGame = false;
		} else if ((yC+1) < blocks.length && Screen.getRoom().blocks[yC+1][xC].groundID ==Block.GROUND_END){
			inGame = false;
		} else if (yC-1>=0 && Screen.getRoom().blocks[yC-1][xC].groundID ==Block.GROUND_END){
			inGame = false;
		} else if ( xC-1>=0 && Screen.getRoom().blocks[yC][xC-1].groundID == Block.GROUND_END){
			inGame = false;
		} 
		
		if (direction != baw && (xC+1)<blocks[yC].length && Screen.getRoom().blocks[yC][xC+1].groundID == Block.GROUND_ROAD){
			direction = fow;
		} else if (direction != upw && (yC+1) < blocks.length && Screen.getRoom().blocks[yC+1][xC].groundID ==Block.GROUND_ROAD){
			direction = dow;
		} else if (direction != dow && yC-1>=0 && Screen.getRoom().blocks[yC-1][xC].groundID ==Block.GROUND_ROAD){
			direction = upw;
		} else if (direction != fow && xC-1>=0 && Screen.getRoom().blocks[yC][xC-1].groundID == Block.GROUND_ROAD){
			direction = baw;
		} 
	}
	
	
	
	
}
