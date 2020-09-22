package test;


import java.util.Scanner;

import controler.Controler;

public class Main {

	public static void main(String[] args) {
		
		Controler data = new Controler();
		
		boolean tryAgain = true;
		      
//        while( tryAgain ) {
//        	try { 
//        		Scanner scanner = new Scanner(System.in);
//	            System.out.print( "Enter the file name : " );
//	            String file = scanner.nextLine();
//	            
//	            if(file.equals("quit")) return;
//	            
//        	} catch(Exception e) {
//        		System.out.println("");
//        	}
			
			data.start("test.txt");
			
			data.print();
			
			data.FloydAlgoritm();
			
			if (data.isTheGraphAnAbsorberCircuit()) {
				data.showRoute();
			}
//        }
	}

}
