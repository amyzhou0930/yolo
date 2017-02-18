package com.eugenewang.mobattack;

import java.util.Scanner;

public class StringFiller {
	public static int english = 0x1;
	public static int french = 0x2;
	public static int spanish = 0x3; 
	
	private int languageSelected; 
	Scanner sc; 
	
	
	public StringFiller(){
		languageSelected = english;
		
	}
	
	public StringFiller(int Language){
		languageSelected = Language; 
		
		
	}
	
	private void init (){
		sc = new Scanner ("")
	}
	
	
}
