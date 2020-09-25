package test;


import java.util.Scanner;

import controler.Controler;

public class Main {

	public static void main(String[] args) {
		
		boolean tryAgain = true;
		      
    	try {
    		
    		while( tryAgain ) {
    			
	    		@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
	            System.out.print( "Enter the file name (quit to turn off): " );
	            String file = scanner.nextLine();
	            
	            if(file.equals("quit")) return;
	            
				Controler data = new Controler(file);
				
				data.print();
				
				if (data.isNotAnAbsorberCircuit()) {
					data.FloydAlgoritm();
				}
    		}
		
    	} catch(Exception e) {
    		System.out.println("");
    	}
	}

}
