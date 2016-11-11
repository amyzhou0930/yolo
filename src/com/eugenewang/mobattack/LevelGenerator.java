package com.eugenewang.mobattack;
import java.io.PrintWriter;

public class LevelGenerator {
	boolean turnable = false;
	int [][] map;
	double difficult = 0.1;
	Ref ref;
	
	public LevelGenerator(int rows, int columns, int start, double difficulty){
		define (rows, columns);		
		
		difficult = difficulty;
		start = start%rows;
		ref = new Ref(start);
		
		
		for ( int i = 0; i < map.length; i++){
			derive (map[i]);
		}
		printArray(map);
	}
	
	private void define (int rows, int columns){
		map = new int [rows][columns];
		  
		for (int i = 0;  i< map.length; i ++){
			for (int j = 0; j < map[i].length; j ++){
				map[i][j] = 0;
			}
		}		
	}
	
	public static void main (String [] args){
		new LevelGenerator(16, 10, 5, 0.2);		
	}
	
	private void derive ( int []map){
		
		map[ref.getI()] = 1;
				
		if (Math.random()>difficult && turnable){			//if turn around
			System.out.print("Turning ");			
			int turning_index = ref.getI();	
			
			if (turning_index==0){		//left bound, turn right
				System.out.print("+ : ");
				while (Math.random()>0.10 && turning_index + 1 < map.length ){
					turning_index = ref.Ipp();
					System.out.print(turning_index+" ");
					map[turning_index] = 1;					
				}
				System.out.println("");
				
			}
			else if (turning_index == map.length-1){	//right bound, turn left
				System.out.print("- : ");
				while (Math.random()>0.10 && turning_index > 0 ){
					turning_index = ref.Imm();
					System.out.print(turning_index+ " ");
					map[turning_index] = 1;					
				}
				System.out.println("");
				
			} else{				//no bound anyhow turn
				if(Math.random()>0.5 ){		//turn right
					System.out.print("+ : ");
					while (Math.random()>0.10 && turning_index + 1 < map.length ){
						turning_index = ref.Ipp();
						System.out.print(turning_index+" ");
						map[turning_index] = 1;					
					}
					System.out.println("");
					
				}else {
					System.out.print("- : ");
					while (Math.random()>0.10 && turning_index > 0 ){
						turning_index = ref.Imm();
						System.out.print(turning_index+ " ");
						map[turning_index] = 1;					
					}
					System.out.println("");
					
				}	
			}
			turnable = false;
		} else {
			turnable = true;
		}
	}
	
	
	private void printArray (int [][]map){
		
		try{
		    PrintWriter writer = new PrintWriter("C:/Users/eugene/Desktop/data.txt", "UTF-8");
		    
		    for (int i = 0;  i< map.length; i ++){
				for (int j = 0; j < map[i].length; j ++){
					writer.print(map[i][j]+ " ");
				}
				writer.println();
			}
		    writer.close();
		} catch (Exception e) {
		   
		}
		
		
	}
	
	class Ref {
		int i = 0;
		Ref(){
			i = 0;
		}
		
		Ref(int target){
			i = target;
		}
		
		int getI(){
			return i;
		}
		
		void setI(int ta){
			i = ta;
		}
		
		int Imm(){
			i = i-1;
			return (i);
		}		
		
		int Ipp(){
			i = i +1;
			return (i);
		}
		
	}
}
