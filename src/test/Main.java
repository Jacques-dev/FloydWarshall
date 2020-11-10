package test;


import java.util.Scanner;

import controler.Controler;

public class Main {

	@SuppressWarnings("resource")
	public static void main(String[] args) {
		      
    	try {
    		
    		while( true ) {
    			
	    		@SuppressWarnings("resource")
				Scanner scanner = new Scanner(System.in);
	            System.out.print( "Entrer le nom du fichier (ex : test.txt): " );
	            String file = scanner.nextLine();
	            
				Controler data = new Controler(file);
				
				data.print();
				
				if (data.isNotAnAbsorberCircuit()) {
					data.FloydAlgoritm();
				} else {
					System.out.println("Ce graph possède un circuit...");
				}
				
				return;
    		}
		
    	} catch(Exception e) {
    		System.out.println("");
    	}
	}

}
