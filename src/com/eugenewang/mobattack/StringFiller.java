package com.eugenewang.mobattack;

import java.util.HashMap;
import java.util.Scanner;

public class StringFiller {
	public static int english = 0x1;
	public static int french = 0x2;
	public static int spanish = 0x3; 
	public static int german = 0x4; 
	public static int chinese = 0x5; 
	public static int japanese = 0x3; 
	
	Scanner sc; 	
	HashMap<String, String> text = new HashMap<String, String>();
	
	
	public StringFiller(){		
		init(english);
	}
	
	public StringFiller(int lang){
		init(lang);
	}
	
	private void init (int lang){
		sc = new Scanner ("res/lang.csv");
		String buffer [];
		sc.nextLine();
		
		while (sc.hasNextLine()){
			String as = sc.nextLine();
			buffer = as.split(",");
			text.put(buffer[0], buffer[lang]);
			System.out.print(as);
		}
		
	}
	
	public String p (String key){
		return (text.get(key) == null)? "null":text.get(key) ;
	}
	
	
	
	
}
